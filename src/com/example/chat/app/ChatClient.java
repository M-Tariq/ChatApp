package com.example.chat.app;

import java.io.IOException;
import java.net.Socket;

public class ChatClient {
    private Socket socket;
    public void startSession(){
        try {
            socket=new Socket("localhost",6000);
            System.out.println("Connected to server");
            new Reader(socket).start();
            new Writer(socket).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        ChatClient chatClient=new ChatClient();
        chatClient.startSession();
    }
}
