package com.console.Controller;

import com.console.commands.*;
import com.console.parameter.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Service class
 */


public class Service {
    private static Scanner scanner = new Scanner(System.in);

    public void startConsole() {
        Parameter parameter = new Parameter();
        List<Command> listCommand = new ArrayList<Command>();
        listCommand.add(new Prompt(parameter));
        listCommand.add(new Dir(parameter));
        listCommand.add(new Tree(parameter));
        listCommand.add(new Cd(parameter));
        listCommand.add(new Ctdir(parameter));
        listCommand.add(new Ctfile(parameter));
        listCommand.add(new Delete(parameter));
        listCommand.add(new Exit());
        Interpreter interpreter = new Interpreter();

        while (interpreter.getIsExecuting()) {
            System.out.print("[MyShell] " + parameter.getParameter() + ">");
            String userCommand = getScanner();

            interpreter.interpretCommand(listCommand, userCommand);
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
