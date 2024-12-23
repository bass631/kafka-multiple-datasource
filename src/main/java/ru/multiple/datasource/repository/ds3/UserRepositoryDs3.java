package ru.multiple.datasource.repository.ds3;

import static ru.multiple.datasource.configuration.database.DatabaseConfigUtil.USER_REPOSITORY_DS3;

import org.springframework.stereotype.Repository;

import ru.multiple.datasource.repository.UserRepository;

@Repository(USER_REPOSITORY_DS3)
public interface UserRepositoryDs3 extends UserRepository {

}