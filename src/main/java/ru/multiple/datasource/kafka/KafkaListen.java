package ru.multiple.datasource.kafka;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.multiple.datasource.configuration.kafka.KafkaProperties;
import ru.multiple.datasource.dto.KafkaListenerMessage;
import ru.multiple.datasource.service.AppService;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "kafka.listener.enabled", matchIfMissing = true)
public class KafkaListen {

    private final ObjectMapper mapper = new ObjectMapper();
    private final KafkaProperties properties;
    private final AppService appService;

    @KafkaListener(
        topics = "${kafka.consumer.topic}",
        groupId = "${kafka.consumer.group-id}"
    )
    public void onMessage(String message) {
        log.info("Received Kafka message from topic: '{}'", properties.getConsumer().getTopic());
        try {
            KafkaListenerMessage kafkaMessage = mapper.readValue(message, KafkaListenerMessage.class);
            log.debug("Kafka message: {}", kafkaMessage);
            appService.proceed(kafkaMessage);
        } catch (Exception e) {
            log.error("Error occurred while processing message! {}: {}", e.getClass(), e.getMessage());
        }
    }
}