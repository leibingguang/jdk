package nio.channel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class ChannelRead {
    File file = new File(Constants.FILE_PATH);

    FileChannel fileWriteChannel = null;
    FileOutputStream fileOutputStream = null;
    FileInputStream fileInputStream = null;
    FileChannel fileReadChannel = null;

    @Before
    public void before() throws IOException {
        fileOutputStream = new FileOutputStream(file);
        fileWriteChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.wrap("abcde".getBytes());
        fileWriteChannel.write(byteBuffer);
        fileInputStream = new FileInputStream(file);
        fileReadChannel = fileInputStream.getChannel();
    }

    /**
     * 验证int read(ByteBuffer dst) 返回值的意义
     *
     * @throws IOException
     */
    @Test
    public void testReadReturn() throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        int readLength = fileReadChannel.read(byteBuffer);
        System.out.println("A readLength : " + readLength);
//        byteBuffer.clear();
        readLength = fileReadChannel.read(byteBuffer);
        System.out.println("B readLength : " + readLength);
        byteBuffer.clear();
        readLength = fileReadChannel.read(byteBuffer);
        System.out.println("C readLength : " + readLength);
        byteBuffer.clear();

    }

    /**
     * 验证int read(ByteBuffer dst) 方法是从通道的当前位置开始读取的
     *
     * @throws IOException
     */
    @Test
    public void testReadWithChannelPosition() throws IOException {
        System.out.println("A, channel position: " + fileReadChannel.position());
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        fileReadChannel.read(byteBuffer);
        System.out.println("A, read content: " + Arrays.toString(byteBuffer.array()));
        fileReadChannel.position(2);
        byteBuffer.clear();
        System.out.println("B, channel position: " + fileReadChannel.position());
        fileReadChannel.read(byteBuffer);
        System.out.println("B, read content: " + Arrays.toString(byteBuffer.array()));

    }

    /**
     * 验证int read(ByteBuffer dst) 方法将字节放入ByteBuffer的当前位置
     *
     * @throws IOException
     */
    @Test
    public void testReadWithBufferPosition() throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        byteBuffer.position(2);
        int readLength = fileReadChannel.read(byteBuffer);
        System.out.println("length : " + readLength);
        System.out.println("A, read content: " + Arrays.toString(byteBuffer.array()));
    }

    /**
     * 验证int read(ByteBuffer dst) 方法具有同步特性
     */
    @Test
    public void testReadSynchronize()
    {

    }

    @After
    public void after() throws IOException {
        fileWriteChannel.close();
        fileOutputStream.close();
        fileInputStream.close();
        fileReadChannel.close();
    }
}
