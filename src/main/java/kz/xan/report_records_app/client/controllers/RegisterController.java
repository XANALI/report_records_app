package kz.xan.report_records_app.client.controllers;

import kz.xan.report_records_app.client.Connection;
import kz.xan.report_records_app.client.Request;

import java.util.Scanner;

public class RegisterController {
    private final Connection connection;
    private final Scanner scanner;

    public RegisterController(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    private void registration(){
        System.out.println("REGISTRATION");

        System.out.println("Username:");
        String username = scanner.next();

        System.out.println("Password:");
        String password = scanner.next();

        if(!username.isEmpty() && !password.isEmpty()){
            Request request = new Request("ADD_USER");

            request.setUsername(username);
            request.setPassword(password);

            connection.sendRequest(request);
        }
    }

    public void addUser(){
        registration();
    }
}
