package nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test13_17 {
    public static void main(String[] args) throws Exception {

            RandomAccessFile fileB = new RandomAccessFile(Constants.FILE_PATH, "rw");
            FileChannel fileChannelB = fileB.getChannel();
            ByteBuffer byteBufferB = ByteBuffer.allocate(10);
            fileChannelB.read(byteBufferB);
            byteBufferB.flip();
            for (int i = 0; i < byteBufferB.limit(); i++) {
                System.out.println("===="+(char) byteBufferB.get());
            }

    }
}
