package org.example;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser (String login, String password){
        User user = new User(login, password);
        boolean userExist = this.userRepository
                .getAllUsers()
                .stream()
                .anyMatch(t -> t.equals(user));
        if(userExist){
            throw new IllegalArgumentException("Пользователь с таким логином существует");
        }
        if (login == null || password == null){
            throw new IllegalArgumentException("Логин и пароль не должны быть пустыми");
        }
        userRepository.addUser(user);
    }

    public List<String> getAllLogin (UserRepository userRepository){
        List<User> users = userRepository.getAllUsers();
        List<String> logins = new ArrayList<>();
        for(User user : users){
            logins.add(user.getLogin());
        }
        return logins;
    }
}
