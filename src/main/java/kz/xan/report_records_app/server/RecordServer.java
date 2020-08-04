package kz.xan.report_records_app.server;

import kz.xan.report_records_app.bootstrap.DataLoader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static kz.xan.report_records_app.client.config.ConnectionConfig.PORT_NUMBER;

public class RecordServer {

    public static void main(String[] args) {
        try{
            // initializing of a server with defined port
            ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
            DataLoader dataLoader = new DataLoader();

            while (true) {
                //listening for a client socket request
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());

                // Making a new ClientHandler for each client
                UserHandler userHandler = new UserHandler(socket, dataLoader);

                // Starting of relations client and server
                userHandler.start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
