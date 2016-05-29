package com.console.running_app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.console.parameter.Parameter;
import com.console.commands.*;

/**
 * Main class for interactive console application
 */

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isExecuting = true;

    public static void main(String[] args){
        Parameter parameter = new Parameter();
        List<Command> activeCommand = new ArrayList<Command>();
        activeCommand.add(new Prompt(parameter));
        activeCommand.add(new Dir(parameter));
        activeCommand.add(new Tree(parameter));
        activeCommand.add(new Cd(parameter));
        activeCommand.add(new Ctdir(parameter));
        activeCommand.add(new Ctfile(parameter));
        activeCommand.add(new Exit());

        while(isExecuting){
            boolean matchFound = false;
            System.out.print("[MyShell] " + parameter.getParameter() + ">");
            String userCommand = getScanner();

            for (Command command : activeCommand) {
                if (command.matches(userCommand)) {
                    matchFound = true;
                    command.executeCommand();
                    isExecuting = command.endLoop();
                    break; //handle the first matching command only
                }
            }

            if(!matchFound){
                System.out.println(userCommand + ": unknown command");
            }
        }
        closeScanner();
    }

    private static void closeScanner(){
        scanner.close();
    }

    private static String getScanner(){
        return scanner.nextLine();
    }
}
