package ru.multiple.datasource.configuration.database.jpa;

import static ru.multiple.datasource.configuration.database.DatabaseConfigUtil.JPA_PROPERTY;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.RequiredArgsConstructor;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class JpaCustomConfig {

    private final JpaProperty jpaProperty;

    @Bean(JPA_PROPERTY)
    public Properties getProperties() {
        return new Properties();
    }
}