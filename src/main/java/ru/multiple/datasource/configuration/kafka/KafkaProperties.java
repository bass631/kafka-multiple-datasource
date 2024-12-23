package ru.multiple.datasource.configuration.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "kafka")
@Data
public class KafkaProperties {

    private Listener listener;
    private Consumer consumer;
    private Producer producer;
    private Ssl ssl;

    @Data
    public static class Listener {

        private boolean enabled;
    }

    @Data
    public static class Consumer {

        private String bootstrapServer;
        private String groupId;
        private String topic;
    }

    @Data
    public static class Producer {

        private String bootstrapServer;
        private String topic;
    }

    @Data
    public static class Ssl {

        private SslConsumer consumer;
        private SslProducer producer;
    }

    @Data
    public static class SslConsumer {

        private boolean enabled;
        private String trustStorePath;
        private String trustStorePassword;
        private String keyStorePath;
        private String keyStorePassword;
    }

    @Data
    public static class SslProducer {

        private boolean enabled;
        private String trustStorePath;
        private String trustStorePassword;
        private String keyStorePath;
        private String keyStorePassword;
    }
}