package concurrent.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundQueue<T> {
    private int removeIndex, addIndex, count;
    private Object[] objectArray;
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public BoundQueue(int size) {
        objectArray = new Object[size];
    }

    /**
     * 添加元素
     *
     * @param t
     * @throws InterruptedException
     */
    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            if (count == objectArray.length)
                notFull.await();
            objectArray[addIndex] = t;
            if (++addIndex == objectArray.length)
                addIndex = 0;
            count++;
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 移除元素
     *
     * @return
     * @throws InterruptedException
     */
    public T remove() throws InterruptedException {
        lock.lock();
        Object object = null;
        try {
            if (count == 0)
                notEmpty.await();
            object = objectArray[removeIndex];
            if (++removeIndex == objectArray.length)
                removeIndex = 0;
            count--;
            notFull.signalAll();
        } finally {
            lock.unlock();
        }
        return (T) object;
    }
}
