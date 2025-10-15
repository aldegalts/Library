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
        commands.stream()
                .filter(cmd -> cmd.supports(input))
                .findFirst()
                .ifPresentOrElse(
                        cmd -> cmd.execute(input),
                        () -> System.out.println("Неизвестная команда...")
                );
    }
}
