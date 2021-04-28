package ru.yandex.cloud.kafka.train.consumer.remote;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.cloud.kafka.train.util.ConsumerUtility;
import ru.yandex.cloud.kafka.train.util.PropertiesLoader;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

/**
 * DESCRIPTION of the class
 *
 * @author Geraskin Egor(geraskin@yandex-team.ru)
 * @since 4/27/21
 */
public class ConsumerRemote {
    private static final String CONSUMER_PROPERTIES_FILE_NAME = "kafka.consumer.remote.properties";
    private static final Logger LOG = LoggerFactory.getLogger(ConsumerRemote.class.getName());

    public static void main(String[] args) {
        Properties props = PropertiesLoader.load(CONSUMER_PROPERTIES_FILE_NAME);
        String topic = props.getProperty("topic");
        Consumer<String, String> consumer = ConsumerUtility.makeConsumerFromProperties(props);


        try {
            consumer.subscribe(List.of(topic));
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    String msgTemplate = "Topic: %s; Partition: %d; Offset: %d; Key:%s; Value:%s;%n";
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
