package com.console.Controller;

/**
 * Intepreter class for interpret which command have to execute
 */

import com.console.commands.Command;
import java.util.List;

class Interpreter {
    private boolean isExecuting = true;

    void interpretCommand(List<Command> activeCommand, String userCommand){
        boolean matchFound = false;
        for (Command command : activeCommand) {
            if (command.matches(userCommand)) {
                matchFound = true;
                command.executeCommand();
                isExecuting = command.endLoop();
                break; //handle the first matching command only
            }
        }

        if (!matchFound) {
            System.out.println(userCommand + ": unknown command");
        }
    }

    boolean getIsExecuting(){
        return isExecuting;
    }
}
