package nio.socket;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
//        long beginTime = System.currentTimeMillis();
////        Socket socket = new Socket("10.211.93.155", 9094);
//        Socket socket = new Socket("localhost", 9999);
////        Thread.sleep(Integer.MAX_VALUE);
//        Thread.sleep(1000);
//        socket.close();
//        long endTime = System.currentTimeMillis();
//        System.out.println(endTime - beginTime);
        System.out.println(InetAddress.getLocalHost());

    }
}
