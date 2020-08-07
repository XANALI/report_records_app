package kz.xan.report_records_app.client.controllers.panel.client;

import kz.xan.report_records_app.client.controllers.panel.PanelController;
import kz.xan.report_records_app.client.main.Connection;
import kz.xan.report_records_app.domain.User;

import java.util.Scanner;

public class ClientPanelController extends PanelController {

    public ClientPanelController(Connection connection, Scanner scanner, User client) {
        super(connection, scanner, client);
    }

    @Override
    public void showServices() {
        while(true){
            System.out.println("USER Panel");
            System.out.println("Choose one service:");
            System.out.println("1. Profile");
            System.out.println("2. Records");
            System.out.println("3. Reports");
            System.out.println("0. Logout");

            int choice = scanner.nextInt();

            if(choice == 0){
                break;
            }else if(choice == 1){
                user = getProfile();
                if(user == null){
                    break;
                }
            }else if(choice == 2){
                getRecords();
            }
        }
    }

}
