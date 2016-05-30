package com.console.commands;

import com.console.parameter.Parameter;

import java.io.File;
import java.io.IOException;

/**
 * Class for command ctfile which allow create new file
 */

public class Ctfile implements Command, Observer {
    private Parameter parameter;
    private String nameOfNewFile;
    private String path;

    public Ctfile(Parameter parameter){
        this.parameter = parameter;
        parameter.addObserver(this);
        this.path = parameter.getPath();
    }

    public boolean matches(String command) {
        if (command.matches("ctfile (.+)")){
            this.nameOfNewFile = command.substring(7);
            return true;
        }
        else return false;
    }

    public void executeCommand() {
        try {
            File file = new File(path+"/"+nameOfNewFile);
            if(file.createNewFile()){
                System.out.println(nameOfNewFile + " is created.");
            }
            else {
                System.out.println(nameOfNewFile + " already exists.");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean endLoop() {
        return true;
    }

    public void update() {
        this.path = parameter.getPath();
    }
}
