package com.example.demo;

import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<User> getUserList();
    void addUser(User userInfo);
    void updateUserById(User userInfo);
    void deleteUserById();
    List<User> getCurrentUserList();
    Page<User> getPageUserList();
}
