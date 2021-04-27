package ru.yandex.cloud.kafka.train.util;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;

/**
 * DESCRIPTION of the class
 *
 * @author Geraskin Egor(geraskin@yandex-team.ru)
 * @since 4/27/21
 */
public class ConsumerUtility {
    public static <T1, T2> Consumer<T1, T2> makeConsumerFromProperties(Properties props) {
        return new KafkaConsumer<>(props);
    }
}
