package ru.alibaev.testForIntech.spring.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alibaev.testForIntech.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByLogin(String login);
}
