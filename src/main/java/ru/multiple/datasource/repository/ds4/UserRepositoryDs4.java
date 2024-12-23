package ru.multiple.datasource.repository.ds4;

import static ru.multiple.datasource.configuration.database.DatabaseConfigUtil.USER_REPOSITORY_DS4;

import org.springframework.stereotype.Repository;

import ru.multiple.datasource.repository.UserRepository;

@Repository(USER_REPOSITORY_DS4)
public interface UserRepositoryDs4 extends UserRepository {

}