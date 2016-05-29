package com.console.commands;

import com.console.commands.filters.ExtFilter;
import com.console.commands.filters.RegFilter;
import com.console.parameter.Parameter;

import java.io.File;

public class Delete implements Command, Observer {
    private Parameter parameter;
    private String path;
    private String nameOfFile;

    public Delete(Parameter parameter){
        this.parameter = parameter;
        parameter.addObserver(this);
        this.path = parameter.getPath();
    }

    public boolean matches(String command) {
        if (command.matches("delete (.*)")){
            this.nameOfFile = command.substring(7);
            return true;
        }
        else return false;
    }

    public void executeCommand() {
        if(nameOfFile.matches("all")){
            deleteAllFiles();
        }
        else if(nameOfFile.matches("(.+)\\*")){
            deleteFileWithRegex();
        }
        else if (nameOfFile.matches("\\..+")){
            deleteFilesWithExtension();
        }
        else {
            deleteFileFullName();
        }
    }

    public boolean endLoop() {
        return true;
    }

    public void update() {
        this.path = parameter.getPath();
    }

    private void deleteAllFiles(){
        File [] files = new File(path).listFiles();
        deleteOperationFromListFiles(files);
    }

    private void deleteFilesWithExtension(){
        ExtFilter filter = new ExtFilter(nameOfFile);
        File [] files = new File(path).listFiles(filter);
        deleteOperationFromListFiles(files);
    }

    private void deleteFileWithRegex(){
        RegFilter regFilter = new RegFilter(nameOfFile);
        File [] files = new File(path).listFiles(regFilter);
        deleteOperationFromListFiles(files);
    }

    private void deleteFileFullName(){
        File file = new File(path+"/"+nameOfFile);
        if (file.delete()){
            System.out.println(nameOfFile + " is deleted");
        }
        else {
            System.out.println("Delete operation has failed");
        }
    }

    private void deleteOperationFromListFiles(File[] files){
        if (files != null) {
            for (File f : files) {
                if (f.delete()) {
                    System.out.println(f.getName() + " is deleted");
                } else {
                    System.out.println(f.getName() + " - delete operation has failed");
                }
            }
        }
        else System.out.println("There is no files to delete");
    }
}
