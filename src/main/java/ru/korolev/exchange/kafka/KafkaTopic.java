package ru.korolev.exchange.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
/**
 * Class for generating a new topic for kafka.
 *
 * @author Nikita Korolev
 * @version 1.0
 */
public class KafkaTopic {
    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("topic1").build();
    }
}
