package com.degaltseva.library.utils;

import org.springframework.stereotype.Component;
import java.util.List;

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
