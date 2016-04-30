package com.console.commands;

import com.console.parameter.Parameter;

import java.io.File;

/**
 * Class for tree command.
 * User can display the directory structure of current working directory
 * and its subdirectories using a ‘tree’ command.
 */

public class Tree implements Command, Observer {
    private Parameter parameter;
    private String path;

    public Tree(Parameter parameter){
        this.parameter = parameter;
        parameter.addObserver(this);
        this.path = parameter.getPath();
    }

    public boolean matches(String command) {
        return command.matches("tree");
    }

    public void executeCommand() {
        File currDir = new File(path);
        System.out.print(printTree(currDir));
    }

    public boolean endLoop() {
        return true;
    }

    private String printTree(File directory){
        int branch = 0;
        StringBuilder sb = new StringBuilder();
        printDirectoryTree(directory, branch, sb);
        return sb.toString();
    }

    private void printDirectoryTree(File directory, int branch, StringBuilder sb){
        sb.append(getBranch(branch));
        sb.append(directory.getName());
        sb.append("\n");
        File [] files = directory.listFiles();
        if(files != null) {
            for (File file : files) {
                if (file.isDirectory() && !file.isHidden()) {
                    printDirectoryTree(file, branch + 1, sb);
                }
            }
        }
    }

    private static String getBranch(int branch) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < branch; i++) {
            sb.append("-");
        }
        return sb.toString();
    }

    public void update() {
        this.path = parameter.getPath();
    }
}
