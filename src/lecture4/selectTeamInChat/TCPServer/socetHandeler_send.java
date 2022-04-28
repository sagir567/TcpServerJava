package lecture4.selectTeamInChat.TCPServer;

import lecture4.selectTeamInChat.TCPServer.message;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;


public class socetHandeler_send extends Thread {

    String clientSentence;
    String capitalizedSentence;
    Socket incoming;
    int place=0;
    boolean flag = false ;
    ArrayList<message>  arr_array;
    int team = 0 ;


    socetHandeler_send(Socket incoming , ArrayList<message>  arr_array )
    {
        super(incoming.toString());
        this.incoming = incoming;
        this.arr_array = arr_array ;

    }


    public void run(){
        try
        {
            String result="";

            DataOutputStream  outToClient =
                    new DataOutputStream(incoming.getOutputStream());

            for(int i=0 ; i< 10 ; i++)
            {
                for(message m : arr_array) {
                    if(m.getTeam() == i )
                        result +=  i+ " "+ m.getNickName();
                }
            }
            outToClient.writeBytes(result +"\n");
            outToClient.writeBytes("enter your nick name\n");
            outToClient.writeBytes("enter your team num\n");

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
            for(int i=place ; i<arr_array.size(); i++,place++) {
                if(arr_array.get(i).getIncoming() == incoming && arr_array.get(i).getNote().equals("1A2B3C") )
                {
                    this.team =  arr_array.get(i).getTeam();
                }
                if(arr_array.get(i).getIncoming() != incoming && arr_array.get(i).getTeam() == team && !arr_array.get(i).getNote().equals("1A2B3C"))// we don't want that the client will get his
                {									// own words

                    clientSentence+=arr_array.get(i).getNickName()+" "+arr_array.get(i).getNote()+" ";

                    flag=true;// the vault is now full
                }
            }
        }catch ( Exception e)
        {

        }
    }

}


