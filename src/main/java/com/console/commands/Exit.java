package com.console.commands;

/**
 * Class for exit command.
 * User can terminate the application by using ‘exit’ command.
 */

public class Exit implements Command{

    public boolean matches(String command) {
        return command.matches("exit");
    }

    public void executeCommand() {
        System.out.println("Bye.");
    }

    public boolean endLoop(){
        return false;
    }
}
