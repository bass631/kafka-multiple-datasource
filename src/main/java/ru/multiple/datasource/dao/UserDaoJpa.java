package ru.multiple.datasource.dao;

import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.Map;

import ru.multiple.datasource.constant.DataSource;
import ru.multiple.datasource.exception.RepositoryException;
import ru.multiple.datasource.model.User;
import ru.multiple.datasource.repository.UserRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDaoJpa implements UserDao {

    private final Map<DataSource, UserRepository> userRepositoryMap;

    @Override
    public User saveUser(DataSource dataSource, User user) {
        try {
            return userRepositoryMap.get(dataSource).save(user);
        } catch (Exception e) {
            throw new RepositoryException("Error occurred while saving user", e);
        }
    }

    @Override
    public User findUserById(DataSource dataSource, int id) {
        User user;
        try {
            user = userRepositoryMap.get(dataSource).findUserById(id);
        } catch (Exception e) {
            throw new RepositoryException("Error occurred while finding user", e);
        }
        return user;
    }
}