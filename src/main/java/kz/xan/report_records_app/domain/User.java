package kz.xan.report_records_app.domain;

public class User extends BaseEntity {
    private static Long userID = 0L;

    private String username;
    private String password;
    private RoleEnum role;

    public User() {
        super(userID++);
    }

    public User(Long ID) {
        super(ID);
    }

    public User(String username, String password, RoleEnum role) {
        super(userID++);
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(Long ID, String username, String password, RoleEnum role) {
        super(ID);
        this.username = username;
        this.password = password;
        this.role = role;
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

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
