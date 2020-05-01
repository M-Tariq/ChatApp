package com.example.chat.app;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.*;
public class ChatServer{
    private Set<String> chatters=new HashSet<>();
    private Set<ChatThread> chatThreads=new HashSet<>();
    private ServerSocket serverSocket;

    public ChatServer() {
        try {
           serverSocket=new ServerSocket(6000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void goLive(){
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                ChatThread chatThread = new ChatThread(socket, this);
                chatThreads.add(chatThread);
                chatThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addNewChatter(String chatter){
        chatters.add(chatter);
        chatters.add(chatter);
       // System.out.println("chatter "+chatter+" added.");
    }

    public void broadcast(String message, ChatThread sender) {
        //System.out.println("Broadcasting: "+message);
        for(ChatThread aThread: chatThreads){
            if(aThread!=sender){
                aThread.sendMessage(message);
            }
        }
    }
    public void removeChatter(String chatterName, ChatThread chatThread) {
        chatters.remove(chatterName);
        chatThreads.remove(chatThread);
    }
    public static void main(String[] args){
        ChatServer chatServer=new ChatServer();
        chatServer.goLive();
        System.out.println("Live");
    }
}
