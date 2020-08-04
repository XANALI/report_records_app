package kz.xan.report_records_app.client.controllers;

import kz.xan.report_records_app.client.Connection;

import java.util.Scanner;

public class BaseController {

    protected final Connection connection;
    protected final Scanner scanner;


    public BaseController(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }
}
