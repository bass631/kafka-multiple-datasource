package ru.multiple.datasource.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.Objects;

import ru.multiple.datasource.constant.DataSource;
import ru.multiple.datasource.dao.UserDao;
import ru.multiple.datasource.dto.KafkaListenerMessage;
import ru.multiple.datasource.kafka.KafkaSender;
import ru.multiple.datasource.mapper.UserMapper;
import ru.multiple.datasource.model.User;
import ru.multiple.datasource.util.ActionTypeUtil;
import ru.multiple.datasource.util.DataSourceSelectorUtil;
import ru.multiple.datasource.util.DataSourceUtil;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppService {

    private final UserDao userDao;
    private final KafkaSender kafkaSender;

    public void proceed(KafkaListenerMessage message) {
        log.info("Start processing the received message");

        try {
            switch (message.getAction().toUpperCase()) {
                case ActionTypeUtil.CREATE -> saveUser(message);
                case ActionTypeUtil.FIND -> findUser(message);
                default -> log.warn("Not supported action type: '{}'", message.getAction());
            }
        } catch (Exception e) {
            log.error("Unexpected error occurred! {}: {}", e.getClass(), e.getMessage());
        } finally {
            log.info("End processing the received message");
        }
    }


    private void saveUser(KafkaListenerMessage message) {
        var user = UserMapper.toUserEntity(message);
        var dataSource = DataSourceSelectorUtil.getDataSource(user.getId());
        var savedUser = userDao.saveUser(dataSource, user);
        log.info("User id: '{}' saved success: {}", savedUser.getId(), savedUser);
    }

    private void findUser(KafkaListenerMessage message) {
        var user = UserMapper.toUserEntity(message);
        var id = user.getId();
        var dataSource = DataSourceSelectorUtil.getDataSource(id);
        var savedUser = findUserInDs(dataSource, id);
        if (savedUser != null) {
            log.info("User id: '{}' found success: {}", id, savedUser);
            var kafkaSenderMessage = UserMapper.toKafkaSenderMessage(savedUser);
            kafkaSender.sendKafka(kafkaSenderMessage);
        } else {
            log.warn("User id: '{}' not found", id);
        }
    }


    private User findUserInDs(DataSource dataSourceFirst, int id) {
        User user = userDao.findUserById(dataSourceFirst, id);
        if (user == null) {
            user = DataSourceUtil.getDataSourceSet().stream()
                .filter(dataSource -> !dataSource.equals(dataSourceFirst))
                .peek(dataSource -> log.debug("Check user id: '{}' in {}", id, dataSource.getValue()))
                .map(dataSource -> userDao.findUserById(dataSource, id))
                .filter(Objects::nonNull)
                .map(userFound -> {
                    log.debug("User id: '{}' found", id);
                    return userFound;
                })
                .findFirst()
                .orElse(null);
        }
        return user;
    }
}