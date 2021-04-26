package ru.yandex.cloud.kafka.train.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;


/**
 * Class for overview Producer class.
 * Using hardcoded properties
 * and Kafka on local machine.
 *
 * @since 23.04.2021
 * @author Geraskin Egor(geraskin@yandex-team.ru)
 */
public class ProducerTest1 {

    public static void main(String[] args) {
        System.out.println("Let's get started with Kafka API");

        String clientId = "producer_1(Author: Egor_Geraskin)";

        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("acks", "all");
        props.put("client.id", clientId);

        int numOfRecords = 100;
        String topic = "strings";

        try (KafkaProducer<Integer, String> producer = new KafkaProducer<>(props)) {

            for (int key = 0; key < numOfRecords; key++) {
                String message = String.format("ru.yandex.cloud.kafka.train.producer.Producer %s send message %s at time %s", clientId, key, System.currentTimeMillis());
                ProducerRecord<Integer, String> record = new ProducerRecord<>(topic, key, message);
                producer.send(record);
                System.out.println("Message " + message + " was just produced");
            }

        }

    }
}
