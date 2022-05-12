package lecture5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    private ServerSocket serverSocket;


    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void initializeServer() {

        try {
            while (!serverSocket.isClosed()) {

                Socket socket = serverSocket.accept();
                System.out.println(" a new connection establish with " + socket.getLocalAddress());
                SocketHandler socketHandler;
                socketHandler = new SocketHandler(socket);

                Thread thread = new Thread(socketHandler);
                thread.start();


            }
        } catch (IOException e) {

        }

    }

    public void closeSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(5004);
        Server myServer = new Server(serverSocket);
        myServer.initializeServer();
        System.out.println("server is running");

    }


}
