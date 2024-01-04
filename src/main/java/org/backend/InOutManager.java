package org.backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class InOutManager {
    Scanner sc = new Scanner(System.in);
    public int choiceOperator(ArrayList<String> choices, String actionInfo){
        int choice;
        System.out.println(actionInfo);
        while (true) {
            for (int i = 0; i < choices.size(); i++) {
                System.out.println(i + " - " + choices.get(i));
            }
            try {
                choice = sc.nextInt();
                if (choice < choices.size() && choice > -1){
                    break;
                }
            }catch (java.util.InputMismatchException e){
                sc.next();
                System.out.println("\nUnexpected value, enter a valid choice...");
            }
        }
        return choice;
    }
    public String inString(){
        return sc.next();
    }
    public int inInt(){
        return sc.nextInt();
    }
    public void showFormattedData(ResultSet rs, ArrayList<String> columns) {
        try {
            while (rs.next()) {
                System.out.println("+" + "-".repeat(30));
                for (String col : columns) {
                    System.out.println("| " + col + ": " + rs.getString(col));
                }
            }
            System.out.println("+" + "-".repeat(30));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
