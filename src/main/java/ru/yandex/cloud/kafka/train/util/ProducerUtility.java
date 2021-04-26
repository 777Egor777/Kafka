package ru.yandex.cloud.kafka.train.util;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;

/**
 * Util class for Producer.
 *
 * @author Geraskin Egor(geraskin@yandex-team.ru)
 * @since 4/25/21
 */
public class ProducerUtility {
    public static <T1, T2>  Producer<T1, T2> makeProducerFromProperties(Properties props) {
        return new KafkaProducer<>(props);
    }
}
