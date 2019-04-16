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
        properties.setProperty("bootstrap.servers", "10.211.95.103:5811");
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("linger.ms", "1");
        properties.setProperty("retries", "1");
        properties.setProperty("timeout.ms", "2000");
//        properties.setProperty("request.timeout.ms", "1000");
//        properties.setProperty("metadata.fetch..timeout.ms", "1000");
        System.out.println(null == (Integer.valueOf("0"))? "" : (Integer.valueOf("0").toString().trim()));

        ProducerRecord<String, String> record = new ProducerRecord<String, String>(TEST_TOPIC, "test","test");
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer(properties);

        try {
            RecordMetadata recordMetadata = kafkaProducer.send(record).get();
            System.out.println(recordMetadata);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

class DemoProducerCallback implements Callback {
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        System.out.println("callback-----" + recordMetadata);
        if (e != null) {
            e.printStackTrace();
        }
    }
}