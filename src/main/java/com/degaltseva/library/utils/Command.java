package com.degaltseva.library.utils;

/**
 * Базовый интерфейс для всех команд в библиотечной системе.
 * <p>
 */
public interface Command {
    boolean supports(String input);
    void execute(String input);
    String getDescription();
}
