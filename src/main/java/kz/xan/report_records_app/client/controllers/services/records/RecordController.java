package kz.xan.report_records_app.client.controllers.services.records;

import kz.xan.report_records_app.client.controllers.services.ServiceController;
import kz.xan.report_records_app.client.main.Connection;
import kz.xan.report_records_app.client.main.Request;
import kz.xan.report_records_app.domain.Record;
import kz.xan.report_records_app.domain.RecordState;
import kz.xan.report_records_app.domain.RecordStatus;
import kz.xan.report_records_app.domain.User;
import kz.xan.report_records_app.server.Reply;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RecordController extends ServiceController {

    public RecordController(Connection connection, Scanner scanner, User user) {
        super(connection, scanner, user);
    }

    private LocalDate addRecordDate(){
        System.out.println("Date:");

        System.out.println("Day:");
        int day = scanner.nextInt();

        System.out.println("Month:");
        int month = scanner.nextInt();

        System.out.println("Year:");
        int year = scanner.nextInt();

        return LocalDate.of(year, month, day);
    }

    private String addCarModel(){
        while (true) {
            System.out.println("Car Model:");
            String carModel = scanner.nextLine();

            if(!carModel.isEmpty()){
                return carModel;
            }
        }
    }

    private String addVinCode(){
        while (true) {
            System.out.println("Vin Code:");
            String vinCode = scanner.nextLine();

            if(!vinCode.isEmpty()){
                return vinCode;
            }
        }
    }

    private RecordState addRecordState(){
        RecordState[] recordStates = RecordState.values();
        while (true) {
            System.out.println("Record State:");

            int i = 1;
            for(RecordState recordState : recordStates){
                System.out.println(i + ". " + recordState);
            }

            int choice = scanner.nextInt();
            if(choice > 0 && choice < recordStates.length + 1){
                return recordStates[choice-1];
            }
        }
    }

    private RecordStatus addRecordStatus(){
        RecordStatus[] recordStatuses = RecordStatus.values();
        while (true) {
            System.out.println("Record Status:");

            int i = 1;
            for(RecordStatus recordStatus : recordStatuses){
                System.out.println(i + ". " + recordStatus);
            }

            int choice = scanner.nextInt();
            if(choice > 0 && choice < recordStatuses.length + 1){
                return recordStatuses[choice-1];
            }
        }
    }

    private Integer addSum(){
        while (true) {
            System.out.println("Sum:");
            Integer sum = scanner.nextInt();

            if (sum > 0) {
                return sum;
            }
        }
    }

    private void addRecord(){
        System.out.println("Adding record");

        LocalDate date = addRecordDate();

        String carModel = addCarModel();

        String vinCode = addVinCode();

        RecordState recordState = addRecordState();

        RecordStatus recordStatus = addRecordStatus();

        Integer sum = addSum();

        LocalDateTime recordDateTime = LocalDateTime.now();

        Long userID = user.getID();

        Record record = new Record();
        record.setDate(date);
        record.setCarModel(carModel);
        record.setVin(vinCode);
        record.setRecordState(recordState);
        record.setRecordStatus(recordStatus);
        record.setSum(sum);
        record.setRecordDateTime(recordDateTime);
        record.setUserID(userID);

        Request request = new Request("ADD_RECORD");
        request.setRecord(record);

        connection.sendRequest(request);
    }



    private Record chooseRecord(){
        Request request = new Request("GET_RECORDS_BY_USER_ID");
        request.setUserID(user.getID());

        connection.sendRequest(request);

        Reply reply = connection.getReply();
        if(reply.getCode().equals("RECORDS_NOT_FOUND")){
            System.out.println("RECORDS NOT FOUND");
            return null;
        }

        // converting set to arrayList
        Map<Long, Record> records = reply.getRecords();
        List<Record> recordsList = new ArrayList<>(records.values());
        while (true) {
            System.out.println("Choose Record");

            int i = 1;
            for(Record record : recordsList){
                System.out.println(i++ + ". " + record);
            }

            System.out.println("0. Close");

            int choice = scanner.nextInt();
            if(choice > 0 && choice <= recordsList.size()){
                return recordsList.get(choice - 1);
            }else if(choice == 0){
                return null;
            }
        }
    }



    private void removeRecord(){
        Record record = chooseRecord();

        if(record != null){
            Request request = new Request("REMOVE_RECORD");
            request.setRecord(record);

            connection.sendRequest(request);
        }
    }



    private void editRecordDate(Record record){
        System.out.println("Old Date: " + record.getDate());

        LocalDate recordDate = addRecordDate();
        record.setDate(recordDate);
    }

    private void editRecordCarModel(Record record){
        System.out.println("Old Car model: " + record.getCarModel());

        String carModel = addCarModel();
        record.setCarModel(carModel);
    }

    private void editRecordVINCode(Record record){
        System.out.println("Old VIN code: " + record.getVin());

        String vinCode = addVinCode();
        record.setVin(vinCode);
    }

    private void editRecordState(Record record){
        System.out.println("Old Record state: " + record.getRecordState());

        RecordState recordState = addRecordState();
        record.setRecordState(recordState);
    }

    private void editRecordStatus(Record record){
        System.out.println("Old Record status: " + record.getRecordStatus());

        RecordStatus recordStatus = addRecordStatus();
        record.setRecordStatus(recordStatus);
    }

    private void editRecordSum(Record record){
        System.out.println("Old Record sum: " + record.getSum());

        Integer sum = addSum();
        record.setSum(sum);
    }

    private void editRecord(){
        Record record = chooseRecord();
        if(record == null){
            return;
        }

        while(true) {
            System.out.println("Edit Record:");

            System.out.println("1. Edit Date");
            System.out.println("2. Edit Car Model");
            System.out.println("3. Edit VIN code");
            System.out.println("4. Edit Record state");
            System.out.println("5. Edit Record status");
            System.out.println("6. Edit Sum");
            System.out.println("0. Close");

            int choice = scanner.nextInt();
            if(choice == 0){
                break;
            }else if(choice == 1){
                editRecordDate(record);
            }else if(choice == 2){
                editRecordCarModel(record);
            }else if(choice == 3){
                editRecordVINCode(record);
            }else if(choice == 4){
                editRecordState(record);
            }else if(choice == 5){
                editRecordStatus(record);
            }else if(choice == 6){
               editRecordSum(record);
            }
        }

        Request request = new Request("SAVE_EDITED_RECORD");
        request.setRecord(record);

        connection.sendRequest(request);

    }


    private void showRecords(){
        Request request = new Request("GET_RECORDS_BY_USER_ID");
        request.setUserID(user.getID());

        connection.sendRequest(request);

        Reply reply = connection.getReply();
        if(reply.getCode().equals("RECORDS_NOT_FOUND")){
            System.out.println("RECORDS NOT FOUND");
            return;
        }

        // converting set to arrayList
        List<Record> recordsList = new ArrayList<>(reply.getRecords().values());
        for(Record record : recordsList){
            System.out.println(record);
        }
    }

    private void showFunctionality(){
        while (true) {
            System.out.println("RECORDS");
            System.out.println("1. Add Record");
            System.out.println("2. Remove Record");
            System.out.println("3. Edit Record");
            System.out.println("4. Show records");
            System.out.println("0. Close");

            int choice = scanner.nextInt();

            if(choice == 0){
                break;
            }else if(choice == 1){
                addRecord();
            }else if(choice == 2){
                removeRecord();
            }else if(choice == 3){
                editRecord();
            }else if(choice == 4){
                showRecords();
            }
        }
    }

    public void getRecords(){
        showFunctionality();
    }
}
