package lecture4.TCPServer;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

class TCPServer {

    private static ArrayList<arrays> arr_array = new ArrayList<arrays>();  // the key for the vault
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

            } catch(IOException e) {
                System.out.println(e);
                continue;
            }

            System.out.println(incoming);



            new socketHandler(incoming , arr_array , team/3 ).start();// the first thread for receive from clients
            new socketHandler_send(incoming ,arr_array , team/3 ).start();// the second thread for sending to
            //the clients
            team++;
        }
    }
}
