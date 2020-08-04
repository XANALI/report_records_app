package kz.xan.report_records_app.client.controllers.panel.admin;

import kz.xan.report_records_app.client.Connection;
import kz.xan.report_records_app.client.controllers.panel.PanelController;
import kz.xan.report_records_app.domain.User;

import java.util.Scanner;

public class AdminPanelController extends PanelController {

    public AdminPanelController(Connection connection, Scanner scanner, User admin) {
        super(connection, scanner, admin);
    }

    @Override
    public void showServices(){
        while(true){
            System.out.println("ADMIN Panel");
            System.out.println("Choose one service:");
            System.out.println("1. Profile");
            System.out.println("2. Users");
            System.out.println("3. Records");
            System.out.println("0. Logout");

            int choice = scanner.nextInt();

            if(choice == 0){
                break;
            }else if(choice == 1){
                showProfile();
            }
        }
    }
}
