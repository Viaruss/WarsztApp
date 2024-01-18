package org.backend;

import GUI.AccountManager;
import GUI.LoginWindow;

public class Main {
    public static void main (String[] args){
        AccountManager accountManager = new AccountManager();
        System.out.println("Start");
        new LoginWindow(accountManager);
        System.out.println("Goodbye :)");
    }
}