package kz.xan.report_records_app.client.controllers;

import kz.xan.report_records_app.client.Connection;
import kz.xan.report_records_app.client.Request;
import kz.xan.report_records_app.domain.User;
import kz.xan.report_records_app.server.Reply;

import java.util.Scanner;


public class LoginController {
    private final Connection connection;
    private final Scanner scanner;

    public LoginController(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public User foundUser(String username, String password){
        Request request = new Request("GET_USER");
        request.setUsername(username);
        request.setPassword(password);

        connection.sendRequest(request);

        Reply reply = connection.getReply();

        if(reply.getCode().equals("USER_NOT_FOUND")){
            System.out.println("user is null");
            return null;
        }

        User user = reply.getUser();
        return user;
    }

    public User login(){
        System.out.println("Enter a username:");
        String username = scanner.next();
        System.out.println("Enter a password");
        String password = scanner.next();

        return foundUser(username, password);
    }

    public User getClient () {
        User user = login();

        return user;
    }

}
