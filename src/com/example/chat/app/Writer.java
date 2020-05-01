package com.example.chat.app;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
public class Writer extends Thread {
    public Socket socket;
    private DataOutputStream dataOutputStream;
    public Writer(Socket socket) {
        try {
            this.socket=socket;
            dataOutputStream= new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
       Scanner scanner=new Scanner(System.in);
        String userName=scanner.nextLine();
        try {
            dataOutputStream.writeUTF(userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String msg;
        do {
            //System.out.println("Writer thread: ");
            msg = scanner.nextLine();
            try {
                dataOutputStream.writeUTF(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }while (!"end".equals(msg));
        try{
            socket.close();
            dataOutputStream.flush();
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }



    }
}
