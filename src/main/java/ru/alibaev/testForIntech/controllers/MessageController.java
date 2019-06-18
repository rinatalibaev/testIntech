package ru.alibaev.testForIntech.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alibaev.testForIntech.model.Messages;
import ru.alibaev.testForIntech.model.Users;
import ru.alibaev.testForIntech.spring.data.repository.MessageRepository;
import ru.alibaev.testForIntech.spring.data.repository.RoleRepository;
import ru.alibaev.testForIntech.spring.data.repository.ThemeRepository;
import ru.alibaev.testForIntech.spring.data.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/messages")
public class MessageController extends ForumController {

    public MessageController(MessageRepository messageRepo, ThemeRepository themeRepo, UserRepository userRepository, RoleRepository roleRepository) {
        super(messageRepo, themeRepo, userRepository, roleRepository);
    }

    @GetMapping("/{themeId}")
    public String getMessages(@PathVariable("themeId") int intTheme, @RequestParam(defaultValue = "0") int page, Model model) {
        Page<Messages> messages = null;
        try{
            messages = getMessageRepo().findAllByMessagetheme(getTheme(Integer.toString(intTheme)), PageRequest.of(page, 4, Sort.by("messageTimestamp").descending()));
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
        }
        model.addAttribute("themeMessages", messages);
        model.addAttribute("theme", getTheme(Integer.toString(intTheme)));
        model.addAttribute("currentPage", page);
        model.addAttribute("currentUserLogin", findUserByLogin(getAuthenticatedUserName()).getLogin());
        model.addAttribute("currentUserRole", getAuthenticatedUserRole());
        model.addAttribute("messageCount", messages != null ? messages.getTotalElements() : 0);
        return "messages";
    }

    @PostMapping("/save")
    public String save (String newMessage, String themeId) {
        Messages message = new Messages();
        Users user = findUserByLogin(getAuthenticatedUserName());
        if (user != null && !newMessage.equals("")) {
            message.setMessage_text(newMessage);
            message.setMessagetheme(getTheme(themeId));
            message.setMessage_creator(user);
            message.setMessageTimestamp(LocalDateTime.now());
            getMessageRepo().save(message);
        }
        return "redirect:/messages/" + themeId;
    }

    @PostMapping("/delete")
    public String delete (String id) {
        getMessageRepo().deleteById(Integer.parseInt(id));
        return "redirect:/themes";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public Messages findOne (Integer id) {
        Optional<Messages> optMessage = getMessageRepo().findById(id);
        return optMessage.orElse(null);
    }
}
