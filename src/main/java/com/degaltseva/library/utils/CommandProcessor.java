package com.degaltseva.library.utils;

import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Центральный обработчик команд, введённых пользователем.
 * <p>
 * Получает строку команды из консоли, определяет подходящий обработчик
 * (класс, реализующий интерфейс {@link Command}) и делегирует выполнение ему.
 * <p>
 * Использует механизм внедрения зависимостей Spring для автоматического
 * сбора всех доступных команд в {@code List<Command>}.
 */
@Component
public class CommandProcessor {

    private final List<Command> commands;

    public CommandProcessor(List<Command> commands) {
        this.commands = commands;
    }

    public void processCommand(String input) {
        if (input == null || input.isBlank()) {
            System.err.println("Введите команду. Для справки используйте 'help'.");
            return;
        }

        for (Command command : commands) {
            if (command.supports(input)) {
                command.execute(input);
                return;
            }
        }

        System.err.println("Неизвестная команда. Введите 'help' для списка доступных.");
    }
}
