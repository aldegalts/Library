package com.degaltseva.library.utils;

import com.degaltseva.library.entity.User;
import com.degaltseva.library.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserCommand implements Command {

    private final UserService userService;

    public UserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(String input) {
        return input.startsWith("user ");
    }

    @Override
    public void execute(String input) {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            System.err.println("Некорректная команда для пользователя");
            return;
        }

        switch (parts[1]) {
            case "create" -> {
                if (parts.length < 4) {
                    System.err.println("Использование: user create <id> <login>");
                    return;
                }
                userService.createUser(Long.parseLong(parts[2]), parts[3]);
                System.out.println("Пользователь успешно создан");
            }
            case "read" -> {
                if (parts.length < 3) {
                    System.err.println("Использование: user read <id>");
                    return;
                }
                User user = userService.findById(Long.parseLong(parts[2]));
                if (user != null) {
                    System.out.printf("Пользователь: id=%d, login=%s%n", user.getId(), user.getLogin());
                } else {
                    System.err.println("Пользователь не найден");
                }
            }
            case "update" -> {
                if (parts.length < 4) {
                    System.err.println("Использование: user update <id> <newLogin>");
                    return;
                }
                userService.updateLogin(Long.parseLong(parts[2]), parts[3]);
                System.out.println("Логин пользователя обновлён");
            }
            case "delete" -> {
                if (parts.length < 3) {
                    System.err.println("Использование: user delete <id>");
                    return;
                }
                userService.deleteById(Long.parseLong(parts[2]));
                System.out.println("Пользователь удалён");
            }
            default -> System.err.println("Неизвестная команда пользователя");
        }
    }
}
