package ru.multiple.datasource.configuration.kafka;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import lombok.RequiredArgsConstructor;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableKafka
@RequiredArgsConstructor
public class KafkaProducerConfiguration {

    private final KafkaProperties kafkaProperties;

    private ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getProducer().getBootstrapServer());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        if (kafkaProperties.getSsl().getProducer().isEnabled()) {
            props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SSL");
            props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, kafkaProperties.getSsl().getProducer().getTrustStorePath());
            props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, kafkaProperties.getSsl().getProducer().getTrustStorePassword());
            props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, kafkaProperties.getSsl().getProducer().getKeyStorePassword());
            props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, kafkaProperties.getSsl().getProducer().getKeyStorePath());
        }
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}