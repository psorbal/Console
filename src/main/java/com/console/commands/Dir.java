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
        File currDir = new File(path);
        getAllFiles(currDir);
    }

    public boolean endLoop() {
        return true;
    }

    private void getAllFiles(File currDir){
        File [] files = currDir.listFiles();
        if (files != null) {
            System.out.println("Content of " + currDir.getAbsolutePath());
            for (File f : files) {
                if (f.isDirectory()) {
                    System.out.println("DIR \t\t" + f.getName());
                } else {
                    System.out.println("FILE \t\t" + f.getName());
                }
            }
        }
        else System.out.println("This pathname does not denote a directory, then listFiles() returns null");
    }

    public void update() {
        this.path = parameter.getPath();
    }
}
