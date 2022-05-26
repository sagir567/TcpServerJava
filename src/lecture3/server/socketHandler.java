package lecture3.server;

import lecture3.client.Lecture;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;

public class socketHandler extends Thread {
    Socket incoming;

    socketHandler(Socket _in)
    {
        this.incoming=_in;

    }

    public void run()
    {
        int sum =0;
        try
        {

//           BufferedReader inFromClient =
//              new BufferedReader(new
//              InputStreamReader(incoming.getInputStream()));

            ObjectInputStream  inFromClient =
                    new ObjectInputStream (incoming.getInputStream());

//           ObjectOutputStream  outToClient =
//        		   new ObjectOutputStream (incoming.getOutputStream() );

            DataOutputStream outToClient =
                    new DataOutputStream(incoming.getOutputStream());

            while(true) {

                for(int i=0 ; i< 3 ; i++){
                    Object obj = inFromClient.readObject(); // get Object from client
                    if( obj instanceof Lecture)
                    {
                        Lecture l = (Lecture) obj;
                        sum += l.getSalary();
                    }else{
                        System.out.println("bug in de serializetion");
                        System.out.println(obj instanceof Lecture);
                        System.out.println(obj.getClass());
                    }

                }
                //send String to client
                outToClient.writeBytes( ("the average salary is:" +sum / 3 )+ "\n");


            }
        }
        catch(IOException | ClassNotFoundException e)
        {

        }

    }
}
