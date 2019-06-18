package ru.alibaev.testForIntech.spring.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.alibaev.testForIntech.model.Messages;
import ru.alibaev.testForIntech.model.Themes;

public interface MessageRepository extends JpaRepository<Messages,Integer> {
    Page<Messages> findAllByMessagetheme (Themes theme, Pageable pageable);
}
