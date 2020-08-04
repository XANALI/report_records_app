package kz.xan.report_records_app.client.controllers.intro;

import kz.xan.report_records_app.client.Connection;
import kz.xan.report_records_app.client.Request;
import kz.xan.report_records_app.client.controllers.BaseController;
import kz.xan.report_records_app.client.controllers.panel.PanelController;
import kz.xan.report_records_app.client.controllers.panel.admin.AdminPanelController;
import kz.xan.report_records_app.client.controllers.panel.client.ClientPanelController;
import kz.xan.report_records_app.domain.RoleEnum;
import kz.xan.report_records_app.domain.User;

import java.io.IOException;
import java.util.Scanner;

@SuppressWarnings("InfiniteLoopStatement")
public class UserController extends BaseController {

    private static User user;

    public UserController(Connection connection, Scanner scanner) {
        super(connection, scanner);
        user = null;
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

    private void openUserPanel(){
        if(user != null){
            RoleEnum role = user.getRole();

            PanelController panel = null;
            if(role.equals(RoleEnum.ADMIN)){
                panel = new AdminPanelController(connection, scanner, user);
            }else if(role.equals(RoleEnum.CLIENT)){
                panel = new ClientPanelController(connection, scanner, user);
            }

            if(panel != null){
                panel.start();
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
                    openUserPanel();
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
