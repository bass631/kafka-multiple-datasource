package ru.multiple.datasource.util;

import static ru.multiple.datasource.constant.DataSource.DS_1;
import static ru.multiple.datasource.constant.DataSource.DS_2;
import static ru.multiple.datasource.constant.DataSource.DS_3;
import static ru.multiple.datasource.constant.DataSource.DS_4;
import static ru.multiple.datasource.constant.DataSource.DS_5;

import lombok.extern.slf4j.Slf4j;
import java.util.Map;
import java.util.Map.Entry;

import ru.multiple.datasource.constant.DataSource;

@Slf4j
public final class DataSourceSelectorUtil {

    private DataSourceSelectorUtil() {
    }

    private static final Map<ValueConditionPredicate, DataSource> dataSourceMap;

    static {
        dataSourceMap = Map.of(
            ConditionEvaluator::checkConditionDs1, DS_1,
            ConditionEvaluator::checkConditionDs2, DS_2,
            ConditionEvaluator::checkConditionDs3, DS_3,
            ConditionEvaluator::checkConditionDs4, DS_4,
            ConditionEvaluator::checkConditionDs5, DS_5
        );
    }

    public static DataSource getDataSource(int id) {
        DataSource dataSource = DS_1;
        for (Entry<ValueConditionPredicate, DataSource> entry : dataSourceMap.entrySet()) {
            if (entry.getKey().matches(id)) {
                dataSource = entry.getValue();
            }
        }
        log.debug("For user id: '{}' {} is set", id, dataSource.getValue());
        return dataSource;
    }
}