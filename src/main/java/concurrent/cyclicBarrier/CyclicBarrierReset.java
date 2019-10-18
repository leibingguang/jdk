package concurrent.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierReset {
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
        System.out.println("before interrupted " + c.isBroken());
        thread.interrupt();
        c.reset();

       new Thread(()-> {
           try {
               Thread.currentThread().interrupt();
               c.await();
           } catch (InterruptedException e) {
               e.printStackTrace();
               System.out.println("after interrupted " + c.isBroken());

           } catch (BrokenBarrierException e) {
               e.printStackTrace();
               System.out.println("after interrupted " + c.isBroken());

           }
       }).start();
        new Thread(()-> {
            try {
                c.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("after interrupted " + c.isBroken());

            } catch (BrokenBarrierException e) {
                e.printStackTrace();
                System.out.println("after interrupted " + c.isBroken());

            }
        }).start();
        Thread.sleep(1000);
        System.out.println("after interrupted " + c.isBroken());

    }
}
