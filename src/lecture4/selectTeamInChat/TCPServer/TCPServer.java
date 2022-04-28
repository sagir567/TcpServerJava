package lecture4.selectTeamInChat.TCPServer;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

class TCPServer {

    private static ArrayList<message> arr_array = new ArrayList<message>();  // the key for the vault
    static int team = 0 ;
    static String nickName;

    public static void main(String argv[]) throws Exception
    {

        ServerSocket s = null;

        try {
            s = new ServerSocket(10001);

        } catch(IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        while (true) {
            Socket incoming = null;

            try {
                incoming = s.accept();
                System.out.println("new connection accomplished with " + incoming.getLocalSocketAddress());

            } catch(IOException e) {
                System.out.println(e);
                continue;
            }

            System.out.println(incoming);



            new socketHandler(incoming , arr_array  ).start();// the first thread for receive from clients
            new socetHandeler_send(incoming ,arr_array ).start();// the second thread for sending to
            //the clients
            team++;
        }
    }
}
