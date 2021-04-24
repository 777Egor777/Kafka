import org.apache.kafka.clients.producer.*;

import java.util.Date;
import java.util.Properties;

public class Producer {
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

        try(KafkaProducer<Integer ,String> producer = new KafkaProducer<>(props)) {

            for (int key = 0; key < numOfRecords; key++) {
                String message = String.format("Producer %s send message %s at time %s", clientId, key, System.currentTimeMillis());
                ProducerRecord<Integer, String> record = new ProducerRecord<>(topic, key, message);
                producer.send(record);
                System.out.println("Message " + message + " was just produced");
            }

        }

    }
}
