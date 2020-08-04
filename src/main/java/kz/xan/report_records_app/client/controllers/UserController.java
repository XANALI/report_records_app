package kz.xan.report_records_app.client.controllers;

import kz.xan.report_records_app.client.Connection;
import kz.xan.report_records_app.client.Request;
import kz.xan.report_records_app.domain.RoleEnum;
import kz.xan.report_records_app.domain.User;

import java.io.IOException;
import java.util.Scanner;

@SuppressWarnings("InfiniteLoopStatement")
public class UserController {
    private final Connection connection;
    private final Scanner scanner;

    private static User user;

    public UserController(Connection connection, Scanner scanner) {
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

    private boolean userLogin(){
        LoginController login = new LoginController(connection, scanner);
        user = login.getUser();

        if(user == null){
            return false;
        }

        System.out.println(user);

        return true;
    }

    private void userRegister(){
        RegisterController registerController = new RegisterController(connection, scanner);

        registerController.addUser();
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

    private void openClientPanels(){
        if(user != null){
            RoleEnum role = user.getRole();
            if(role.equals(RoleEnum.ADMIN)){

            }else if(role.equals(RoleEnum.CLIENT)){

            }
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
                boolean loginStat = userLogin();
                if(loginStat){

                }
            }else if(choice == 2){
                userRegister();
            }
        }
    }

    public void run() {
        introChoice();
    }
}
