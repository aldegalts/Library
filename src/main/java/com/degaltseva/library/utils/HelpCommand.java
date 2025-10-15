package com.degaltseva.library.utils;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class HelpCommand implements Command {

    private final List<Command> allCommands;

    public HelpCommand(List<Command> allCommands) {
        this.allCommands = allCommands;
    }

    @Override
    public boolean supports(String input) {
        return input.equalsIgnoreCase("help");
    }

    @Override
    public void execute(String input) {
        System.out.println("\nДоступные команды:");
        System.out.println("--------------------------------------");

        allCommands.stream()
                .filter(cmd -> !(cmd instanceof HelpCommand))
                .forEach(cmd -> System.out.printf("• %s%n", cmd.getDescription()));

        System.out.println("--------------------------------------");
        System.out.println("Введите 'exit' для выхода из программы.\n");
    }

    @Override
    public String getDescription() {
        return "help — показать список всех доступных команд";
    }
}
