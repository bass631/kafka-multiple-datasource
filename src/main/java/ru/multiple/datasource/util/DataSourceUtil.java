package ru.multiple.datasource.util;

import java.util.EnumSet;
import java.util.Set;

import ru.multiple.datasource.constant.DataSource;

public final class DataSourceUtil {

    private DataSourceUtil() {
    }

    public static Set<DataSource> getDataSourceSet() {
        return EnumSet.allOf(DataSource.class);
    }
}