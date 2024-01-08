package org.example.rpc;

import org.example.common.Constants;
import org.example.common.Result;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Session {
    private static Session session = new Session();

    public static Session getInstance(){
        return session;
    }

    private Socket socket;
//    private ObjectInputStream ois;
//    private ObjectOutputStream oos;

    private Session(){
        try {
            this.socket = new Socket(Constants.DEFAULE_HOST,Constants.DEFAULE_PORT);
            System.out.println(" initial socket success. server param: " + Constants.DEFAULE_HOST + ":" + Constants.DEFAULE_PORT);
//            oos = new ObjectOutputStream(this.socket.getOutputStream());
//            ois = new ObjectInputStream(this.socket.getInputStream());
        } catch (Exception e) {
            System.out.println(" initial socket fail. ");
            throw new RuntimeException(e);
        }
    }




    public void send(Request request){
//        try(ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());) {
        try{
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            if(request == null){
                throw new IllegalArgumentException("request is null");
            }
//            System.out.println("client send: "+request);
            oos.writeObject(request);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Result receive(){
//        try(ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
        try{
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Result result = (Result) ois.readObject();
            if(result == null){
                throw new IllegalArgumentException("result is null");
            }
//            System.out.println("client receive: "+result);
            return result;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
