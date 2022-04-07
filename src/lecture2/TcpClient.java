package lecture2;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TcpClient {

    public static void main(String argv[]) {

        String sentence;
        String modifiedSentence;

        try {
            Scanner inFromUser = new Scanner(System.in);
            Socket clientSocket = new Socket("localhost", 10000);


            DataOutputStream outToServer =
                    new DataOutputStream(clientSocket.getOutputStream());


            BufferedReader inFromServer =
                    new BufferedReader(
                            new InputStreamReader(
                                    clientSocket.getInputStream()));


            while (true) {
                sentence = inFromUser.nextLine();

                outToServer.writeBytes(sentence + "\n");
                modifiedSentence = inFromServer.readLine();

                if (modifiedSentence != null) System.out.println(modifiedSentence);
                if (sentence.toLowerCase().equals("bye")) break;

            }
            clientSocket.close();


        } catch (ConnectException e) {
            System.out.println("cant connect to server " + e);
        } catch (UnknownHostException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }





}
