package com.console.parameter;

import com.console.commands.Observer;

interface ConcreteObserver {
    void addObserver(Observer observer);
    void notifyAllObservers();
}