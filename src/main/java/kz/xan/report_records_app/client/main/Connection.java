package kz.xan.report_records_app.client.main;


import kz.xan.report_records_app.server.Reply;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public Connection() {
    }

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }

    public void sendRequest(Request request){
        try {
            oos.writeObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Reply getReply(){
        try {
            return (Reply) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void closeInputStream() throws IOException {
        if(ois != null) {
            ois.close();
            ois = null;
        }
    }

    public void closeOutputStream() throws IOException {
        if(oos != null){
            oos.close();
            oos = null;
        }
    }

    public void closeSocket() throws IOException {
        if(socket != null){
            socket.close();
            socket = null;
        }
    }
}
