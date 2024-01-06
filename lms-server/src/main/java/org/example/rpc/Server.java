package org.example.rpc;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {




    public void start(){

        try{
            ServerSocket serverSocket = new ServerSocket(8080);

            System.out.println("Server started and listening on port 8080");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                Session session = new Session(clientSocket);
                System.out.println("Connection established with client");

                Thread thread = new Thread(new ServerHandler(session));
                thread.start();
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }



}
