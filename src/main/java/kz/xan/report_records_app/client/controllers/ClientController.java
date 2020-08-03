package kz.xan.report_records_app.client.controllers;

import kz.xan.report_records_app.client.Connection;
import kz.xan.report_records_app.client.Request;
import kz.xan.report_records_app.domain.User;

import java.io.IOException;
import java.util.Scanner;

@SuppressWarnings("InfiniteLoopStatement")
public class ClientController {
    private final Connection connection;
    private final Scanner scanner;

    private static User user;

    public ClientController(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
        user = null;
    }

    public Connection getConnection() {
        return connection;
    }

    public Scanner getScanner() {
        return scanner;
    }

    private boolean clientLogin(){
        LoginController login = new LoginController(connection, scanner);
        user = login.getClient();

        if(user == null){
            throw new RuntimeException("User not found!");
        }else {
            System.out.println(user);
        }

        return true;
    }

    private void clientRegister(){
        RegisterController registerController = new RegisterController(connection, scanner);

        registerController.setClient();
    }

    private void closeConnection(){
        try {
            connection.sendRequest(new Request("CLOSE"));

            connection.closeInputStream();
            connection.closeOutputStream();
            connection.closeSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void introChoice(){
        while (true) {
            System.out.println("Welcome!");
            System.out.println("Choose one service:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            if(choice == 0){
                closeConnection();
                break;
            }else if(choice == 1){
                clientLogin();
            }else if(choice == 2){
                clientRegister();
            }
        }
    }

    public void run() {
        introChoice();
    }
}
