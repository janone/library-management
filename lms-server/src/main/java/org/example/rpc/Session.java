package org.example.rpc;

import org.example.common.Result;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Session {
    private final Socket socket;

    private ObjectInputStream ios;
    private ObjectOutputStream oos;

    public Session(Socket socket) {
        this.socket = socket;
        try {
            this.ios = new ObjectInputStream(socket.getInputStream());
            this.oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void send(Result result) {
        try {
            oos.writeObject(result);
            oos.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // receive method
    public Request receive() {
        try {
            return (Request) ios.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
