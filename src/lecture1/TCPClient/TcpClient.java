package lecture1.TCPClient;

import java.io.*;
import java.net.*;
import java.util.Scanner;
class TcpClient {

    public static void main(String argv[])
    {
        String sentence;
        String modifiedSentence;

        try {

            Scanner inFromUser =  new Scanner (System.in) ;

            Socket clientSocket = new Socket("localhost", 10000);


            DataOutputStream outToServer =
                    new DataOutputStream(clientSocket.getOutputStream());

            BufferedReader inFromServer =
                    new BufferedReader(new
                            InputStreamReader(clientSocket.getInputStream()));

            while(true)
            {

                System.out.println("please insert something");
                sentence = inFromUser.next();

                outToServer.writeBytes(sentence + '\n');

                modifiedSentence = inFromServer.readLine();

                System.out.println("FROM SERVER: " + modifiedSentence);

                if(sentence.toLowerCase().equals("bye"))
                    break;

            }
            clientSocket.close();


        }catch ( ConnectException e)
        {
            System.out.println( " 404 C'ant connect to the Server");
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

