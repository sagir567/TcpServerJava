package lecture1.TCPServer;

import java.io.*;
import java.net.*;

class TcpServer {

    public static void main(String argv[]) throws Exception
    {

        ServerSocket s = null;

        try {
            s = new ServerSocket(10000);

        } catch(IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        while (true) {
            Socket incoming = null;

            try {
                incoming = s.accept();

            } catch(IOException e) {
                System.out.println(e);
                continue;
            }

            new socketHandler(incoming).start();

        }
    }
}
