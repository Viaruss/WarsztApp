package org.backend;

import GUI.AccountManager;
import GUI.LoginWindow;

public class Main {
    //TODO : proper connection closing
    public static void main (String[] args){
        //Menu menu = new Menu();
        //SQLRequests req = new SQLRequests();
        AccountManager accountManager = new AccountManager();
        System.out.println("Start");
        new LoginWindow(accountManager);                  //GUI version
        //menu.menuLoop(req);                             //Console version
        //req.closeConn();
        System.out.println("Goodbye :)");
    }
}