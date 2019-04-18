package kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class ProducerCallback implements Callback {

    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        System.out.println("callback-----" + recordMetadata);
        if (e != null) {
            e.printStackTrace();
        }
    }
}
