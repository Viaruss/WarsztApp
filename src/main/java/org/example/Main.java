package org.example;

public class Main {
    public static void main (String[] args){
        System.out.println("Start");
        Menu.menuLoop(SQLRequests.connectDB(PropertiesReader.read()));
        System.out.println("Goodbye :)");
    }
}