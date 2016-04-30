package com.console.parameter;

import com.console.commands.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for parameter using in commands.
 * To create this class, was used Singleton pattern.
 * The Singleton pattern ensures that a class has only one instance
 * and provides a global point of access to that instance.
 */

public class Parameter implements ConcreteObserver {
    private List<Observer> observers = new ArrayList<Observer>();
    private String parameter = "$";
    private String path = System.getProperty("user.dir");

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
        notifyAllObservers();
    }

    public String getPath(){
        return path;
    }

    public void setPath(String parameter){
        this.path = parameter;
        notifyAllObservers();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}