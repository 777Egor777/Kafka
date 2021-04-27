package ru.yandex.cloud.kafka.train.consumer;

import org.apache.kafka.clients.consumer.*;
import org.slf4j.*;
import ru.yandex.cloud.kafka.train.util.*;
import java.time.Duration;
import java.util.*;

/**
 * DESCRIPTION of the class
 *
 * @author Geraskin Egor(geraskin@yandex-team.ru)
 * @since 4/26/21
 */
public class ConsumerLocal {
    private static final String CONSUMER_PROPERTIES_FILE_NAME = "kafka.consumer.local.properties";
    private static final Logger LOG = LoggerFactory.getLogger(ConsumerLocal.class.getName());

    public static void main(String[] args) {
        Properties props = PropertiesLoader.load(CONSUMER_PROPERTIES_FILE_NAME);
        Consumer<Integer, String> consumer = ConsumerUtility.makeConsumerFromProperties(props);
        String topic = props.getProperty("topic");

        consumer.subscribe(List.of(topic));

        try {
            while (true) {
                ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<Integer, String> record : records) {
                    String msgTemplate = "Kafka message consumed!%nTopic: %s;%nPartition: %d;%nOffset: %d;%nKey:%d;%nValue:%s;%n";
                    String msg = String.format(
                            msgTemplate,
                            record.topic(),
                            record.partition(),
                            record.offset(),
                            record.key(),
                            record.value()
                    );
                    System.out.print(msg);
                }
            }
        } catch (Exception ex) {
            LOG.error("Exception when consume records by KafkaConsumer", ex);
        } finally {
            consumer.close();
        }
    }
}
