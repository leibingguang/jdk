package reference;

import org.junit.Test;

import java.lang.ref.WeakReference;

public class WeakReferenceDemo {

    @Test
    public void weakReferenceTest() throws Exception {
        WeakReference<Object> weakReference = new WeakReference<>(new Object());
        System.out.println("before gc " + weakReference.get());
        System.gc();
        System.out.println("after gc " + weakReference.get());
//        System.out.println(integer);
    }
}
