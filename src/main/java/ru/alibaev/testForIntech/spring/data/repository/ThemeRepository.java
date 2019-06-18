package ru.alibaev.testForIntech.spring.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alibaev.testForIntech.model.Themes;

public interface ThemeRepository extends JpaRepository<Themes, Integer> {

}
