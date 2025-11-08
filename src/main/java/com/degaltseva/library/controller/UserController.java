package com.degaltseva.library.controller;

import com.degaltseva.library.entity.UserEntity;
import com.degaltseva.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для работы с пользователями.
 * <p>
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final String REDIRECT_USERS = "redirect:/users";
    private final String USER_DETAILS_VIEW = "user/user-details";

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<UserEntity> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/users";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        UserEntity user = userService.findById(id);
        model.addAttribute("user", user);
        return USER_DETAILS_VIEW;
    }

    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable Long id,
                             @ModelAttribute("user") UserEntity updatedUser) {
        userService.updateUser(id, updatedUser);
        return REDIRECT_USERS + "/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return REDIRECT_USERS;
    }

    @GetMapping("/me")
    public String getProfilePage(Model model) {
        String currentUserEmail  = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByEmail(currentUserEmail);
        model.addAttribute("user", user);
        return USER_DETAILS_VIEW;
    }
}