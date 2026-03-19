package com.example.library.service;

import com.example.library.model.User;
import com.example.library.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    // 🔐 Password encoder
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // ✅ REGISTER USER
    public User register(String email, String password, String role) {

        User user = new User();
        user.setEmail(email);

        // 🔐 Encrypt password
        user.setPassword(encoder.encode(password));

        user.setRole(role);

        return repository.save(user);
    }

    // ✅ LOGIN USER
    public User login(String email, String password) {

        User user = repository.findByEmail(email);

        // 🔐 Check encrypted password
        if (user != null && encoder.matches(password, user.getPassword())) {
            return user;
        }

        return null;
    }

    // (Optional)
    public void deleteAllUsers() {
        repository.deleteAll();
    }
}