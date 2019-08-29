package nio.buffer;

import java.nio.ByteBuffer;
import java.nio.InvalidMarkException;

public class TestMark {
    public static void main(String[] args) {
        byte[] byteArray = new byte[]{1, 2, 3, 4, 5};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        //mark<=pos
        byteBuffer.position(3);
        byteBuffer.mark();
//        byteBuffer.reset();
        byteBuffer.position(4);//position调整为不小于mark值，则mark不失效
        System.out.println(byteBuffer.reset());//pos=2
        byteBuffer.position(2);//position调整为小于mark值，则mark失效
        try {
            System.out.println(byteBuffer.reset());//exception
        } catch (Exception e) {
            System.out.println("position调整为小于mark值，则mark失效");
        }
//mark <= limit
        byteBuffer.limit(1);
        try {
            System.out.println(byteBuffer.reset());
        } catch (InvalidMarkException e) {
            System.out.println("limit调整为小于mark值，则mark失效");
        }
        try {
            byteBuffer.position(2);
        } catch (IllegalArgumentException e) {
            System.out.println("position 大于 limit， 抛出IllegalArgumentException");
        }
        try {
            byteBuffer.limit(10);
        }catch (IllegalArgumentException e)
        {
            System.out.println("limit 大于 capacity， 抛出IllegalArgumentException");
        }
        // position大于新的limit，则position为新的limit值
        byteBuffer.limit(5);
        byteBuffer.position(3);
        byteBuffer.limit(2);
        System.out.println(byteBuffer.position());//2

        ByteBuffer byteBuffer2 = ByteBuffer.wrap(byteArray);
        try {
            byteBuffer2.reset();
        } catch (InvalidMarkException e) {
            System.out.println("未定义mark， reset方法抛出InvalidMarkException");
        }
    }
}
