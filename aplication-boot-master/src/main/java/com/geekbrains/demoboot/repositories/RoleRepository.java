package com.geekbrains.demoboot.repositories;

import com.geekbrains.demoboot.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
