package kafka;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;
import java.util.Map;

public class RebalanceCallBack implements ConsumerRebalanceListener {

    private KafkaConsumer<String, String> kafkaConsumer;
    private Map<TopicPartition, OffsetAndMetadata> currentOffsets;

    public RebalanceCallBack(KafkaConsumer<String, String> kafkaConsumer, Map<TopicPartition, OffsetAndMetadata> currentOffsets) {
        this.kafkaConsumer = kafkaConsumer;
        this.currentOffsets = currentOffsets;
    }

    /**
     * 再均衡开始之前和消费者停止读取消息之后会被调用
     * 提交最近处理过的偏移量，以便重新拉取分区数据的时候知道已经被处理过的消息有哪些
     * @param collection
     */
    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> collection) {
        Map<TopicPartition, OffsetAndMetadata> offsets = null;
        for (TopicPartition topicPartition : collection) {
            System.out.println(currentOffsets);
            kafkaConsumer.commitSync(currentOffsets);
        }
    }

    /**
     * 重新分配分区后和消费者开始读消息之前会调用该方法
     * 消费者从分区中获取上次处理的偏移量，以便得知从哪里开始拉取
     * @param collection
     */
    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> collection) {

        for(TopicPartition topicPartition: collection)
        {
            kafkaConsumer.seek(topicPartition, 1);
        }
    }
}
