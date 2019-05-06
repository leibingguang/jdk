package nio.channel;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelLock {
    @Test
    public void testLock() throws Exception {
        Thread thread1 = new Thread(() -> {
            RandomAccessFile fileA = null;
            FileChannel fileChannelA = null;
            try {
                fileA = new RandomAccessFile(Constants.FILE_PATH, "rw");
                fileChannelA = fileA.getChannel();
                fileChannelA.lock(1, 2, false);
                Thread.sleep(Long.MAX_VALUE);
                System.out.println("11111");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fileChannelA.close();
                    fileA.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        Thread.sleep(1000);
        Thread thread = new Thread(() -> {

            FileChannel fileChannelB = null;
            RandomAccessFile fileB = null;
            try {

                 fileB = new RandomAccessFile(Constants.FILE_PATH, "rw");
                fileChannelB = fileB.getChannel();
                ByteBuffer byteBufferB = ByteBuffer.allocate(10);
                fileChannelB.read(byteBufferB);
                byteBufferB.flip();
                for (int i = 0; i < byteBufferB.limit(); i++) {
                    System.out.println("===="+(char) byteBufferB.get());
                }

                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fileChannelB.close();
                    fileB.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * 未出现异常，书上有误
     * @throws Exception
     */
    @Test
    public  void testLockAdvance() throws Exception
    {
        RandomAccessFile randomAccessFile = new RandomAccessFile(Constants.FILE_PATH, "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        fileChannel.lock(6, 2,false);
        fileChannel.write(ByteBuffer.wrap("1".getBytes()));
        fileChannel.write(ByteBuffer.wrap("2".getBytes()));
        fileChannel.write(ByteBuffer.wrap("3".getBytes()));
        fileChannel.write(ByteBuffer.wrap("4".getBytes()));
        fileChannel.write(ByteBuffer.wrap("5".getBytes()));
        fileChannel.write(ByteBuffer.wrap("6".getBytes()));
        fileChannel.write(ByteBuffer.wrap("7".getBytes()));
        System.out.println("11111");
    }
}
