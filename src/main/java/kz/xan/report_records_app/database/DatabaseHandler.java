package kz.xan.report_records_app.database;

import kz.xan.report_records_app.domain.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHandler extends DatabaseConfig {
    private Connection connection;

    public DatabaseHandler() {
        String dbURL = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbDatabase;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            createTable();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    private void createTable() throws SQLException {
        PreparedStatement preparedStatement;

        preparedStatement = connection.prepareStatement(CREATE_USER_TABLE);
        preparedStatement.executeUpdate();
        preparedStatement.close();

        preparedStatement = connection.prepareStatement(CREATE_RECORD_TABLE);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    private User userForm(ResultSet resultSet) throws SQLException {
        Integer ID = resultSet.getInt(1);
        String username = resultSet.getString(2);
        String password = resultSet.getString(3);
        String role = resultSet.getString(4);

        return new User(ID.longValue(), username, password, RoleEnum.valueOf(role));
    }

    public void addUser(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER);
        preparedStatement.setInt(1, user.getID().intValue());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getRoleString());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public User getUser(Long ID) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_USER);
        preparedStatement.setInt(1, ID.intValue());
        ResultSet resultSet = preparedStatement.executeQuery();
        if(!resultSet.next()){
            return null;
        }

        User user = userForm(resultSet);

        preparedStatement.close();

        return user;
    }

    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_USERNAME_PASSWORD);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(!resultSet.next()){
            return null;
        }

        User user = userForm(resultSet);

        preparedStatement.close();

        return user;
    }

    public Map<Long, User> getAllUsers() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet == null){
            return null;
        }

        Map<Long, User> users = new HashMap<>();
        while (resultSet.next()){
            User user = userForm(resultSet);
            users.put(user.getID(), user);
        }

        preparedStatement.close();

        return users;
    }

    public void removeUser(User user) throws SQLException {
        removeUser(user.getID());
    }

    public void removeUser(Long ID) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER);
        preparedStatement.setInt(1, ID.intValue());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    private Record recordForm(ResultSet resultSet) throws SQLException {
        if(resultSet == null){
            return null;
        }

        Integer ID = resultSet.getInt(1);
        String date = resultSet.getString(2);
        String carModel = resultSet.getString(3);
        String vinCode = resultSet.getString(4);
        String recordState = resultSet.getString(5);
        String recordStatus = resultSet.getString(6);
        Integer sum = resultSet.getInt(7);
        String recordDateTime = resultSet.getString(8);
        Integer userID = resultSet.getInt(9);

        return new Record(ID.longValue(), LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy")), carModel, vinCode, RecordState.valueOf(recordState), RecordStatus.valueOf(recordStatus), sum, LocalDateTime.parse(recordDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME), userID.longValue());
    }

    public void addRecord(Record record) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_RECORD);
        preparedStatement.setInt(1, record.getID().intValue());
        preparedStatement.setString(2, record.getDateString());
        preparedStatement.setString(3, record.getCarModel());
        preparedStatement.setString(4, record.getVin());
        preparedStatement.setString(5, record.getRecordState().name());
        preparedStatement.setString(6, record.getRecordStatus().name());
        preparedStatement.setInt(7, record.getSum());
        preparedStatement.setString(8, record.getDateTimeString());
        preparedStatement.setInt(9, record.getUserID().intValue());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public Map<Long, Record> getRecordsByUserID(Long ID) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_RECORDS_BY_USER_ID);
        preparedStatement.setInt(1, ID.intValue());
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet == null){
            return null;
        }

        Map<Long, Record> records = new HashMap<>();
        Record record;
        while (resultSet.next()) {
            record = recordForm(resultSet);

            if(record != null){
                records.put(record.getID(), record);
            }
        }

        return records;
    }

    public void removeRecord(Record record) throws SQLException {
        removeRecord(record.getID());
    }

    public void removeRecord(Long ID) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_RECORD);
        preparedStatement.setInt(1, ID.intValue());
        preparedStatement.executeUpdate();

        preparedStatement.close();
    }

    public void changeRecord(Record record) throws SQLException {
        removeRecord(record);
        addRecord(record);
    }
}
