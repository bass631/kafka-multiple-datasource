package ru.multiple.datasource.repository.ds2;

import static ru.multiple.datasource.configuration.database.DatabaseConfigUtil.USER_REPOSITORY_DS2;

import org.springframework.stereotype.Repository;

import ru.multiple.datasource.repository.UserRepository;

@Repository(USER_REPOSITORY_DS2)
public interface UserRepositoryDs2 extends UserRepository {

}