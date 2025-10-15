package com.degaltseva.library.utils;

public interface Command {
    boolean supports(String input);
    void execute(String input);
    String getDescription();
}
