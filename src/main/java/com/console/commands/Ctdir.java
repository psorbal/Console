package com.console.commands;

import com.console.parameter.Parameter;

import java.io.File;

public class Ctdir implements Command, Observer {
    private Parameter parameter;
    private String nameOfNewDirectory;
    private String path;

    public Ctdir(Parameter parameter){
        this.parameter = parameter;
        parameter.addObserver(this);
        this.path = parameter.getPath();
    }

    public boolean matches(String command) {
        if (command.matches("ctdir (.*)")){
            this.nameOfNewDirectory = command.substring(6);
            return true;
        }
        else return false;
    }

    public void executeCommand() {
        File file = new File(path+"/"+nameOfNewDirectory);
        if(!file.exists()) {
            if (!file.exists()) {
                if (file.mkdir()) {
                    System.out.println("Directory "+nameOfNewDirectory+ " is created!");
                } else {
                    System.out.println("Failed to create "+nameOfNewDirectory+" directory!");
                }
            }
        }
        else System.out.println(nameOfNewDirectory + " directory exist in this current working directory");
    }

    public boolean endLoop() {
        return true;
    }

    public void update() {
        this.path = parameter.getPath();
    }
}
