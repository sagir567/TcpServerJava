package lecture1.TCPServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.*;
import java.util.Arrays;

public class socketHandler extends Thread {
    Socket incoming;

    socketHandler(Socket _in)
    {
        incoming=_in;

    }

    public void run()
    {
        String[] clientSentence;
        String capitalizedSentence = null;
        int num ;
        int num2;
        try
        {

            BufferedReader inFromClient =
                    new BufferedReader(new
                            InputStreamReader(incoming.getInputStream()));


            DataOutputStream  outToClient =
                    new DataOutputStream (incoming.getOutputStream() );

            while(true) {
                clientSentence = inFromClient.readLine().split("_");
                System.out.println(Arrays.toString(clientSentence));
                num = Integer.parseInt(clientSentence[0]);
                num2 = Integer.parseInt(clientSentence[2]);

                switch(clientSentence[1].charAt(0) )
                {

                    case '+':
                        capitalizedSentence = (num + num2) + "";
                        break;
                    case '-':
                        capitalizedSentence = (num - num2) + "";
                        break;
                    case '*':
                        capitalizedSentence = (num * num2) + "";
                        break;
                    case '/':
                        capitalizedSentence = (num / num2) + "";
                        break;


                }

                //  num = Integer.parseInt(clientSentence); // sends int
                capitalizedSentence = capitalizedSentence+"\n"; //MUST BE \N !!!!!!!!!!!!
                // above send string

                outToClient.writeBytes(capitalizedSentence);

                //   outToClient.writeObject( num);




            }
        }
        catch(IOException e)
        {

        }

    }
}
