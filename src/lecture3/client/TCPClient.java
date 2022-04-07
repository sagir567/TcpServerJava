package lecture3.client;

/*
 * Java provides a mechanism, called object serialization where an object can be represented as a sequence of bytes that includes the object's data as well as information about the object's type and the types of data stored in the object.

After a serialized object has been written into a file, it can be read from the file and deserialized that is, the type information and bytes that represent the object and its data can be used to recreate the object in memory.

Most impressive is that the entire process is JVM independent, meaning an object can be serialized on one platform and deserialized on an entirely different platform.

Classes ObjectInputStream and ObjectOutputStream are high-level streams that contain the methods for serializing and deserializing an object.
 *
 * pay attation that if the client sends you integer so in the server you will get integer
 * and if it sends you string so you will get string .
 *
 *
 */

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;



class TCPClient_with_serialized {

    public static void main(String argv[]) throws Exception
    {
        boolean flag = true;

        ArrayList<Lecture> arrL = new ArrayList<Lecture>();
        int id, salary;
        String name;

        Scanner inFromUser =  new Scanner (System.in) ;

        Socket clientSocket = new Socket("localhost", 10000);

        //for string
//        DataOutputStream outToServer =
//          new DataOutputStream(clientSocket.getOutputStream());

        //for object
        ObjectOutputStream  outToServer =
                new ObjectOutputStream (clientSocket.getOutputStream() );
        //for object
//        ObjectInputStream  inFromServer =
//          new ObjectInputStream (clientSocket.getInputStream());

        //for string
        BufferedReader inFromServer =
                new BufferedReader(new
                        InputStreamReader(clientSocket.getInputStream()));

        while(true)
        {
            if (flag ==true) {
                for (int i = 0; i < 3; i++) {
                    System.out.println("plz insert Lecture id");
                    id = inFromUser.nextInt();
                    inFromUser.nextLine();
                    System.out.println("plz insert Lecture name");
                    name = inFromUser.nextLine();
                    System.out.println("plz insert Lecture salary");
                    salary = inFromUser.nextInt();

                    //send object to server
                    outToServer.writeObject(new Lecture(salary, id, name));

                }
                flag = false;
            }


            String result = inFromServer.readLine();// get String from server

            System.out.println(result);



        }


    }
}

