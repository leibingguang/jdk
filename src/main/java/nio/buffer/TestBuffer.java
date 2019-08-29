package nio.buffer;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class TestBuffer {
    public static void main(String[] args) {
        byte[] byteArray = new byte[]{1, 2, 3, 4, 5};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        System.out.println(byteBuffer.capacity());
        System.out.println(Arrays.toString(byteBuffer.array()));
        byteBuffer.put(0, (byte) 21);
//        byteBuffer.limit(1);
        byteBuffer.position(3);
        System.out.println(byteBuffer);
//        byteBuffer.put(0, (byte) 21);
        System.out.println(byteBuffer.get());
//        System.out.println(byteBuffer.limit(1));
        System.out.println(Arrays.toString(byteBuffer.array()));
//        System.out.println(byteBuffer);
    }
}
