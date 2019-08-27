package future;

import sun.plugin2.jvm.CircularByteBuffer.Streamer;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;

public class FutureTest {
    public static void main(String[] args) throws Exception {
        Callable callable = new CallableThread();
        FutureTask<String> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        Thread.sleep(1000);
        System.out.println("before get, isDone: " + futureTask.isDone());
//        System.out.println("cancled: " + futureTask.cancel(false));
        new Thread(new WaitThread(futureTask)).start();
        new Thread(new WaitThread(futureTask)).start();
//        String s = futureTask.get();
        System.out.println("after get isDone: " + futureTask.isDone());
//        System.out.println(s);
    }
}

class CallableThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(1990009);
        return "string";
    }
}

class WaitThread implements Runnable
{
    private FutureTask<String> futureTask;

    public WaitThread(FutureTask<String> futureTask) {
        this.futureTask = futureTask;
    }

    @Override
    public void run() {
        try {
            System.out.println(futureTask.get());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
