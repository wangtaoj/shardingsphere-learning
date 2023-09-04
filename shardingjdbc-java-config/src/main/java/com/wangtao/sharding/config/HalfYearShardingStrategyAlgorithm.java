package com.wangtao.sharding.config;

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * @author wangtao
 * Created at 2023/9/3 15:59
 */
public class HalfYearShardingStrategyAlgorithm implements StandardShardingAlgorithm<LocalDate> {

    private static final String START_YEAR_KEY = "startYear";

    private int startYear;

    @Override
    public void init(Properties props) {
        if (props.containsKey(START_YEAR_KEY)) {
            this.startYear = Integer.parseInt(props.getProperty(START_YEAR_KEY));
        } else {
            this.startYear = LocalDate.now().getYear();
        }

    }

    /**
     * 每半年分一次
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<LocalDate> shardingValue) {
        LocalDate txnDt = shardingValue.getValue();
        int sharding = getShardingCount(txnDt);
        return shardingValue.getDataNodeInfo().getPrefix() + sharding;
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<LocalDate> shardingValue) {
        LocalDate beginTxnDt = shardingValue.getValueRange().lowerEndpoint();
        LocalDate endTxnDt = shardingValue.getValueRange().upperEndpoint();
        Set<String> results = new HashSet<>();
        int startIndex = getShardingCount(beginTxnDt);
        int endIndex = getShardingCount(endTxnDt);
        for (int i = startIndex; i <=endIndex; i++) {
            results.add(shardingValue.getDataNodeInfo().getPrefix() + i);
        }
        return results;
    }

    private int getShardingCount(LocalDate txnDt) {
        int year = txnDt.getYear();
        int month = txnDt.getMonthValue();
        return (year - startYear) * 2 + (month > 6 ? 1 : 0);
    }

    @Override
    public String getType() {
        return "HALF_YEAR";
    }
}
