package com.arpannandi.shoppingapp.dto;

import com.arpannandi.shoppingapp.model.Sex;
import com.arpannandi.shoppingapp.model.UserPreference;

import java.io.Serializable;
import java.util.Objects;

public class UserDto implements Serializable {
    private String email;
    private String password;
    private String name;
    private UserPreference preference;
    private Sex gender;
    private String contact;
    private String role;


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

    public void setRole(String role) {
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

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto entity = (UserDto) o;
        return Objects.equals(this.email, entity.email) &&
                Objects.equals(this.password, entity.password) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.preference, entity.preference) &&
                Objects.equals(this.gender, entity.gender) &&
                Objects.equals(this.role, entity.role)&&
                Objects.equals(this.contact, entity.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, name, preference, gender, contact, role);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "email = " + email + ", " +
                "password = " + password + ", " +
                "Name = " + name + ", " +
                "preference = " + preference + ", " +
                "gender = " + gender + ", " +
                "contact = " + contact + ")"+
                "role = " + role + ")"
                ;
    }
}
