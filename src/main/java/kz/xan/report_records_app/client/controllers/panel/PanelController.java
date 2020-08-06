package kz.xan.report_records_app.client.controllers.panel;

import kz.xan.report_records_app.client.controllers.BaseController;
import kz.xan.report_records_app.client.controllers.panel.services.profile.ProfileController;
import kz.xan.report_records_app.client.controllers.panel.services.records.RecordController;
import kz.xan.report_records_app.client.main.Connection;
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
    }

    protected abstract void showServices();

    protected void getProfile(){
        ProfileController profileController = new ProfileController(connection, scanner, user);
        profileController.getProfile();
    }

    protected void getRecords(){
        RecordController recordController = new RecordController(connection, scanner, user);
        recordController.getRecords();
    }
}
