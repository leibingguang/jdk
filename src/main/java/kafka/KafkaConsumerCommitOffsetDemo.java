package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.*;

public class KafkaConsumerCommitOffsetDemo {
    private static Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
    private static int count = 0;

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "10.211.55.3:9092");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("group.id", "group1");
        properties.setProperty("enable.auto.commit", "false");

        KafkaConsumer consumer = new KafkaConsumer(properties);
        List topicList = new ArrayList<String>();
        topicList.add(KafkaConstants.TOPIC);
        consumer.subscribe(topicList);

        try {
            while (true) {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(100);

                for (ConsumerRecord consumerRecord : consumerRecords) {
                    System.out.println(consumerRecord);

                    TopicPartition topicPartition = new TopicPartition(KafkaConstants.TOPIC, consumerRecord.partition());
                    OffsetAndMetadata offsetAndMetadata = new OffsetAndMetadata(consumerRecord.offset(), "no metadata");
                    offsets.put(topicPartition, offsetAndMetadata);
                    //提交特定的偏移量
                    consumer.commitAsync(offsets, (metadataMap, e) -> {
                        if (e != null)
                            System.out.println("async commit error, metadataMap = " + metadataMap + "exception = " + e);
                    });
                }
                //异步提交
                consumer.commitAsync((metadataMap, e) -> {
                    if (e != null)
                        System.out.println("async commit error, metadataMap = " + metadataMap + "exception = " + e);
                });
            }
        } finally {
            //同步提交
            try {
                consumer.commitSync();
            } finally {
                consumer.close();
            }
        }
    }
}
