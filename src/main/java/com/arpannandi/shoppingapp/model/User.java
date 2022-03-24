package com.arpannandi.shoppingapp.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name="id", nullable = false)
    private String email;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="name",nullable = false)
    private String name;

    //seasonal or new arrivals
    @Column(name="preference",nullable = false)
    @Enumerated(EnumType.STRING)
    private UserPreference preference;

    @Column(name="gender",nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex gender;

    @Column(name="contact",nullable = false)
    private String contact;

    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "users_role",
//            joinColumns = @JoinColumn(
//                    name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "role_id", referencedColumnName = "id"))

    Role role;

    public User(){}

    public User(String email, String password, String name, UserPreference preference, Sex gender, String contact, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.preference = preference;
        this.gender = gender;
        this.contact = contact;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public UserPreference getPreference() {
        return preference;
    }

    public Sex getGender() {
        return gender;
    }

    public String getContact() {
        return contact;
    }

    public Role getRole() {
        return role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPreference(UserPreference preference) {
        this.preference = preference;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
