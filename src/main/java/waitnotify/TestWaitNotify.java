package waitnotify;

import java.util.ArrayList;
import java.util.List;

public class TestWaitNotify {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        new Thread(new Producer(list)).start();
        new Thread(new Consumer(list)).start();
    }
}
