package com.ivash.boot_ivasha.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email")
    private String email;




    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles=new LinkedHashSet<>();

    public User() {
    }
    public void addRoles(Role role){
        roles.add(role);
    }

    public User(String name, String password, String lastName, String email) {
        this.name = name;
        this.password = password;
        this.lastName = lastName;
        this.email = email;
    }


    @Override
    public String toString() {
        return "{ " +
                "id: " + id + " ," +
                " name: " + name  + " ," +
                " password: " + password + " ," +
                " lastName: " + lastName + " , " +
                " email: " + email + " ," +
                " roles: " + roles +
                " }";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(s->new SimpleGrantedAuthority(s.getAuthority())).collect(Collectors.toSet());
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @JsonBackReference
    public Set<Role> getRoleSet() {
        if(roles==null){
            roles=new LinkedHashSet<>();
        }
        return roles;
    }
    public String getRoles(){
        String s="";
        List<Role> roles = this.roles.stream().sorted((Comparator.comparing(Role::getId))).collect(Collectors.toList());
        for (Role value : roles) {
            s = s + value.getRole().toUpperCase(Locale.ROOT) + " ";
        }
        return s;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roles = roleSet;
    }

    @Transient
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
