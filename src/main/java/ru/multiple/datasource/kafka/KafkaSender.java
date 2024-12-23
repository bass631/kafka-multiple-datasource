package ru.multiple.datasource.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import ru.multiple.datasource.configuration.kafka.KafkaProperties;
import ru.multiple.datasource.dto.KafkaSenderMessage;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaSender {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaProperties properties;

    public void sendKafka(KafkaSenderMessage message) {
        kafkaTemplate.send(properties.getProducer().getTopic(), message);
        log.info("Kafka message sent to topic: '{}'", properties.getProducer().getTopic());
    }
}