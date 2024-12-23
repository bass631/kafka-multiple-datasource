package ru.multiple.datasource.configuration.database;

import static ru.multiple.datasource.configuration.database.DatabaseConfigUtil.DATABASE_PROPERTY_DS5;
import static ru.multiple.datasource.configuration.database.DatabaseConfigUtil.DATABASE_PROPERTY_PREFIX_DS5;
import static ru.multiple.datasource.configuration.database.DatabaseConfigUtil.DATA_SOURCE_DS5;
import static ru.multiple.datasource.configuration.database.DatabaseConfigUtil.ENTITY_MANAGER_FACTORY_DS5;
import static ru.multiple.datasource.configuration.database.DatabaseConfigUtil.ENTITY_PACKAGE_DS5;
import static ru.multiple.datasource.configuration.database.DatabaseConfigUtil.HIKARI_PROPERTY_PREFIX;
import static ru.multiple.datasource.configuration.database.DatabaseConfigUtil.JPA_PROPERTY;
import static ru.multiple.datasource.configuration.database.DatabaseConfigUtil.JPA_REPOSITORY_PACKAGE_DS5;
import static ru.multiple.datasource.configuration.database.DatabaseConfigUtil.TRANSACTION_MANAGER_DS5;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.Objects;
import java.util.Properties;
import javax.sql.DataSource;


@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = JPA_REPOSITORY_PACKAGE_DS5,
    entityManagerFactoryRef = ENTITY_MANAGER_FACTORY_DS5,
    transactionManagerRef = TRANSACTION_MANAGER_DS5
)
public class DatabaseConfigDs5 {

    @Bean(DATABASE_PROPERTY_DS5)
    @ConfigurationProperties(prefix = DATABASE_PROPERTY_PREFIX_DS5)
    public DataSourceProperties appDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(DATA_SOURCE_DS5)
    @ConfigurationProperties(HIKARI_PROPERTY_PREFIX)
    public DataSource appDataSource(
    ) {
        return appDataSourceProperties()
            .initializeDataSourceBuilder()
            .build();

    }

    @Bean(ENTITY_MANAGER_FACTORY_DS5)
    public LocalContainerEntityManagerFactoryBean appEntityManager(
        @Qualifier(DATA_SOURCE_DS5) DataSource dataSource,
        @Qualifier(JPA_PROPERTY) Properties jpaProperties
    ) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setJpaProperties(jpaProperties);
        em.setPackagesToScan(ENTITY_PACKAGE_DS5);
        em.setPersistenceUnitName(ENTITY_MANAGER_FACTORY_DS5);
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }

    @Bean(TRANSACTION_MANAGER_DS5)
    public PlatformTransactionManager sqlSessionTemplate(
        @Qualifier(ENTITY_MANAGER_FACTORY_DS5) LocalContainerEntityManagerFactoryBean entityManager
    ) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManager.getObject()));
    }
}