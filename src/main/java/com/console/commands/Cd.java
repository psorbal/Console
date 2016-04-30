package com.console.commands;

import com.console.parameter.Parameter;

import java.io.File;

/**
 * Class for cd command.
 * User can change the current working directory using a ‘cd’ command with a parameter:
 * - ‘..’, for parent directory
 * - Name of existing subdirectory
 */

public class Cd implements Command, Observer {
    private Parameter parameter;
    private String command;
    private String path;

    public Cd(Parameter parameter){
        this.parameter = parameter;
        parameter.addObserver(this);
        this.path = parameter.getPath();
    }

    public boolean matches(String command) {
        if (command.matches("cd (.*)")){
            this.command = command;
            return true;
        }
        else return false;
    }

    public void executeCommand() {
        if(command.matches("cd ..")){
            File directory = new File(path);
            if (directory.getParent() != null) {
                setDir(directory.getParent());
            }
            else System.out.println("This path does not have parent directory");
        }
        else {
            command = command.substring(3);
            File directory = new File(command).getAbsoluteFile();
            if(directory.exists()){
                setDir(directory.getAbsolutePath());
            }
            else System.out.println("This directory does not exist in this scope");
        }
    }

    public boolean endLoop() {
        return true;
    }

    private void setDir(String varDir){
        parameter.setParameter(varDir);
        System.setProperty("user.dir", varDir);
        parameter.setPath(System.getProperty("user.dir"));
    }

    public void update() {
        this.path = parameter.getPath();
    }
}
