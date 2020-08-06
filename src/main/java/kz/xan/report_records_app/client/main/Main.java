package kz.xan.report_records_app.client.main;


import kz.xan.report_records_app.bootstrap.DataLoader;
import kz.xan.report_records_app.client.controllers.intro.UserController;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static kz.xan.report_records_app.client.config.ConnectionConfig.CONNECTION_ADDRESS;
import static kz.xan.report_records_app.client.config.ConnectionConfig.PORT_NUMBER;

public class Main extends Thread {

    private Connection connection;
    private Scanner scanner;
    private DataLoader dataLoader;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run(){
        try {
            Socket socket = new Socket(CONNECTION_ADDRESS, PORT_NUMBER);

            connection = new Connection(socket);

            scanner = new Scanner(System.in);

            UserController userController = new UserController(connection, scanner);
            userController.run();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }
}
