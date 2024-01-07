package org.example.rpc;

import org.example.common.Result;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Session {
    private final Socket socket;

    private ObjectInputStream ios;
    private ObjectOutputStream oos;

    public Session(Socket socket) {
        this.socket = socket;
        try {

            InputStream is = socket.getInputStream();
            this.ios = new ObjectInputStream(is);

            OutputStream os = socket.getOutputStream();
            this.oos = new ObjectOutputStream(os);

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void send(Result result) {
        try {
            System.out.println("server send: "+result);
            oos.writeObject(result);
            oos.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // receive method
    public Request receive() {
        try {
            Request request = (Request) ios.readObject();
            System.out.println("server receive: "+request);
            return request;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
