package cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest2 {
    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                }
                catch (BrokenBarrierException e)
                {
                    e.printStackTrace();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        Thread.sleep(1000);
        c.reset();
        thread.interrupt();
        try {
            c.await();
        } catch (Exception e) {
            System.out.println(c.isBroken());
        }
    }
}