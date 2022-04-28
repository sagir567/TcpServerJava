package lecture4.TCPServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;


public class socketHandler_send extends Thread {

    String clientSentence;
    String capitalizedSentence;
    Socket incoming;
    int place=0;
    boolean flag = false ;
    ArrayList<arrays>  arr_array;
    int team = 0 ;


    socketHandler_send(Socket incoming , ArrayList<arrays>  arr_array , int team)
    {
        super(incoming.toString());
        this.incoming = incoming;
        this.arr_array = arr_array ;
        this.team = team ;


    }


    public void run(){
        try
        {

            DataOutputStream  outToClient =
                    new DataOutputStream(incoming.getOutputStream());

            outToClient.writeBytes("enter your nick name\n");

            while(true)
            {
                clientSentence="";

                get();

                if(clientSentence.toLowerCase().equals("bye"))
                    break;

                if(flag)   {
                    clientSentence += '\n'; //MUST BE \N !!!!!!!!!!!!

                    outToClient.writeBytes(clientSentence );// sending to the client clientSentence string
                    flag = false;
                }

            }

        }
        catch(IOException e)
        {

        }



    }

    public  void get()// enter in the clientSentence all the words from all the users
    {				// then we will send it to the client
        try{
            for(int i=place ; i<arr_array.size(); i++,place++)
                if(arr_array.get(i).getIncoming() != incoming && arr_array.get(i).getTeam() == team)// we don't want that the client will get his
                {									// own words

                    clientSentence+=arr_array.get(i).getNickName()+" "+arr_array.get(i).getNote()+" ";

                    flag=true;// the vault is now full
                }
        }catch ( Exception e)
        {

        }
    }

}


