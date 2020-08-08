package kz.xan.report_records_app.client.controllers.panel;

import kz.xan.report_records_app.client.controllers.BaseController;
import kz.xan.report_records_app.client.controllers.services.profile.ProfileController;
import kz.xan.report_records_app.client.controllers.services.records.RecordController;
import kz.xan.report_records_app.client.controllers.services.reports.ReportController;
import kz.xan.report_records_app.client.main.Connection;
import kz.xan.report_records_app.domain.User;

import java.io.IOException;
import java.util.Scanner;

public abstract class PanelController extends BaseController {
    protected User user;

    public PanelController(Connection connection, Scanner scanner, User user) {
        super(connection, scanner);
        this.user = user;
    }

    public void start(){
        showServices();
    }

    protected abstract void showServices();

    protected User getProfile(){
        ProfileController profileController = new ProfileController(connection, scanner, user);
        return profileController.getProfile();
    }

    protected void getRecords(){
        RecordController recordController = new RecordController(connection, scanner, user);
        recordController.getRecords();
    }

    protected void getReports() throws IOException {
        ReportController reportController = new ReportController(connection, scanner, user);
        reportController.getReports();
    }
}
