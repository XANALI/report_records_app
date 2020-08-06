package kz.xan.report_records_app.client.controllers.panel.services.profile;

import kz.xan.report_records_app.client.controllers.panel.services.ServiceController;
import kz.xan.report_records_app.client.main.Connection;
import kz.xan.report_records_app.client.main.Request;
import kz.xan.report_records_app.domain.User;

import java.util.Scanner;

public class ProfileController extends ServiceController {


    public ProfileController(Connection connection, Scanner scanner, User user) {
        super(connection, scanner, user);
    }

    private void editUsername(){
        System.out.println("Edit username:");
        System.out.println("Username is:" + user.getUsername());
        System.out.println("Enter new username:");

        String username = scanner.next();

        user.setUsername(username);
    }

    private void editPassword(){
        System.out.println("Edit password:");
        System.out.println("Password is:" + user.getPassword());
        System.out.println("Enter new password:");

        String password = scanner.next();

        user.setPassword(password);
    }

    private void editProfile(){
        while (true){
            System.out.println("Edit Profile:");
            System.out.println("1. Username");
            System.out.println("2. Password");
            System.out.println("0. Close");

            int choice = scanner.nextInt();

            if(choice == 0){
                break;
            }else if(choice == 1){
                editUsername();
            }else if(choice == 2){
                editPassword();
            }
        }
    }

    private void showProfile(){
        System.out.println("Your Profile:");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        System.out.println("Role: " + user.getRole());

        System.out.println("Press enter to close");
        scanner.next();
    }

    private void deleteProfile(){
        Request request = new Request("REMOVE_USER");
        request.setUser(user);

        connection.sendRequest(request);
    }

    private void showService(){
        while (true) {
            System.out.println("Profile");
            System.out.println("1. Show");
            System.out.println("2. Edit");
            System.out.println("3. Delete");
            System.out.println("0. Close");

            int choice = scanner.nextInt();

            if(choice == 0){
                break;
            }else if(choice == 1){
                showProfile();
            }else if(choice == 2){
                editProfile();
            }else if(choice == 3){
                deleteProfile();
                break;
            }
        }
    }

    public void getProfile(){
        showService();
    }
}
