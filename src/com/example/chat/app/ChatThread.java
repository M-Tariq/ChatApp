package com.example.chat.app;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatThread extends Thread {
    private Socket socket;
    private ChatServer chatServer;
    private DataOutputStream dataOutputStream;

    public ChatThread(Socket socket, ChatServer chatServer) {
        this.socket=socket;
        this.chatServer=chatServer;
        try {
            this.dataOutputStream=new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            DataInputStream dataInputStream= new DataInputStream(socket.getInputStream());
            String chatterName=dataInputStream.readUTF();
            System.out.println(chatterName+" joined");

            chatServer.addNewChatter(chatterName);
            Scanner scanner=new Scanner(System.in);
           String incomingMessage;
           do{
               incomingMessage=dataInputStream.readUTF();
               chatServer.broadcast(incomingMessage, this);

               //System.out.println("incomingMSG: "+incomingMessage);

           }while (!"end".equals(incomingMessage));
            chatServer.removeChatter(chatterName, this);
            System.out.println("Socket Closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            dataOutputStream.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
