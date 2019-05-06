package nio.buffer;

import java.nio.ByteBuffer;

public class TestDirect {
    public static void main(String[] args) {
        byte[] byteArray = new byte[]{1, 2, 3, 4, 5};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        System.out.println(byteBuffer.isDirect());
        System.out.println(ByteBuffer.allocate(10).isDirect());
        System.out.println(ByteBuffer.allocateDirect(10).isDirect());//分配直接缓冲区
    }
}
