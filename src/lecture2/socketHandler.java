package lecture2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class socketHandler  extends  Thread {
    Socket income;

    socketHandler(Socket _in) {
        income = _in;
    }

    public void run() {

        String[] clientSentence;
        String string;
        String capitalizedSentence = "";


        try {
            BufferedReader inFromClient = new BufferedReader(
                    new InputStreamReader(
                            income.getInputStream()));
            DataOutputStream outToCLient = new DataOutputStream(income.getOutputStream());

            while (true) {
                string = inFromClient.readLine();
                clientSentence = string.split("_");

                for (int i = 0; i < clientSentence.length; i++) {
                    for (String animal : TcpServer.animalsList)
                        if (clientSentence[i].equals(animal)) clientSentence[i] = "x";

                    capitalizedSentence += clientSentence[i] + " ";
                }

                capitalizedSentence = capitalizedSentence + "\n";
                outToCLient.writeBytes(capitalizedSentence);
                capitalizedSentence = "";

            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

