package nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Test12_Server {
    public static void main(String[] args) throws IOException{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));
        serverSocketChannel.configureBlocking(false);

        ServerSocketChannel serverSocketChannel2 = ServerSocketChannel.open();
        serverSocketChannel2.bind(new InetSocketAddress("localhost", 9999));
        serverSocketChannel2.configureBlocking(false);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        serverSocketChannel2.register(selector, SelectionKey.OP_ACCEPT);
        int selectCount = selector.select();
        System.out.println(serverSocketChannel);
        Set<SelectionKey> selectionKeySet = selector.keys();
        Set<SelectionKey> selectedKeySet = selector.selectedKeys();
        Iterator<SelectionKey> selectionKeyIterator = selectedKeySet.iterator();

        while(selectionKeyIterator.hasNext())
        {
            SelectionKey selectedKey = selectionKeyIterator.next();
            selectedKey.cancel();
            selector.close();
            serverSocketChannel.close();
            ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectedKey.channel();
            serverSocketChannel1.accept();
            System.out.println(serverSocketChannel1);
            System.out.println(selectedKey);
        }

        selector.close();
    }
}
