package ru.multiple.datasource.mapper;

import java.util.Date;

import ru.multiple.datasource.dto.KafkaListenerMessage;
import ru.multiple.datasource.dto.KafkaSenderMessage;
import ru.multiple.datasource.model.User;

public final class UserMapper {
    private UserMapper() {}

    public static User toUserEntity(KafkaListenerMessage message){
        return User.builder()
            .id(Integer.parseInt(message.getId()))
            .firstName(message.getFirstName())
            .lastName(message.getLastName())
            .email(message.getEmail())
            .createDate(new Date())
            .build();
    }

    public static KafkaSenderMessage toKafkaSenderMessage(User user) {
        return KafkaSenderMessage.builder()
            .email(user.getEmail())
            .createDate(user.getCreateDate())
            .build();
    }
}