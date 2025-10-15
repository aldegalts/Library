package com.degaltseva.library.utils;

import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Команда {@code help}, которая отображает список всех доступных команд в системе.
 *
 * <p>Эта команда реализует интерфейс {@link Command} и используется для предоставления пользователю
 * справочной информации о доступных действиях в библиотечной системе. Команда исключает себя
 * из вывода, чтобы избежать циклической ссылки.</p>
 */
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
