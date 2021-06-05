package com.ivash.boot_ivasha.service;


import com.ivash.boot_ivasha.dao.RolesDao;
import com.ivash.boot_ivasha.dao.UserDao;
import com.ivash.boot_ivasha.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

@Component
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final RolesDao rolesDao;

    public UserServiceImpl(UserDao userDao, RolesDao rolesDao) {
        this.userDao = userDao;
        this.rolesDao = rolesDao;
    }

    public void getUsers(ModelMap map, Authentication authentication) {
        if (authentication != null) {
            map.addAttribute("user", getShowUser(authentication.getName()));
        }
    }

    public User getShowUser(String name) {
        return userDao.show(name);
    }

    @Transactional
    public void create(User user) {

        if (user.getRole()!=null && user.getRole().contains("admin")) {
            user.addRoles(rolesDao.getAdminRole());
        }
        user.addRoles(rolesDao.getUserRole());
        PasswordEncoder encoder = new BCryptPasswordEncoder(12);
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.addUser(user);
    }

    public User showUser(long id ) {
        System.out.println(userDao.show(id));
        return userDao.show(id);
    }

    public void adminRole(ModelMap map ,Authentication authentication) {
        map.addAttribute("users", userDao.userList());
        map.addAttribute("viewer",getShowUser(authentication.getName()));
    }

    public void newUser(ModelMap map) {
        map.addAttribute("user", new User());
    }

    public void edit(long id, ModelMap map) {
        map.addAttribute("user", userDao.show(id));
    }

    public void delete(long id) {
        userDao.deleteUser(userDao.show(id));
    }

    public void update(long id, User user) {
        if(user.getRole()!=null && user.getRole().contains("admin")){
            user.addRoles(rolesDao.getAdminRole());
        }
        user.addRoles(rolesDao.getUserRole());
        userDao.updateUser(id, user);
    }


}
