package com.arpannandi.shoppingapp.model;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long roleId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Role(){}

    public Role(String name) {
        this.name = name;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
