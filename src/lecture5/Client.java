package lecture5;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Client {


    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String userName;
    private String help = "opertaions key words\n 1) get clients list- get all clients connected right now  ";
    private Scanner sc = new Scanner(System.in);

    public Client(Socket socket, String userName) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.userName = userName;


        } catch (IOException e) {
            e.printStackTrace();
            closeStream(socket, bufferedReader, bufferedWriter);
        }

    }


    public void send() {

        try {
            bufferedWriter.write(userName);
            bufferedWriter.newLine();
            bufferedWriter.flush();


//            System.out.println(help);
            while (socket.isConnected()) {
                System.out.println("chat menu:\n 1) help\n 2) get users online \n 3) send group message \n 4) send private message" );




                int userChoice = sc.nextInt();
                switch (userChoice) {
                    case 1:
                        System.out.println(help);
                        break;

                    case 2:
                        getClientsList();
                        break;
                    case 3:
                        sendMessage();
                        break;
                    case 4:
                        getClientsList();
                        System.out.println("who would you like to send secret ???");
                        String name = sc.next();

                        System.out.println("type anything to send a message secretly to " + name);
                        String message =sc.nextLine();

                       String [] messageArr = sc.nextLine().split(" ");
//                       bufferedWriter.write();
                        for(int i=0;i<messageArr.length;i++) {
                            message+=(messageArr[i]+" ");

                        }
                        message = "<private><"+name+">"+userName+" has whispered you:"+message;
//                        System.out.println(message);
                        bufferedWriter.write(message);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
//                        sendMessage(name,message);


                        break;
                }


            }
        } catch (IOException ex) {
            System.out.println("Oh oh, something went wrong, failed to send message");
            ex.printStackTrace();
            closeStream(socket, bufferedReader, bufferedWriter);
        }

    }
    public void getClientsList(){

        try {
            bufferedWriter.write("get clients list");
            bufferedWriter.newLine();
            bufferedWriter.flush();

        }catch (IOException e){
            e.printStackTrace();
        }


    }
    public String sendMessage(String userName,String message) {

        try {
            message =(this.userName + ": " + message);

            bufferedWriter.write("<private><"+userName+">"+message);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            return " you successfully sent a secret to: "+userName;

        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
            return "failed to send secret to: "+userName;
        }

    }

    public void sendMessage() {


        System.out.println("type anything to send a message");
        String message =sc.nextLine();

        String [] messageArr = sc.nextLine().split(" ");

        for(int i=0;i<messageArr.length;i++) {
            message+=(messageArr[i]+" ");

        }
        try {
            bufferedWriter.write(userName + ": " + message);
            bufferedWriter.newLine();
            bufferedWriter.flush();


        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public void receiveMessage() {// here we're about to create a new thread, so we'll be able to send and receive messages at the same time


        new Thread(new Runnable() {


            @Override
            public void run() {
                String groupMessage;
                while (socket.isConnected()) {
                    try {
                        groupMessage = bufferedReader.readLine();
                        System.out.println(groupMessage);


                    } catch (IOException e) {
                        System.out.println("oh oh something went wrong, system failed to receive message ");
                        e.printStackTrace();
                        closeStream(socket, bufferedReader, bufferedWriter);
                    }
                }


            }
        }).start();

    }

    public void closeStream(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {

        try {
            if (bufferedReader != null) bufferedReader.close();

            if (bufferedWriter != null) bufferedWriter.close();

            if (socket != null) socket.close();


        } catch (IOException e) {
            System.out.println("failed to close connection");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter user name");
        String userName = sc.nextLine();

        Socket socket = new Socket("localhost", 5004);
        Client client = new Client(socket, userName);
        client.receiveMessage();
        client.send();

    }

}
