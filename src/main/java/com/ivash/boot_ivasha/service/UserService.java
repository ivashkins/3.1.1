package com.ivash.boot_ivasha.service;


import com.ivash.boot_ivasha.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


public interface UserService {
     ModelAndView getUsers(Authentication authentication);

     ModelAndView adminPage(Authentication authentication);

     User getShowUser(String name);

     void create(User user);

     User showUser(long id);

     List<User> adminRole();

     void newUser(ModelMap map);

     void edit(long id);

     void delete(long id);

     void update(long id, User user);
}
