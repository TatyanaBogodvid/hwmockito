package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryTest {
    //Получение пустого списка пользователей → должен возвращаться пустой список.
    @Test
    public void gettingAnEmptyListOfUsers(){
        UserRepository userRepository = new UserRepository();
        List<User> expected = userRepository.getAllUsers();
        List<User> actual = new ArrayList<>();
        Assertions.assertEquals(expected, actual);
    }

    //Получение списка пользователей при изначально заполненном сервисе → должны возвращаться те же самые пользователи которых добавляли.
    @Test
    public void gettingListOfUsersWithInitiallyFilledService(){
        UserRepository userRepository = new UserRepository();
        User user1= new User("login1", "password1");
        User user2= new User("login2", "password2");
        User user3= new User("login3", "password3");
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        userRepository.addUser(user3);

        List<User> expected = userRepository.getAllUsers();
        List<User> actual = new ArrayList<>();
        actual.add(user1);
        actual.add(user2);
        actual.add(user3);

        Assertions.assertEquals(expected, actual);
    }

    //Поиск пользователя по логину → в случае если такой пользователь есть.
    @Test
    public void searchByLoginWhenUserExists(){
        UserRepository userRepository = new UserRepository();
        User user1= new User("login1", "password1");
        User user2= new User("login2", "password2");
        User user3= new User("login3", "password3");
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        userRepository.addUser(user3);

        Optional<User> actual = userRepository.findByLogin("login1");
        Optional<User> expected = Optional.of(user1);
        Assertions.assertEquals(expected, actual);
    }

    //Поиск пользователя по логину → в случае когда такого пользователя нет.
    @Test
    public void searchByLoginWhenThereIsNoUser(){
        UserRepository userRepository = new UserRepository();
        User user1= new User("login1", "password1");
        User user2= new User("login2", "password2");
        User user3= new User("login3", "password3");
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        userRepository.addUser(user3);

        Optional<User> actual = userRepository.findByLogin("login");
        Optional<User> expected = Optional.of(new User(null, null));
        Assertions.assertEquals(expected, actual);
    }

    //Поиск пользователя по логину и паролю → в случае если такой пользователь есть.
    @Test
    public void searchByLoginWhenSuchUserExists(){
        UserRepository userRepository = new UserRepository();
        User user1= new User("login1", "password1");
        User user2= new User("login2", "password2");
        User user3= new User("login3", "password3");
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        userRepository.addUser(user3);

        Optional<User> actual = userRepository.findByLoginAndPassword("login1", "password1");
        Optional<User> expected = Optional.of(user1);
        Assertions.assertEquals(expected, actual);
    }

    //Поиск пользователя по логину и паролю → в случае когда пароль совпадает с одним из существующих, а логин - нет.
    @Test
    public void searchWhenPasswordAlreadyExists(){
        UserRepository userRepository = new UserRepository();
        User user1= new User("login1", "password1");
        User user2= new User("login2", "password2");
        User user3= new User("login3", "password3");
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        userRepository.addUser(user3);

        Optional<User> actual = userRepository.findByLoginAndPassword("login", "password1");
        Optional<User> expected = Optional.of(new User(null, null));
        Assertions.assertEquals(expected, actual);
    }

    //Поиск пользователя по логину и паролю - в случае когда логин совпадает с одним из существующих, а пароль - нет.
    @Test
    public void searchWhenLoginAlreadyExists(){
        UserRepository userRepository = new UserRepository();
        User user1= new User("login1", "password1");
        User user2= new User("login2", "password2");
        User user3= new User("login3", "password3");
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        userRepository.addUser(user3);

        Optional<User> actual = userRepository.findByLoginAndPassword("login1", "password");
        Optional<User> expected = Optional.of(new User(null, null));
        Assertions.assertEquals(expected, actual);
    }
}
