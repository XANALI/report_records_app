package kz.xan.report_records_app.client.controllers.intro;

import kz.xan.report_records_app.client.controllers.BaseController;
import kz.xan.report_records_app.client.main.Connection;
import kz.xan.report_records_app.client.main.Request;
import kz.xan.report_records_app.domain.User;
import kz.xan.report_records_app.server.Reply;

import java.util.Scanner;


public class LoginController extends BaseController {

    public LoginController(Connection connection, Scanner scanner) {
        super(connection, scanner);
    }

    private User foundUser(String username, String password){
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

    private User login(){
        System.out.println("Enter a username:");
        String username = scanner.next();

        System.out.println("Enter a password");
        String password = scanner.next();

        return foundUser(username, password);
    }

    public User getUser () {
        User user = login();

        return user;
    }

}
