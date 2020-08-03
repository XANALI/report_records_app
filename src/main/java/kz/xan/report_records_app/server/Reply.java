package kz.xan.report_records_app.server;

import kz.xan.report_records_app.domain.User;

import java.io.Serializable;

public class Reply implements Serializable {
    private String code;

    private User user;

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
}
