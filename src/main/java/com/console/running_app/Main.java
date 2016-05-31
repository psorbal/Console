package com.console.running_app;

import com.console.Controller.Service;

/**
 * Main class for interactive console application
 */

public class Main {

    public static void main(String[] args){
        Service service = new Service();
        service.startConsole();
    }

}

