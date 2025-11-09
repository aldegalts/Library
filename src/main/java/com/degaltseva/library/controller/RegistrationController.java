package com.degaltseva.library.controller;

import com.degaltseva.library.entity.UserEntity;
import com.degaltseva.library.entity.enums.Role;
import com.degaltseva.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Контроллер для регистрации пользователей.
 * <p>
 */
@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") UserEntity user, Model model) {
        try {
            userService.registerUser(
                    user.getUserFirstName(),
                    user.getUserLastName(),
                    user.getUserEmail(),
                    user.getPhone(),
                    user.getPassword_hash(),
                    Role.USER
            );

            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("message", "Ошибка регистрации: возможно, пользователь уже существует");
            return "registration";
        }
    }
}
