package nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class Test10_Server {
    public static void main(String[] args) throws IOException{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        System.out.println("1");
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));
        System.out.println("2");
        serverSocketChannel.configureBlocking(false);
        System.out.println("3");
        Selector selector = Selector.open();
        System.out.println("4");
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("5");
        selector.select();
        System.out.println("6");
        selector.close();
        System.out.println("7 end!");
    }
}
