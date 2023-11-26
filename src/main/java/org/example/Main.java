package org.example;

public class Main {
    //TODO : proper connection closing
    public static void main (String[] args){
        Menu menu = new Menu();
        SQLRequests req = new SQLRequests();
        System.out.println("Start");
        menu.menuLoop(req);
        req.closeConn();
        System.out.println("Goodbye :)");
    }
}