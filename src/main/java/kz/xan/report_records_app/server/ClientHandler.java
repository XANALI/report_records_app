package kz.xan.report_records_app.server;

import kz.xan.report_records_app.bootstrap.DataLoader;
import kz.xan.report_records_app.client.Request;
import kz.xan.report_records_app.domain.RoleEnum;
import kz.xan.report_records_app.domain.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@SuppressWarnings("InfiniteLoopStatement")
public class ClientHandler extends Thread {
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private DataLoader dataLoader;

    ClientHandler(Socket socket, DataLoader dataLoader) throws IOException {
        this.socket = socket;
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());

        this.dataLoader = dataLoader;
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

    public void run(){
        while (true) {
            try{
                Request request = (Request) ois.readObject();

                System.out.println(request.getCode());

                String code = request.getCode();

                if(code.equals("CLOSE")){
                    closeInputStream();
                    closeOutputStream();
                    closeSocket();
                    break;
                }else if(code.equals("GET_USER")){
                    String username = request.getUsername();
                    String password = request.getPassword();

                    User user = null;
                    if(username != null && password != null){
                        user = dataLoader.getUserByUsernameAndPassword(username, password);
                    }

                    Reply reply;
                    if(user == null){
                        reply = new Reply("USER_NOT_FOUND");
                    }else{
                        reply = new Reply("USER_FOUND");
                    }

                    reply.setUser(user);

                    oos.writeObject(reply);
                }else if(code.equals("ADD_USER")){
                    String username = request.getUsername();
                    String password = request.getPassword();

                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setRole(RoleEnum.CLIENT);

                    dataLoader.addUser(user);
                }
            }catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}
