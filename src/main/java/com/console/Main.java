package com.console;

import com.console.controller.Service;

/**
 * Main class for interactive console application
 */

public class Main {

    public static void main(String[] args) {
        Service service = new Service();
        service.startConsole();
    }

}

