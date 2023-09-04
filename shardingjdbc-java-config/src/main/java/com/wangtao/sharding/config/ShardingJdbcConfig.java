package com.wangtao.sharding.config;

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

    private ModeConfiguration modeConfiguration() {
        StandalonePersistRepositoryConfiguration persistRepositoryConfiguration =
                new StandalonePersistRepositoryConfiguration("JDBC", new Properties());
        return new ModeConfiguration("Standalone", persistRepositoryConfiguration);
    }

    private Map<String, DataSource> dataSourceMap() {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        org.apache.tomcat.jdbc.pool.DataSource dataSource1 = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://localhost:3306/tradedb");
        dataSource1.setUsername("root");
        dataSource1.setPassword("123456");
        dataSourceMap.put("tradedb", dataSource1);
        return dataSourceMap;
    }

    private Collection<RuleConfiguration> ruleConfigs() {
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        shardingRuleConfiguration.getTables().add(getTrTradeInfoTableRuleConfiguration());
        shardingRuleConfiguration.getShardingAlgorithms().put("dateBased", classBasedAlgorithmConfiguration());
        shardingRuleConfiguration.getKeyGenerators().put("snowflake", snowflakeAlgorithmConfiguration());

        // 广播表, 新增修改时每个库都会执行, 一般都是公共表, 且不分表, 每个库的数据和表结构一致
        BroadcastRuleConfiguration broadcastRuleConfiguration = new BroadcastRuleConfiguration(List.of("user"));
        return List.of(shardingRuleConfiguration, broadcastRuleConfiguration);
    }

    private ShardingTableRuleConfiguration getTrTradeInfoTableRuleConfiguration() {
        ShardingTableRuleConfiguration tableRuleConfiguration =
                new ShardingTableRuleConfiguration("tr_trade_info", "tradedb.tr_trade_info_${0..3}");
        // 库分片策略
        tableRuleConfiguration.setDatabaseShardingStrategy(null);
        // 表分片策越
        tableRuleConfiguration.setTableShardingStrategy(new StandardShardingStrategyConfiguration("txn_dt", "dateBased"));
        // 主键生成策略
        tableRuleConfiguration.setKeyGenerateStrategy(new KeyGenerateStrategyConfiguration("txn_id", "snowflake"));
        return tableRuleConfiguration;
    }

    private AlgorithmConfiguration classBasedAlgorithmConfiguration() {
        Properties properties = new Properties();
        // ClassBasedShardingAlgorithm算法必传的两个参数
        properties.put("strategy", ClassBasedShardingAlgorithmStrategyType.STANDARD.toString());
        properties.put("algorithmClassName", HalfYearShardingStrategyAlgorithm.class.getName());
        // HalfYearShardingStrategyAlgorithm自定义参数
        properties.put("startYear", 2023);
        return new AlgorithmConfiguration("CLASS_BASED", properties);
    }

    private AlgorithmConfiguration snowflakeAlgorithmConfiguration() {
        Properties properties = new Properties();
        return new AlgorithmConfiguration("SNOWFLAKE", properties);
    }
}
