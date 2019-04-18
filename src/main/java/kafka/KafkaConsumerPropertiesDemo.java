package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class KafkaConsumerPropertiesDemo {
    public static void main(String[] args) throws Exception{
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "10.211.55.3:9092");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("group.id", "group1");
        //ƫ������Ч������´�ʲô�ط���ʼ��ȡ��earliest����ʼƫ���� latest�����µļ�¼��ʼ��ȡ
        properties.setProperty("auto.offset.reset", "earliest");
        //�������������û����ô�broker��ȡ���ݵ�����������fetch.min.bytes��ʾ������������ȡ���ֽ���Ҫ�ﵽ�����ֽ�ʱ�ŷ������ݡ�
        //fetch.max.wait.ms��ʾ�������ָ����ʱ�仹û���㹻�����ݣ��򷵻����е�����
//        properties.setProperty("fetch.min.bytes", "100");
        properties.setProperty("fetch.max.wait.ms", "10000");
        //�ò�����ʾ������session��Чʱ��Ϊ3s�����������Чʱ�䣬consumerû�з���������broker����ô�жϸ�consumer�Ѿ�������broker�پ���
        properties.setProperty("session.timeout.ms", "6000");//��СֵΪ6��
        //��������Ƶ��,�����session.timeout.msС
        properties.setProperty("heartbeat.interval.ms", "1000");
        //�����Ƿ��Զ��ύƫ�������������Ϊfalse������Ҫ�ڴ������ֹ��ύ��consumer.commitAsync()��consumer.commitSync()���������첽��ͬ���ύ
        properties.setProperty("enable.auto.commit", "false");
        //���enable.auto.commit����Ϊtrue�����������Ч����ʾ�Զ��ύ���ʱ��
        properties.setProperty("auto.commit.interval.ms", "1000");
        //�����ʾ��ֻ��������ʾ�ͻ��ˣ�����������
        properties.setProperty("client.id", "xxx");
        //�ò�����0.10�汾�в���Ч��0.9�汾����Ч
        properties.setProperty("max.poll.records", "10");

        KafkaConsumer consumer = new KafkaConsumer(properties);
        List topicList = new ArrayList<String>();
        topicList.add(KafkaConstants.TOPIC);
        consumer.subscribe(topicList);

        try {
            while (true) {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(100);

                Thread.sleep(3000);
                for (ConsumerRecord consumerRecord : consumerRecords) {
                    System.out.println(consumerRecord);

                }

            }
        }finally {
            consumer.close();
        }

    }
}
