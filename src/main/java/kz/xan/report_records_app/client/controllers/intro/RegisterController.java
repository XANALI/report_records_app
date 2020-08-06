package kz.xan.report_records_app.client.controllers.intro;

import kz.xan.report_records_app.client.controllers.BaseController;
import kz.xan.report_records_app.client.main.Connection;
import kz.xan.report_records_app.client.main.Request;

import java.util.Scanner;

public class RegisterController extends BaseController {

    public RegisterController(Connection connection, Scanner scanner) {
        super(connection, scanner);
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
