package org.example.rpc;

import org.example.common.Constants;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public void start(){

        try{
            ServerSocket serverSocket = new ServerSocket(Constants.DEFAULE_PORT);

            System.out.println("Server started and listening on port "+Constants.DEFAULE_PORT);

            while (true) {
                try{
                    Socket clientSocket = serverSocket.accept();
                    Session session = new Session(clientSocket);
                    System.out.println("Connection established with client");

                    Thread thread = new Thread(new ServerHandler(session));
                    thread.start();
                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }



}
