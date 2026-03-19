package com.example.library.service;

import com.example.library.model.User;
import com.example.library.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    // REGISTER
    public User register(String email, String password, String role) {

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        return repo.save(user);
    }

    // LOGIN
    public User login(String email, String password) {
        User user = repo.findByEmail(email).orElseThrow();

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

    // GET USER
    public User findByEmail(String email) {
        return repo.findByEmail(email).orElseThrow();
    }

    // ✅ FIX: DELETE ALL USERS
    public void deleteAllUsers() {
        repo.deleteAll();
    }
}