package lecture5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    private static ServerSocket serverSocket;


    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void initializeServer() {

        try {
            while (!serverSocket.isClosed()) {

                Socket socket = serverSocket.accept();
//                System.out.println(" a new connection establish with " + socket.getLocalAddress());
                System.out.println(" a new connection establish with " + socket.getInetAddress());

                SocketHandler socketHandler;
                socketHandler = new SocketHandler(socket);

                Thread thread = new Thread(socketHandler);
                thread.start();


            }
        } catch (IOException e) {

        }

    }

    public static void closeSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5004);
            Server myServer = new Server(serverSocket);
            myServer.initializeServer();
            System.out.println("server is running");
        } catch (IOException e) {

            System.out.println("system failure: failed to initialize the server");
            System.out.println(e.getCause());
            e.printStackTrace();
            closeSocket();

        }
    }
}
