package kz.xan.report_records_app.bootstrap;

import kz.xan.report_records_app.domain.RoleEnum;
import kz.xan.report_records_app.domain.User;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataLoader {
    private Set<User> users;

    public DataLoader() {
        users = Stream.of(
                    new User("ali", "ali", RoleEnum.ADMIN),
                    new User("aa", "bb", RoleEnum.CLIENT)
                ).collect(Collectors.toCollection(HashSet::new));
    }

    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }

    public User getUserByUsernameAndPassword(String username, String password){
        Set<User> u = users.stream()
                .filter(user ->
                        user.getUsername().equals(username) && user.getPassword().equals(password))
                .collect(Collectors.toCollection(HashSet::new));

        return u.stream().findFirst().orElse(null);
    }
}
