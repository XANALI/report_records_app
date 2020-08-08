package kz.xan.report_records_app.client.controllers.services.reports;

import kz.xan.report_records_app.client.controllers.services.ServiceController;
import kz.xan.report_records_app.client.main.Connection;
import kz.xan.report_records_app.client.main.Request;
import kz.xan.report_records_app.domain.Record;
import kz.xan.report_records_app.domain.User;
import kz.xan.report_records_app.server.Reply;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReportController extends ServiceController {
    public ReportController(Connection connection, Scanner scanner, User user) {
        super(connection, scanner, user);
    }

    private Map<Long, Record> getUserRecords(){
        Request request = new Request("GET_RECORDS_BY_USER_ID");
        request.setUserID(user.getID());
        connection.sendRequest(request);

        Reply reply = connection.getReply();
        Map<Long, Record> records = null;
        if(reply.getCode().equals("RECORDS_FOUND")){
            records = reply.getRecords();
        }

        return records;
    }

    private void getReportAllRecords() throws IOException {
        String file = "C://Users/patro/Desktop/report.xlsx";

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Report");

        Map<Long, Record> records = getUserRecords();
        if(records == null){
            return;
        }

        List<Long> rows = new ArrayList<>(records.keySet());

        int recordID = 1;
        int rowID = 0;
        for(Long row : rows){
            Row newRow = sheet.createRow(rowID++);

            int cellID = 0;
            Record record = records.get(row);

            Cell id = newRow.createCell(cellID++);
            id.setCellValue(String.valueOf(recordID++));

            Cell date = newRow.createCell(cellID++);
            date.setCellValue(record.getDateString());

            sheet.autoSizeColumn(1);

            Cell carModel = newRow.createCell(cellID++);
            carModel.setCellValue(record.getCarModel());

            sheet.autoSizeColumn(2);

            Cell vinCode = newRow.createCell(cellID++);
            vinCode.setCellValue(record.getVin());

            sheet.autoSizeColumn(3);

            Cell recordState = newRow.createCell(cellID++);
            recordState.setCellValue(record.getRecordState().name());

            sheet.autoSizeColumn(4);

            Cell recordStatus = newRow.createCell(cellID++);
            recordStatus.setCellValue(record.getRecordStatus().name());

            sheet.autoSizeColumn(5);

            Cell sum = newRow.createCell(cellID);
            sum.setCellValue(record.getSum());
        }

        FileOutputStream recordFile = new FileOutputStream(file);
        workbook.write(recordFile);
        recordFile.close();
        workbook.close();
    }

    private void showReportServices() throws IOException {
        while (true) {
            System.out.println("Report Services");
            System.out.println("1. Get Report of all Records");
            System.out.println("0. Close");

            int choice = scanner.nextInt();
            if(choice == 0){
                break;
            }else if(choice == 1){
                getReportAllRecords();
            }
        }
    }

    public void getReports() throws IOException {
        showReportServices();
    }
}
