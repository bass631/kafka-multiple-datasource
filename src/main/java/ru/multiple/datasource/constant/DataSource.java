package ru.multiple.datasource.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public enum DataSource {
    DS_1("data_source_1"),
    DS_2("data_source_2"),
    DS_3("data_source_3"),
    DS_4("data_source_4"),
    DS_5("data_source_5");

    private final String value;
}
