package org.example;

        import java.sql.*;
        import java.util.Scanner;

public class SQLRequests {
    public static Connection connectDB (String url) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connected...\n");
        } catch (SQLException e) {
            // handle any sql errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return conn;
    }
    public static void sendRequest (Connection conn, String query) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            System.out.println("Database updated");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }
    public static void select(Connection conn, Scanner sc, Boolean showALl) {
        Statement stmt;
        String query, filter = null;
        int choice = -1;
        if (showALl) {
            query = "SELECT * FROM pracownicy";
        } else {
            do {
                System.out.println("\nWhat are You searching For?\n" +
                        "0 - ID\n" +
                        "1 - Name\n" +
                        "2 - Surname\n" +
                        "3 - Age\n");
                try {
                    choice = sc.nextInt();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("\nUnexpected value: " + choice +
                            "\nenter a valid choice...");
                }
            } while (choice < 0 || choice >= 4);
            switch (choice){
                case 0:
                    filter = "id";
                    break;
                case 1:
                    filter = "imie";
                    break;
                case 2:
                    filter = "nazwisko";
                    break;
                case 3:
                    filter = "wiek";
                    break;
            }
            System.out.println("Search for:");
            query = "SELECT * FROM pracownicy WHERE " + filter + " = '" + sc.next() + "'";
        }
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("+-----+" + "-".repeat(17) + "+" + "-".repeat(17) + "+------+");
            System.out.println("| id  | imie" + " ".repeat(12) + "| nazwisko        | wiek |");
            System.out.println("+-----+" + "-".repeat(17) + "+" + "-".repeat(17) + "+------+");

            while (rs.next()) {
                String id = String.format("%-3d", (rs.getInt("id")));
                String imie = String.format("%-15s", rs.getString("imie"));
                String nazwisko = String.format("%-15s", rs.getString("nazwisko"));
                String wiek = String.format("%-4d", (rs.getInt("wiek")));
                System.out.println("| " + id + " | " + imie + " | " + nazwisko + " | " + wiek + " |");
            }
            System.out.println("+-----+" + "-".repeat(17) + "+" + "-".repeat(17) + "+------+");
        } catch (SQLException ignored){}
    }
    public static void insert(Connection conn, Scanner sc){

        String imie, nazwisko;
        int wiek;
        System.out.println("podaj imie, nazwisko, wiek do wstawienia");
        imie = sc.next();
        nazwisko = sc.next();
        wiek = sc.nextInt();

        String query = String.format(
                "INSERT INTO pracownicy (imie, nazwisko, wiek) VALUES('%s', '%s', %d);",
                imie, nazwisko, wiek);
        try {
            sendRequest(conn, query);
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public static void update(Connection conn, Scanner sc){
        Statement stmt;
        String query;
        String imie = "", nazwisko = "", temp = "";
        int wiek = 0, choice;
        do {
            System.out.println("Enter data ID:\n");
            choice = -1;
            try {
                choice = sc.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Enter valid id number...\n");
            }
        } while (choice == -1);
        System.out.println("Enter modified data one by one, type '-skip-' to skip to next column");
        try {
            imie = sc.next();
            nazwisko = sc.next();
            temp = sc.next();
            if(!temp.equals("-skip-")){
                wiek = Integer.parseInt(temp);
            }
        } catch (java.util.InputMismatchException e) {System.out.println("Error, nothing was changed\n");}
        query = "UPDATE pracownicy SET ";
        query = (imie.equals("-skip-")) ? query : query + "imie = '" +  imie + "'," ;
        query = (nazwisko.equals("-skip-")) ? query : query + "nazwisko = '" +  nazwisko + "',";
        query = (temp.equals("-skip-")) ? query : query + " wiek = " +  wiek + ",";
        query = query.substring(0, query.length()-1) + " WHERE id = " + choice + ";";
        System.out.println("Are You sure you want to change");
        String confirmation = "";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pracownicy where id = " + choice);
            rs.next();
            System.out.printf("%s %s %d%n", rs.getString("imie"), rs.getString("nazwisko"), (rs.getInt("wiek")));
            System.out.printf("to\n" +
                    "%s %s %s%n", imie, nazwisko, temp);
            do {
                System.out.println("Y/N?");
                confirmation = sc.next();
            } while (!confirmation.equalsIgnoreCase("Y") && !confirmation.equalsIgnoreCase("N"));
        } catch (java.sql.SQLException ignored){}
        if (confirmation.equalsIgnoreCase("Y")){
            try {
                sendRequest(conn, query);
                System.out.println("Data Updated Succesfully");
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
            }
        } else {
            System.out.println("Update canceled, data unchanged...");
        }
    }
    public static void delete(Connection conn, Scanner sc) {
        int choice;
        do {
            System.out.println("Enter data ID:\n");
            choice = -1;
            try {
                choice = sc.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Enter valid id number...\n");
            }
        } while(choice == -1);

        System.out.println("Are You sure you want to delete:");
        String confirmation = "";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pracownicy where id = " + choice);
            rs.next();
            System.out.printf("%d %s %s %s%n", rs.getInt("id"), rs.getString("imie"), rs.getString("nazwisko"), rs.getInt("wiek"));
            do {
                System.out.println("Y/N?");
                confirmation = sc.next();
            } while(!confirmation.equalsIgnoreCase("Y") && !confirmation.equalsIgnoreCase("N"));
        } catch (SQLException ignored) {}
        if (confirmation.equalsIgnoreCase("Y")) {
            try {
                sendRequest(conn, "DELETE FROM pracownicy WHERE id = " + choice);
                System.out.println("Data Deleted Succesfully");
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
            }
        } else {
            System.out.println("Deletion canceled, data unchanged...");
        }
    }

}
