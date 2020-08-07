package kz.xan.report_records_app.bootstrap;

import kz.xan.report_records_app.domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataLoader {
    private Set<User> users;
    private Set<Record> records;

    public DataLoader() {
        users = Stream.of(
                    new User("ali", "ali", RoleEnum.ADMIN),
                    new User("aa", "bb", RoleEnum.CLIENT)
                ).collect(Collectors.toCollection(HashSet::new));

        records = Stream.of(
                    new Record(LocalDate.now(), "a", "b", RecordState.TRADE_IN, RecordStatus.WITHDRAWAL, 2000, LocalDateTime.now(), 0L),
                    new Record(LocalDate.now(), "c", "d", RecordState.TRADE_IN, RecordStatus.WITHDRAWAL, 2000, LocalDateTime.now(), 2L),
                    new Record(LocalDate.of(2018,5, 21), "c", "d", RecordState.TRADE_IN, RecordStatus.WITHDRAWAL, 2000, LocalDateTime.now(), 2L)
                ).collect(Collectors.toCollection(HashSet::new));
    }

    public Set<User> getUsers() {

        return new HashSet<>(users);
    }

    public void addUser(User user){
        users.add(user);
    }

    public User getUserByUsernameAndPassword(String username, String password){
        User getUser = users.stream()
                    .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                    .findFirst().orElse(null);

        return getUser;
    }

    public void removeUser(User user){
        users.remove(user);
    }



    public Set<Record> getRecords(){

        return new HashSet<>(records);
    }

    public Set<Record> getRecordsByUserID(Long ID){

        System.out.println("GET RECORDS BY ID:" + ID);

        return records.stream()
                .filter(record -> record.getUserID().equals(ID))
                .sorted(Comparator.comparing(Record::getDate))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public void addRecord(Record record){
        records.add(record);
    }

    public void removeRecord(Record record){
        records.removeIf(record1 -> record1.getID().equals(record.getID()));
    }

    public void changeRecord(Record record){
        removeRecord(record);
        addRecord(record);
    }
}
