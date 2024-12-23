package ru.multiple.datasource.repository.ds1;


import static ru.multiple.datasource.configuration.database.DatabaseConfigUtil.USER_REPOSITORY_DS1;

import org.springframework.stereotype.Repository;

import ru.multiple.datasource.repository.UserRepository;

@Repository(USER_REPOSITORY_DS1)
public interface UserRepositoryDs1 extends UserRepository {

}