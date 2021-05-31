package com.ivash.boot_ivasha.service;


import com.ivash.boot_ivasha.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.ui.ModelMap;


public interface UserService {
    public void getUsers(ModelMap map, Authentication authentication);

    public User getShowUser(String name);

    public void create(User user);

    public void showUser(long id, ModelMap map);

    public void adminRole(ModelMap map);

    public void newUser(ModelMap map);

    public void edit(long id, ModelMap map);

    public void delete(long id);

    public void update(long id, User user);
}
