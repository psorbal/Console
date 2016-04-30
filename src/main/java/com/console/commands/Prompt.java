package com.console.commands;

import com.console.parameter.Parameter;

/**
 * Class for prompt command.
 * User can customize the presented prompt using a ‘prompt’ command with a parameter.
 * Possible values of the parameter:
 * -‘$cwd’, will result in displaying a path to current working directory in the prompt
 * -‘reset’, will reset the prompt to default ($ sign)
 * - Any other string will display the parameter in the prompt
 */

public class Prompt implements Command, Observer {
    private String command;
    private Parameter parameter;

    public Prompt(Parameter parameter){
        this.parameter = parameter;
        parameter.addObserver(this);
    }

    public boolean matches(String command) {
        if (command.matches("prompt (.*)")){
            this.command = command;
            return true;
        }
        else return false;
    }

    public void executeCommand() {
        if(command.matches("prompt reset")){
            parameter.setParameter("$");
        }
        else if (command.matches("prompt \\$cwd")){
            parameter.setParameter(System.getProperty("user.dir"));
        }
        else {
            parameter.setParameter(command.substring(7));
        }
    }

    public boolean endLoop(){
        return true;
    }

    public void update() {
    }
}
