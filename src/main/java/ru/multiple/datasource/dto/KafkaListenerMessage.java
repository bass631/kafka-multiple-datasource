package ru.multiple.datasource.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@ToString
@EqualsAndHashCode
@Builder
@Jacksonized
public class KafkaListenerMessage {

    private String action;
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}

