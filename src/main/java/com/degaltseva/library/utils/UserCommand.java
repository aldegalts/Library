package com.degaltseva.library.utils;

import com.degaltseva.library.entity.User;
import com.degaltseva.library.service.UserService;
import org.springframework.stereotype.Component;

/**
 * Команда для управления пользователями библиотеки.
 * <p>
 * Поддерживает создание, получение, обновление и удаление пользователей.
 */
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
        String[] parts = input.trim().split(" +");
        if (parts.length < 2) {
            System.err.println("Некорректная команда для пользователя");
            return;
        }

        switch (parts[1]) {
            case "create" -> handleCreate(parts);
            case "read" -> handleRead(parts);
            case "update" -> handleUpdate(parts);
            case "delete" -> handleDelete(parts);
            default -> System.err.println("Неизвестная команда пользователя");
        }
    }

    @Override
    public String getDescription() {
        return """
           user create <id> <login> — создать пользователя
           user read <id> — вывести информацию о пользователе
           user update <id> <newLogin> — обновить логин пользователя
           user delete <id> — удалить пользователя
           """;
    }

    private void handleCreate(String[] parts) {
        if (parts.length < 4) {
            System.err.println("Использование: user create <id> <login>");
            return;
        }

        Long id = parsePositiveId(parts[2]);
        if (id == null) return;

        if (userService.findById(id) != null) {
            System.err.printf("Пользователь с id=%d уже существует%n", id);
            return;
        }

        userService.createUser(id, parts[3]);
        System.out.println("Пользователь успешно создан");
    }

    private void handleRead(String[] parts) {
        if (parts.length < 3) {
            System.err.println("Использование: user get <id>");
            return;
        }

        Long id = parsePositiveId(parts[2]);
        if (id == null) return;

        User user = userService.findById(id);
        if (user == null) {
            System.err.printf("Пользователь с id=%d не найден%n", id);
            return;
        }

        System.out.printf("Пользователь: id=%d, login=%s%n", user.getId(), user.getLogin());
    }

    private void handleUpdate(String[] parts) {
        if (parts.length < 4) {
            System.err.println("Использование: user update <id> <newLogin>");
            return;
        }

        Long id = parsePositiveId(parts[2]);
        if (id == null) return;

        User user = userService.findById(id);
        if (user == null) {
            System.err.printf("Пользователь с id=%d не найден, обновление невозможно%n", id);
            return;
        }

        userService.updateLogin(id, parts[3]);
        System.out.println("Логин пользователя обновлён");
    }

    private void handleDelete(String[] parts) {
        if (parts.length < 3) {
            System.err.println("Использование: user delete <id>");
            return;
        }

        Long id = parsePositiveId(parts[2]);
        if (id == null) return;

        User user = userService.findById(id);
        if (user == null) {
            System.err.printf("Пользователь с id=%d не найден, удаление невозможно%n", id);
            return;
        }

        userService.deleteById(id);
        System.out.printf("Пользователь с id=%d удалён%n", id);
    }

    private Long parsePositiveId(String value) {
        try {
            long id = Long.parseLong(value);
            if (id <= 0) {
                System.err.println("ID должен быть положительным числом");
                return null;
            }
            return id;
        } catch (NumberFormatException e) {
            System.err.println("Некорректный формат ID, требуется число");
            return null;
        }
    }
}
