package com.ivash.boot_ivasha.dao;



import com.ivash.boot_ivasha.model.User;

import java.util.List;


public interface UserDao {
    void addUser(User user);
    void deleteUser(User user);
    void updateUser(long id,User user);
    List<User> userList();
    User show(long id);
    User show(String name);
}
