package ru.alibaev.testForIntech.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alibaev.testForIntech.model.Themes;
import ru.alibaev.testForIntech.model.Users;
import ru.alibaev.testForIntech.spring.data.repository.MessageRepository;
import ru.alibaev.testForIntech.spring.data.repository.RoleRepository;
import ru.alibaev.testForIntech.spring.data.repository.ThemeRepository;
import ru.alibaev.testForIntech.spring.data.repository.UserRepository;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/themes")
public class ThemesController extends ForumController {



    public ThemesController(MessageRepository messageRepo, ThemeRepository themeRepo, UserRepository userRepository, RoleRepository roleRepository) {
        super(messageRepo, themeRepo, userRepository, roleRepository);
    }

    @GetMapping
    public String getThemes(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Themes> themes = null;
        try{
            themes = getThemeRepo().findAll(PageRequest.of(page, 5, Sort.by("createdTimeStamp").descending()));
        } catch(DataAccessException dae){
            logger.error(dae.getMessage());
        }
        model.addAttribute("themes", themes);
        model.addAttribute("currentPage", page);
        model.addAttribute("currentUserRole", getAuthenticatedUserRole());
        return "themes";
    }

    @PostMapping("/delete")
    public String delete (String id) {
        if (getAuthenticatedUserRole().equals("ROLE_ADMIN")) {
            try{
                getThemeRepo().deleteById(Integer.parseInt(id));
            } catch (DataAccessException dae) {
                logger.error(dae.getMessage());
            }
        }
        return "redirect:/themes";
    }

    @PostMapping("/save")
    public String save (String newThemeName) {
        Themes theme = new Themes();
        Themes themes;
        Users user = findUserByLogin(getAuthenticatedUserName());
        if(user != null && !newThemeName.equals("")) {
            theme.setTheme_name(newThemeName);
            theme.setThemeAuthor(user);
            theme.setCreatedTimeStamp(LocalDateTime.now());
            themes = getThemeRepo().save(theme);
            return "redirect:/messages/" + (themes != null ? Integer.toString(themes.getId()) : "1");
        }
        return "redirect:/themes";
    }
}
