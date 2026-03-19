package com.example.library.controller;

import com.example.library.model.User;

public class CurrentUser {

    private static User user;

    public static void set(User u) {
        user = u;
    }

    public static User get() {
        return user;
    }

    public static void clear() {
        user = null;
    }
}