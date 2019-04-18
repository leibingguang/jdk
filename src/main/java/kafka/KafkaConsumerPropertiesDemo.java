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
        //偏移量无效的情况下从什么地方开始读取，earliest：起始偏移量 latest：最新的记录开始读取
        properties.setProperty("auto.offset.reset", "earliest");
        //以下两个参数用户设置从broker获取数据的限制条件：fetch.min.bytes表示服务器可以拉取的字节数要达到多少字节时才返回数据。
        //fetch.max.wait.ms表示如果到达指定的时间还没有足够的数据，则返回已有的数据
//        properties.setProperty("fetch.min.bytes", "100");
        properties.setProperty("fetch.max.wait.ms", "10000");
        //该参数表示服务器session有效时间为3s，如果超过有效时间，consumer没有发送心跳给broker，那么判断该consumer已经死亡，broker再均衡
        properties.setProperty("session.timeout.ms", "6000");//最小值为6秒
        //发送心跳频率,必须比session.timeout.ms小
        properties.setProperty("heartbeat.interval.ms", "1000");
        //设置是否自动提交偏移量，如果设置为false，则需要在代码中手工提交，consumer.commitAsync()和consumer.commitSync()两个方法异步和同步提交
        properties.setProperty("enable.auto.commit", "false");
        //如果enable.auto.commit设置为true，则该设置有效，表示自动提交间隔时间
        properties.setProperty("auto.commit.interval.ms", "1000");
        //任意表示，只是用来表示客户端，无其他作用
        properties.setProperty("client.id", "xxx");
        //该参数在0.10版本中才生效，0.9版本不生效
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
