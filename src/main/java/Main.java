import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Main {
    public static void main(String[] args) {
        System.out.println("Logs from your program will appear here!");
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        int port = 6379;
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        clientSocket.close();
    }
}