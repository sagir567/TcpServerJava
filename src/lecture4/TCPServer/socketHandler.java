package lecture4.TCPServer;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

public class socketHandler extends Thread {

    Socket incoming;
    ArrayList<arrays>  arr_array;
    private String clientSentence;
    int team;
    String NickNAME;
    static int num = 15;
    int point=0;
    static boolean flag = false;

    socketHandler(Socket _in , ArrayList<arrays>  arr_array , int team )
    {
        this.incoming=_in;
        this.arr_array = arr_array;
        this.team = team;


    }

    public void run()
    {


        try
        {

            BufferedReader inFromClient =
                    new BufferedReader(new
                            InputStreamReader(incoming.getInputStream()));

            NickNAME = (inFromClient.readLine());


            while(true) {


                clientSentence = inFromClient.readLine(); // Receive from client

                if(clientSentence.toLowerCase().equals("bye"))// if the client send bye
                {										// we close the connection

                    System.out.println(incoming + " is now disconnect");

                    incoming.close();

                    break;
                }

                if(clientSentence.equals(num +""))
                    point+=10;

                in(clientSentence , incoming , arr_array , team , NickNAME, point);// save the client Sentence

                if(flag == true)
                {
                    point=0;
                    sleep(5000);
                    flag = false;

                }
                clientSentence="";


            }

        }
        catch(IOException e)
        {

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static synchronized  void in(String clientSentence , Socket incoming , ArrayList<arrays>  arr_array , int team , String nickName,int point ){

        if(clientSentence.equals(num +""))
            arr_array.add(new arrays(nickName +" Secsses "+point , null , team , nickName)); // all the users can see it because it is static

        else
            arr_array.add(new arrays(clientSentence , incoming , team , nickName));

        if(point == 50){
            arr_array.add(new arrays(nickName +" win the game !!!! " , null , team , nickName)); // all the users can see it because it is static
            flag = true;

        }
    }

}
