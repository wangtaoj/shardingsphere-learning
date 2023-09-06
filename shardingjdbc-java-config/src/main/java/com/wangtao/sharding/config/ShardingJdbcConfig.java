package com.wangtao.sharding.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.broadcast.api.config.BroadcastRuleConfiguration;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.AlgorithmConfiguration;
import org.apache.shardingsphere.infra.config.mode.ModeConfiguration;
import org.apache.shardingsphere.infra.config.rule.RuleConfiguration;
import org.apache.shardingsphere.mode.repository.standalone.StandalonePersistRepositoryConfiguration;
import org.apache.shardingsphere.sharding.algorithm.sharding.classbased.ClassBasedShardingAlgorithmStrategyType;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.keygen.KeyGenerateStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.single.api.config.SingleRuleConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * @author wangtao
 * Created at 2023/9/3 13:43
 */
@Configuration(proxyBeanMethods = false)
public class ShardingJdbcConfig {

    /**
     * 注入shardingjdbc构建出来的数据源
     * 如果表没有加载到元数据中
     * 1) 检查actualDataNodes属性, 要配置真实的database.table
     * 2) 检查数据源的key, 必须为真实的database name
     */
    @Bean
    public DataSource dataSource() throws SQLException {
        // 指定逻辑 Database 名称
        String databaseName = "tradedb";
        // 构建运行模式
        ModeConfiguration modeConfig = modeConfiguration();
        // 构建真实数据源
        Map<String, DataSource> dataSourceMap = dataSourceMap();
        // 构建具体规则
        Collection<RuleConfiguration> ruleConfigs = ruleConfigs();
        // 构建属性配置
        Properties props = new Properties();
        // 打印sql
        props.put("sql-show", true);
        return ShardingSphereDataSourceFactory.createDataSource(databaseName, modeConfig, dataSourceMap, ruleConfigs, props);
    }

    /**
     * 元数据保存在repository表中
     * 规则信息key: /metadata/tradedb/versions/0/rules
     * 数据源信息key: /metadata/tradedb/versions/0/data_sources
     * 数据表key parent: /metadata/tradedb/schemas/tradedb/tables
     */
    private ModeConfiguration modeConfiguration() {
        Properties properties = new Properties();
        properties.setProperty("provider", "MySQL");
        properties.setProperty("jdbc_url", "jdbc:mysql://localhost:3306/tradedb_1?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai");
        properties.setProperty("username", "root");
        properties.setProperty("password", "123456");
        StandalonePersistRepositoryConfiguration persistRepositoryConfiguration =
                new StandalonePersistRepositoryConfiguration("JDBC", properties);
        return new ModeConfiguration("Standalone", persistRepositoryConfiguration);
    }

    private Map<String, DataSource> dataSourceMap() {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        // 使用tomcat数据源由于元数据存在时反序列化时信息不对称, 导致连接不上
        HikariDataSource dataSource1 = new HikariDataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setJdbcUrl("jdbc:mysql://localhost:3306/tradedb_1?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai");
        dataSource1.setUsername("root");
        dataSource1.setPassword("123456");
        // 注意key是真实的数据库名称, 否则加载不了表的元数据(单表、广播表)
        dataSourceMap.put("tradedb_1", dataSource1);
        return dataSourceMap;
    }

    private Collection<RuleConfiguration> ruleConfigs() {
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        shardingRuleConfiguration.getTables().add(getTrTradeInfoTableRuleConfiguration());
        shardingRuleConfiguration.getShardingAlgorithms().put("halfYear", classBasedAlgorithmConfiguration());
        shardingRuleConfiguration.getKeyGenerators().put("snowflake", snowflakeAlgorithmConfiguration());

        // 广播表, 新增修改时每个库都会执行, 一般都是公共表, 且不分表, 每个库的数据和表结构一致
        BroadcastRuleConfiguration broadcastRuleConfiguration = new BroadcastRuleConfiguration(List.of("user"));

        // 单表, 数据源中特有的表, 不是所有库都有这个表
        SingleRuleConfiguration singleRuleConfiguration = new SingleRuleConfiguration();
        // 特别的可以使用*.*来加载所有表
        singleRuleConfiguration.getTables().add("tradedb_1.single_table");
        return List.of(shardingRuleConfiguration, broadcastRuleConfiguration, singleRuleConfiguration);
    }

    private ShardingTableRuleConfiguration getTrTradeInfoTableRuleConfiguration() {
        ShardingTableRuleConfiguration tableRuleConfiguration =
                new ShardingTableRuleConfiguration("tr_trade_info", "tradedb_${[1]}.tr_trade_info_${0..3}");
        // 库分片策略
        tableRuleConfiguration.setDatabaseShardingStrategy(null);
        // 表分片策越
        tableRuleConfiguration.setTableShardingStrategy(new StandardShardingStrategyConfiguration("txn_dt", "halfYear"));
        // 主键生成策略
        tableRuleConfiguration.setKeyGenerateStrategy(new KeyGenerateStrategyConfiguration("txn_id", "snowflake"));
        return tableRuleConfiguration;
    }

    private AlgorithmConfiguration classBasedAlgorithmConfiguration() {
        // 属性全部使用字符串形式, 否则再次启动时由于类型序列化可能导致启动报错
        Properties properties = new Properties();
        // ClassBasedShardingAlgorithm算法必传的两个参数
        properties.setProperty("strategy", ClassBasedShardingAlgorithmStrategyType.STANDARD.toString());
        properties.setProperty("algorithmClassName", HalfYearShardingStrategyAlgorithm.class.getName());
        // HalfYearShardingStrategyAlgorithm自定义参数
        properties.setProperty("startYear", "2023");
        return new AlgorithmConfiguration("CLASS_BASED", properties);
    }

    private AlgorithmConfiguration snowflakeAlgorithmConfiguration() {
        Properties properties = new Properties();
        return new AlgorithmConfiguration("SNOWFLAKE", properties);
    }
}
