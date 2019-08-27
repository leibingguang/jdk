package waitnotify;

import java.util.List;

public class Producer implements Runnable {
    private List<Integer> integerList;

    public Producer(List<Integer> integerList) {
        this.integerList = integerList;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            i++;
            synchronized (integerList) {

                integerList.add(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("produce: " + i);
                integerList.notify();
            }
        }
    }
}
