package concurrent.countdownlatch;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    CountDownLatch countDownLatch = new CountDownLatch(10);

    @Test
    public void test() throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(1000);
        for (int i = 0; i < 10; i++) {
            new Thread(countDownLatch::countDown).start();
        }
        countDownLatch.await();
        System.out.println("notified");
        Thread.sleep(1000000000);
    }
}
