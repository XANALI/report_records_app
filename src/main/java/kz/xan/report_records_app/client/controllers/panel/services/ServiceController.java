package kz.xan.report_records_app.client.controllers.panel.services;

import kz.xan.report_records_app.client.controllers.BaseController;
import kz.xan.report_records_app.client.main.Connection;
import kz.xan.report_records_app.domain.User;

import java.util.Scanner;

public class ServiceController extends BaseController {
    protected final User user;

    public ServiceController(Connection connection, Scanner scanner, User user) {
        super(connection, scanner);
        this.user = user;
    }
}
