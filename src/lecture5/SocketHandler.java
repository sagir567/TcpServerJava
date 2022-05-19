package lecture5;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class SocketHandler implements Runnable {

    public static ArrayList<SocketHandler> clientsArray = new ArrayList<>(); // trough this array we'll be able to broadcast messages to the all requested destinations
    private Socket socket;
    private BufferedReader bufferedReader;          // BufferedReader obj read data in buffered block so until we won't fill the buffer he will not send the message
    private BufferedWriter bufferedWriter;
    private String userName;


    public SocketHandler(Socket socket) {
        try {
            this.socket = socket;
            new OutputStreamWriter(socket.getOutputStream());
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            this.userName = bufferedReader.readLine();
            clientsArray.add(this);
            broadCastMessage("IN From server:" + userName + " has joined the chat!");

        } catch (IOException e) {
            closeStream(socket, bufferedReader, bufferedWriter);

        }
    }

    @Override
    public void run() {
        String message;
        while (socket.isConnected()) {
            try {

                message = bufferedReader.readLine();
                if(message.equals(userName+": get clients list")){
                    getList(this);
                    continue;
                }
                broadCastMessage(message);


            } catch (IOException e) {
                closeStream(socket, bufferedReader, bufferedWriter);
                e.printStackTrace();
                break;
            }
        }
    }

    public void getList(SocketHandler user){
        String users ="";
        for (SocketHandler client: clientsArray){
            if(!client.userName.equals(userName)){
                users+=(client.userName+"\n" );
            }
            try {
                user.bufferedWriter.write(users);
                user.bufferedWriter.newLine();
                user.bufferedWriter.flush();
            }catch (IOException e){


                System.out.println(e);
                e.printStackTrace();
                System.out.println("failed to deliver clients list");
            }
            }


    }

    public void broadCastMessage(String message) {
        for (SocketHandler client : clientsArray) {
            try {
                if (!client.userName.equals(userName)) {

                    client.bufferedWriter.write(message);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();

                }
            } catch (IOException e) {

                closeStream(socket, bufferedReader, bufferedWriter);
            }
        }

    }

    public void removeClient() {
        clientsArray.remove(this);
        broadCastMessage("IN From server: " + userName + " has disconnected !!! ");
    }

    public void closeStream(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClient();
        try {
            if (bufferedReader != null) bufferedReader.close();

            if (bufferedWriter != null) bufferedWriter.close();

            if (socket != null) socket.close();


        } catch (IOException e) {
            System.out.println("failed to close connection");
            e.printStackTrace();
        }


    }
}

