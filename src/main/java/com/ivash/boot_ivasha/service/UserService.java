package com.ivash.boot_ivasha.service;


import com.ivash.boot_ivasha.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.ui.ModelMap;


public interface UserService {
     void getUsers(ModelMap map, Authentication authentication);

     User getShowUser(String name);

     void create(User user);

     User showUser(long id);

     void adminRole(ModelMap map,Authentication authentication);

     void newUser(ModelMap map);

     void edit(long id, ModelMap map);

     void delete(long id);

     void update(long id, User user);
}
