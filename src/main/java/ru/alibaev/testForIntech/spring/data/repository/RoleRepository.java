package ru.alibaev.testForIntech.spring.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alibaev.testForIntech.model.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Roles findByRole(String role);
}
