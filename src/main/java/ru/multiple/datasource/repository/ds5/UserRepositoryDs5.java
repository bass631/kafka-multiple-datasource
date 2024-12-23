package ru.multiple.datasource.repository.ds5;

import static ru.multiple.datasource.configuration.database.DatabaseConfigUtil.USER_REPOSITORY_DS5;

import org.springframework.stereotype.Repository;

import ru.multiple.datasource.repository.UserRepository;

@Repository(USER_REPOSITORY_DS5)
public interface UserRepositoryDs5 extends UserRepository {

}