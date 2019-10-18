package zookeeper.source;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import zookeeper.ZookeeperConstants;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 创建简单的zookeeper客户端
 */
public class Zookeeper_Constructor_Usage_With_SID_PASSWD implements Watcher {
    static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {

        ZooKeeper zooKeeper = new ZooKeeper("10.211.95.114:6830",
                5000,
                new Zookeeper_Constructor_Usage_With_SID_PASSWD());
        System.out.println(zooKeeper.getState());
        countDownLatch.await();
        long sessionId = zooKeeper.getSessionId();
        byte[] sessionPasswd = zooKeeper.getSessionPasswd();
        zooKeeper = new ZooKeeper(ZookeeperConstants.CONNECTION_STRING,
                ZookeeperConstants.CONNECTION_TIMEOUT,
                new Zookeeper_Constructor_Usage_With_SID_PASSWD(),
                12L,
                "test".getBytes());
        zooKeeper = new ZooKeeper(ZookeeperConstants.CONNECTION_STRING,
                ZookeeperConstants.CONNECTION_TIMEOUT,
                new Zookeeper_Constructor_Usage_With_SID_PASSWD(),
                sessionId,
                sessionPasswd);
        Thread.sleep(Integer.MAX_VALUE);
//        System.out.println("Zookeeper session established");
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("Receive watched event: " + event);
        if (KeeperState.SyncConnected == event.getState()) {
            countDownLatch.countDown();
        }

    }
}
