package kz.xan.report_records_app.client;

import java.io.Serializable;

public class Request implements Serializable {
    private String code;

    private String username;
    private String password;

    public Request() {
    }

    public Request(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
