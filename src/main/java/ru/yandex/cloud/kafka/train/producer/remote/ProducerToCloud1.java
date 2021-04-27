package ru.yandex.cloud.kafka.train.producer.remote;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import ru.yandex.cloud.kafka.train.util.ProducerUtility;
import ru.yandex.cloud.kafka.train.util.PropertiesLoader;

import java.util.Properties;

/**
 * Class for overview
 * Kafka Producer Java API.
 *
 * Using Producer properties
 * from resource file.
 *
 * Kafka cluster is working
 * on Yandex Cloud.
 *
 * @author Geraskin Egor(geraskin@yandex-team.ru)
 * @since 4/26/21
 */
public class ProducerToCloud1 {
    private final static String PROPERTIES_FILE_NAME = "kafka.producer.remote.properties";
    private final static Properties PROPS = PropertiesLoader.load(PROPERTIES_FILE_NAME);
    private final static int NUM_OF_RECORDS = 10;

    public static void main(String[] args) {
        Producer<Integer, String> producer = ProducerUtility.makeProducerFromProperties(PROPS);
        String topic = PROPS.getProperty("topic");
        for (int i = 1; i <= NUM_OF_RECORDS; ++i) {
            ProducerRecord<Integer, String> record = makeRecord(topic, i, "" + i);
            sendMessage(producer, record);
        }
        producer.flush();
        producer.close();
    }

    public static <K, V> ProducerRecord<K, V> makeRecord(String topic, K key, V value) {
        return new ProducerRecord<>(topic, key, value);
    }

    public static <K, V> void sendMessage(Producer<K, V> producer, ProducerRecord<K, V> record) {
        producer.send(record);
    }
}
