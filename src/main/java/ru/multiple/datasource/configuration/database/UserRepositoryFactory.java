package ru.multiple.datasource.configuration.database;

import static ru.multiple.datasource.configuration.database.DatabaseConfigUtil.USER_REPOSITORY_DS1;
import static ru.multiple.datasource.configuration.database.DatabaseConfigUtil.USER_REPOSITORY_DS2;
import static ru.multiple.datasource.configuration.database.DatabaseConfigUtil.USER_REPOSITORY_DS3;
import static ru.multiple.datasource.configuration.database.DatabaseConfigUtil.USER_REPOSITORY_DS4;
import static ru.multiple.datasource.configuration.database.DatabaseConfigUtil.USER_REPOSITORY_DS5;
import static ru.multiple.datasource.constant.DataSource.DS_1;
import static ru.multiple.datasource.constant.DataSource.DS_2;
import static ru.multiple.datasource.constant.DataSource.DS_3;
import static ru.multiple.datasource.constant.DataSource.DS_4;
import static ru.multiple.datasource.constant.DataSource.DS_5;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.RequiredArgsConstructor;
import java.util.Map;

import ru.multiple.datasource.constant.DataSource;
import ru.multiple.datasource.repository.UserRepository;

@Configuration
@RequiredArgsConstructor
public class UserRepositoryFactory {

    @Bean(name = "userRepositoryMap")
    public Map<DataSource, UserRepository> getUserRepositoryMap(
        @Qualifier(USER_REPOSITORY_DS1) UserRepository userRepositoryDs1,
        @Qualifier(USER_REPOSITORY_DS2) UserRepository userRepositoryDs2,
        @Qualifier(USER_REPOSITORY_DS3) UserRepository userRepositoryDs3,
        @Qualifier(USER_REPOSITORY_DS4) UserRepository userRepositoryDs4,
        @Qualifier(USER_REPOSITORY_DS5) UserRepository userRepositoryDs5
    ) {
        return Map.of(
            DS_1, userRepositoryDs1,
            DS_2, userRepositoryDs2,
            DS_3, userRepositoryDs3,
            DS_4, userRepositoryDs4,
            DS_5, userRepositoryDs5
        );
    }
}