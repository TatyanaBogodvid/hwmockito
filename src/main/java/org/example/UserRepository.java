package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private List<User> users = new ArrayList<>();

    public User addUser(User user){
        users.add(user);
        return user;
    }

    public List<User> getAllUsers(){
        List<User> allUsers = new ArrayList<>();
        for(User user : users){
            allUsers.add(user);
        }
        return allUsers;
    }

    public Optional<User> findByLogin (String login){
        User result = new User(null, null);
        for(User user : users){
            if (login.equals(user.getLogin())){
                result = user;
            }
        }
        return Optional.of(result);
    }

    public Optional<User> findByLoginAndPassword (String login, String password){
        User result = new User(null, null);
        for(User user : users){
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())){
                result = user;
            }
        }
        return Optional.of(result);
    }



}
