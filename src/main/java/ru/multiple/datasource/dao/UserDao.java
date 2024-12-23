package ru.multiple.datasource.dao;

import ru.multiple.datasource.constant.DataSource;
import ru.multiple.datasource.model.User;

public interface UserDao {

    User saveUser(DataSource dataSource, User user);
    User findUserById(DataSource dataSource, int id);
}
