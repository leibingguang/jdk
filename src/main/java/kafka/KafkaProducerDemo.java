package kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class KafkaProducerDemo {
    private static final String TEST_TOPIC = "test";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "10.211.55.3:9092");
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("linger.ms", "5000");
        properties.setProperty("batch.size", "1000");
        properties.setProperty("retries", "1");
        properties.setProperty("timeout.ms", "2000");
//        properties.setProperty("request.timeout.ms", "1000");
//        properties.setProperty("metadata.fetch..timeout.ms", "1000");

        ProducerRecord<String, String> record = new ProducerRecord<String, String>(TEST_TOPIC, "test","test");
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer(properties);

        try {
            RecordMetadata recordMetadata = kafkaProducer.send(record).get();
            RecordMetadata recordMetadata1 = kafkaProducer.send(record, new ProducerCallback()).get();

            System.out.println(recordMetadata);
            System.out.println(recordMetadata1);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
