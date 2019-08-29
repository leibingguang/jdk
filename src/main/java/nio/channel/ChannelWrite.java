package nio.channel;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelWrite {

    /**
     * 验证int write(ByteBuffer src)方法是从通道的当前位置开始写入的
     * @throws IOException
     */
    @Test
    public void testChannelPosition() throws IOException {
        File file = new File(Constants.FILE_PATH);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        FileChannel fileChannel = fileOutputStream.getChannel();
        System.out.println("A FileChannel position: " + fileChannel.position());//0
        ByteBuffer byteBuffer = ByteBuffer.wrap("abcde".getBytes());
        fileChannel.write(byteBuffer);//写入5个字节
        System.out.println("B FileChannel position: " + fileChannel.position());//5

        byteBuffer.rewind();
        byteBuffer.position(2);
        fileChannel.write(byteBuffer);//写入3个字节
        System.out.println("C FileChannel position: " + fileChannel.position());//8

        fileChannel.close();
        fileOutputStream.close();
    }


    /**
     * 验证int write(ByteBuffer src)方法将ByteBuffer的remaining写入通道
     * @throws IOException
     */
    @Test
    public void testChannelWithBufferRemaining() throws IOException {
        File file = new File(Constants.FILE_PATH);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer1 = ByteBuffer.wrap("abcde".getBytes());
        ByteBuffer byteBuffer2 = ByteBuffer.wrap("12345".getBytes());
        fileChannel.write(byteBuffer1);//写入5个字节
        fileChannel.position(2);

        byteBuffer2.position(2);
        byteBuffer2.limit(3);
        fileChannel.write(byteBuffer2);
        fileChannel.close();
        fileOutputStream.close();
    }

}
