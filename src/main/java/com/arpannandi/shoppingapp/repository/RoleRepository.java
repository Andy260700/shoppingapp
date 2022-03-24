package com.arpannandi.shoppingapp.repository;

import com.arpannandi.shoppingapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
