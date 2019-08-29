package nio.channel;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTruncate {
    @Test
    public void testChannelTruncate() throws IOException
    {
        File file = new File(Constants.FILE_PATH);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.wrap("abcdefg".getBytes());
        fileChannel.write(byteBuffer);
        fileChannel.position(2);
        fileChannel.truncate(4);
        fileChannel.close();
        fileOutputStream.close();
    }
}
