package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    public static void menuLoop(Connection conn){
        Scanner sc = new Scanner(System.in);
        loop: while(true){
            System.out.println(
                    "Choose what You want to do:\n" +
                            "0 - Show all data\n" +
                            "1 - Search data\n" +
                            "2 - Add data\n" +
                            "3 - Modify data\n" +
                            "4 - Delete data\n" +
                            "5 - Exit\n");
            int choice = -1;
            try {
                choice = sc.nextInt();
            }catch (java.util.InputMismatchException e){ //catch any non-int data being entered
                sc.next();                               //and skip, preventing infinite looping
            }
            switch(choice){
                case 0:
                    SQLRequests.select(conn, sc, true);
                    break;
                case 1:
                    SQLRequests.select(conn, sc, false);
                    break;
                case 2:
                    SQLRequests.insert(conn, sc);
                    break;
                case 3:
                    SQLRequests.update(conn, sc);
                    break;
                case 4:
                    SQLRequests.delete(conn, sc);
                    break;
                case 5:
                    break loop;
                default:
                    System.out.println("\nUnexpected value: " + choice +
                            "\nenter a valid choice...");
            }
        }
        try {
            conn.close(); //disconnect from DB
            System.out.println("Disconnected...");
        } catch (SQLException ignored){}
    }

}
