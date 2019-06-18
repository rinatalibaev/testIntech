package ru.alibaev.testForIntech.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.alibaev.testForIntech.model.Roles;
import ru.alibaev.testForIntech.model.Users;
import ru.alibaev.testForIntech.spring.data.repository.MessageRepository;
import ru.alibaev.testForIntech.spring.data.repository.RoleRepository;
import ru.alibaev.testForIntech.spring.data.repository.ThemeRepository;
import ru.alibaev.testForIntech.spring.data.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/registration")
public class RegController extends ForumController{

    public RegController(MessageRepository messageRepo, ThemeRepository themeRepo, UserRepository userRepository, RoleRepository roleRepository) {
        super(messageRepo, themeRepo, userRepository, roleRepository);
    }

    @PostMapping(value="/save", produces = "application/json; charset=utf-8")
    public @ResponseBody
    ResponseEntity<String> save (@RequestBody Users userBody) {
        List<Users> users = getUserRepository().findAll();
        if (users != null && !users.stream().filter(user -> user.getLogin().equals(userBody.getLogin())).collect(Collectors.toList()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        Users user = new Users();
        Roles role = null;
        try {
            role = getRoleRepository().findByRole("ROLE_USER");
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
        }
        if (userBody == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else if (role == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        String encodedPassword = new BCryptPasswordEncoder().encode(userBody.getPassword());
        user.setLogin(userBody.getLogin());
        user.setUsername(userBody.getUsername());
        user.setSurname(userBody.getSurname());
        user.setEnabled(userBody.getEnabled());
        user.setPassword(encodedPassword);
        user.setAuthority(role);
        getUserRepository().save(user);
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }
}
