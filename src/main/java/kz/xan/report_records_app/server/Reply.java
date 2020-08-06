package kz.xan.report_records_app.server;

import kz.xan.report_records_app.domain.Record;
import kz.xan.report_records_app.domain.User;

import java.io.Serializable;
import java.util.Set;

public class Reply implements Serializable {
    private String code;

    private User user;

    private Long userID;

    private Set<Record> records;

    public Reply() {
    }

    public Reply(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }
}
