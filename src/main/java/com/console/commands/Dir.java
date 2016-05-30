package com.console.commands;

import com.console.parameter.Parameter;
import java.io.File;

/**
 * Class for dir command.
 * User can display the content of current working directory using a ‘dir’ command.
 * Format of the display: type of the item (DIR|FILE) and name of the item.
 * Example:
 * DIR      classes
 * FILE     pom.xml
 */

public class Dir implements Command, Observer {
    private Parameter parameter;
    private String path;

    public Dir(Parameter parameter){
        this.parameter = parameter;
        parameter.addObserver(this);
        this.path = parameter.getPath();
    }

    public boolean matches(String command) {
        return command.matches("dir");
    }

    public void executeCommand() {
        try{
            File [] files = new File(path).listFiles();
            if (files != null && files.length != 0) {
                System.out.println("Content of " + path);
                for (File f : files) {
                    if (f.isDirectory()) {
                        System.out.println("DIR \t\t" + f.getName());
                    } else {
                        System.out.println("FILE \t\t" + f.getName());
                    }
                }
            }
            else System.out.println("This folder is empty");
        }
        catch(Exception e){
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
