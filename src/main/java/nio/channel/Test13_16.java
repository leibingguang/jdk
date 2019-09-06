package nio.channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test13_16 {
    public static void main(String[] args) throws Exception {
        RandomAccessFile fileA = new RandomAccessFile(Constants.FILE_PATH, "rw");
        FileChannel fileChannelA = fileA.getChannel();
        fileChannelA.lock(1, 2, false);
////        fileChannelA.concurrent();
        System.out.println("1111");
        Thread.sleep(Long.MAX_VALUE);
        System.out.println("2222");
    }
}
