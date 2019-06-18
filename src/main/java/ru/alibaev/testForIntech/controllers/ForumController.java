package ru.alibaev.testForIntech.controllers;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ru.alibaev.testForIntech.model.Themes;
import ru.alibaev.testForIntech.model.Users;
import ru.alibaev.testForIntech.spring.data.repository.MessageRepository;
import ru.alibaev.testForIntech.spring.data.repository.RoleRepository;
import ru.alibaev.testForIntech.spring.data.repository.ThemeRepository;
import ru.alibaev.testForIntech.spring.data.repository.UserRepository;

import java.util.Optional;

@Data

public class ForumController {

    private MessageRepository messageRepo;
    private ThemeRepository themeRepo;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    Logger logger = LoggerFactory.getILoggerFactory().getLogger(getClass().getName());

    public ForumController(MessageRepository messageRepo, ThemeRepository themeRepo, UserRepository userRepository, RoleRepository roleRepository) {
        this.messageRepo = messageRepo;
        this.themeRepo = themeRepo;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    String getAuthenticatedUserName () {
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    String getAuthenticatedUserRole () {
        return userRepository.findByLogin(getAuthenticatedUserName()).getAuthority().getRole();
    }

    Themes getTheme(String themeId) {
        Themes theme = null;
        Optional<Themes> optThemes= getThemeRepo().findById(Integer.parseInt(themeId));
        if (optThemes.isPresent()) theme = optThemes.get();
        return theme;
    }

    Users findUserByLogin(String login) {
        try{
            return getUserRepository().findByLogin(login);
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
        }
        return null;
    }
}

