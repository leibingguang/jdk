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
     * �پ��⿪ʼ֮ǰ��������ֹͣ��ȡ��Ϣ֮��ᱻ����
     * �ύ����������ƫ�������Ա�������ȡ�������ݵ�ʱ��֪���Ѿ������������Ϣ����Щ
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
     * ���·��������������߿�ʼ����Ϣ֮ǰ����ø÷���
     * �����ߴӷ����л�ȡ�ϴδ����ƫ�������Ա��֪�����￪ʼ��ȡ
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
