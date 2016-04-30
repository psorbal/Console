package com.console.commands;

public interface Command {
    boolean matches(String command);
    void executeCommand();
    boolean endLoop();
}
