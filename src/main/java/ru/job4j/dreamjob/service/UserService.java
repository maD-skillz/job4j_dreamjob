package ru.job4j.dreamjob.service;


import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.store.UserDbStore;

import java.util.*;

@Service
public class UserService {

    private final UserDbStore store;

    public UserService(UserDbStore store) {
        this.store = store;
    }

    public Optional<User> addUser(User user) {
        return user != null ? Optional.of(store.add(user)) : Optional.empty();
    }

    public Collection<User> findAllUsers() {
        return store.findAll();
    }

    public void updateUser(User user) {
        store.update(user);
    }
}