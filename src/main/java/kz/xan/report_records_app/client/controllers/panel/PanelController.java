package kz.xan.report_records_app.client.controllers.panel;

import kz.xan.report_records_app.client.Connection;
import kz.xan.report_records_app.client.controllers.BaseController;
import kz.xan.report_records_app.domain.User;

import java.util.Scanner;

public abstract class PanelController extends BaseController {
    protected final User user;

    public PanelController(Connection connection, Scanner scanner, User user) {
        super(connection, scanner);
        this.user = user;
    }

    public void start(){
        showServices();
    };

    protected abstract void showServices();

    protected void showProfile(){
        ProfileController profileController = new ProfileController(connection, scanner, user);
        profileController.getProfile();
    };
}
