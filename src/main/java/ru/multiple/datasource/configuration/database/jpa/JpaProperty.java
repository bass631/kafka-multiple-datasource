package ru.multiple.datasource.configuration.database.jpa;

import static ru.multiple.datasource.configuration.database.DatabaseConfigUtil.JPA_PROPERTY_PREFIX;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = JPA_PROPERTY_PREFIX)
@Data
public class JpaProperty {

}