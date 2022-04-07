package lecture2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TcpServer {
    public static ArrayList<String> animalsList = new ArrayList<>();
    public static void main(String argv[]) throws Exception {

        animalsList.add("dog");
        animalsList.add("cat");
        animalsList.add("owl");
        animalsList.add("fish");
        animalsList.add("cow");
        animalsList.add("squirrel");

        ServerSocket s = null;

        try {
            s = new ServerSocket(10000);
        } catch (IOException e) {
            System.out.println("failed to create socket " + e);
            System.exit(1);
        }
        while (true) {
            Socket income = null;
            try {
                income = s.accept();

            } catch (IOException e) {
                System.out.println("failed to create connetion " + e);
                continue;
            }
            new socketHandler(income).start();

        }
    }

}
