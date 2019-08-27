package waitnotify;

import java.util.List;

public class Consumer implements Runnable {
    private List<Integer> integerList;

    public Consumer(List<Integer> integerList) {
        this.integerList = integerList;
    }

    @Override
    public void run() {
        while (integerList.size() > 0) {
            synchronized (integerList) {
                Integer i = integerList.remove(0);
                System.out.println("======consume: " + i);
                if (integerList.isEmpty()) {
                    try {
                        integerList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
