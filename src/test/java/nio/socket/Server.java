package nio.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    public static void main(String[] args) throws IOException{

        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("accept begin: " + System.currentTimeMillis());
        Socket socket = serverSocket.accept();
        System.out.println("accept end: " + System.currentTimeMillis());
        System.out.println("read begin: " + System.currentTimeMillis());
        int readLength = socket.getInputStream().read();
        System.out.println(readLength);
        System.out.println("read end: " + System.currentTimeMillis());
        socket.close();
        serverSocket.close();
    }
}
