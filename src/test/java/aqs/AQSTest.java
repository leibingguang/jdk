package aqs;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class AQSTest {
    @Test
    public void testCondition() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread t1 = new Thread(new MyThread(countDownLatch));
        Thread t2 = new Thread(new MyThread(countDownLatch));
        Thread t3 = new Thread(new MyThread(countDownLatch));
        Thread t4 = new Thread(new MyThread(countDownLatch));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        Thread.sleep(2000);
        countDownLatch.countDown();
        Thread.sleep(1999999);

    }
}

class MyThread implements Runnable {
    private CountDownLatch countDownLatch;

    public MyThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("before await!!");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end await!!!");
    }
}
