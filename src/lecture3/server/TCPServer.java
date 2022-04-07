package lecture3.server;

import java.io.*;
import java.net.*;

class TCPServer {

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
                System.out.println("connected to client");

            } catch(IOException e) {
                System.out.println(e);
                continue;
            }

            new socketHandler(incoming).start();

        }
    }
}
