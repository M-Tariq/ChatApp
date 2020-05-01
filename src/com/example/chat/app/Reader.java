package com.example.chat.app;

import java.io.*;
import java.net.Socket;

public class Reader extends Thread {
    public Socket socket;
    private DataInputStream dataInputStream;

    public Reader(Socket socket) {
        this.socket=socket;
        try {
           dataInputStream = new DataInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String incomingMessage = dataInputStream.readUTF();

                System.out.println("IncomingMessage: "+incomingMessage);

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }

    }
}
