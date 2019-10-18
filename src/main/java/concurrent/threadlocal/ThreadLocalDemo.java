package concurrent.threadlocal;

import org.junit.Test;

public class ThreadLocalDemo {
    ThreadLocal<Integer> threadLocal = new ThreadLocal();

    @Test
    public void testThreadLocal() {
        threadLocal.set(1);
        threadLocal.set(2);
        threadLocal.set(3);
        System.out.println(threadLocal.get());
        System.out.println(threadLocal.get());
        System.out.println(threadLocal.get());
    }
}
