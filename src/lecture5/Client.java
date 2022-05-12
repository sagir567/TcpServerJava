package lecture5;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {


    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String userName;


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


            Scanner sc = new Scanner(System.in);
            while (socket.isConnected()) {
                String message = sc.nextLine();
                bufferedWriter.write(userName + ": " + message);
                bufferedWriter.newLine();

                bufferedWriter.flush();


            }
        } catch (IOException ex) {
            System.out.println("Oh oh, something went wrong, failed to send message");
            ex.printStackTrace();
            closeStream(socket, bufferedReader, bufferedWriter);
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
