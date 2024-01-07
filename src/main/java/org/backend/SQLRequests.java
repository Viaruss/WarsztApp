package org.backend;

import org.backend.dataTypes.Account;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

/*
TODO : move confirmation logic from SQLRequests to InOutManager
TODO : Adapt functions to also work with GUI input
*/
public class SQLRequests {
    private final ArrayList <String> tableNames = new ArrayList<>();
    private Connection conn = null;
    private Statement stmt;
    InOutManager inOut = new InOutManager();
    public SQLRequests(){
        try {
            conn = DriverManager.getConnection(PropertiesReader.read());
            System.out.println("Connected...\n");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("show tables");
            while (rs.next()) {
                tableNames.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }
    public ArrayList<String> getTableNames(){
        return tableNames;
    }
    public ArrayList<String> getColumnNames (String table){
        ArrayList<String> temp = new ArrayList<>();
        try {
            ResultSetMetaData rsMetaData = getMetaData(table);
            int count = rsMetaData.getColumnCount();
            for (int i = 1; i <= count; i++) {
                temp.add(rsMetaData.getColumnName(i));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return temp;
    }
    public ResultSetMetaData getMetaData(String table) throws SQLException{
        return stmt.executeQuery("SELECT * FROM " + table).getMetaData();
    }
    public void updateDB(String query) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            System.out.println("Database updated");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public void select (int tableIndex, Boolean showALl) {
        String query, tableName = tableNames.get(tableIndex);
        ArrayList<String> columns = getColumnNames(tableName);
        if (showALl) {
            query = "SELECT * FROM " + tableNames.get(tableIndex);
        } else {
            int choice = inOut.choiceOperator(columns, "What are You searching For?");
            System.out.println("Search for:");
            query = "SELECT * FROM " + tableNames.get(tableIndex) +  " WHERE " + columns.get(choice) + " = '" + inOut.inString() + "'";
        }
        try {
            ResultSet rs = stmt.executeQuery(query);
            inOut.showFormattedData(rs, columns);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void insert(int tableIndex){
        ArrayList<String> data = new ArrayList<>();
        ArrayList<String> columns = getColumnNames(tableNames.get(tableIndex));
        System.out.println("Input data to insert, one by one:");
        StringBuilder query = new StringBuilder("INSERT INTO " + tableNames.get(tableIndex) + " (");
        for (int i = 1; i < columns.size(); i++){
            System.out.println(columns.get(i) + ": ");
            data.add("'" + inOut.inString()+"'");
            query.append(columns.get(i)).append(", ");
        }
        query = new StringBuilder(query.substring(0, query.length() - 2));
        query.append(") VALUES(");
        for (String d : data){
            query.append(d).append(", ");
        }
        query = new StringBuilder(query.substring(0, query.length() - 2));
        query.append(")");
        System.out.println(query);
        try {
            updateDB(query.toString());
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
    }
    public void update(int tableIndex) {
        int choice;
        String tableName = tableNames.get(tableIndex);
        //chose data
        do {
            System.out.println("Enter data ID:");
            choice = -1;
            try {
                choice = inOut.inInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Enter valid id number...");
            }
        } while (choice == -1);
        //enter data
        System.out.println("Enter modified data one by one, type '-' to skip to next column");
        ArrayList<String> data = new ArrayList<>();
        ArrayList<String> columns = getColumnNames(tableName);
        System.out.println("Input data to insert, one by one:");
        StringBuilder query = new StringBuilder("UPDATE " + tableName + " set ");
        for (int i = 1; i < columns.size(); i++) {
            System.out.println(columns.get(i));
            String temp = inOut.inString();
            if (temp.equalsIgnoreCase("-")) continue;
            query.append(columns.get(i)).append(" = '").append(temp).append("', ");
            data.add(temp);
        }
        query = new StringBuilder(query.substring(0, query.length() - 2) + " WHERE " + columns.get(0) + " = " + choice);
        //confirmation
        System.out.println("Are You sure you want to change:");
        String confirmation = "";
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE " + getColumnNames(tableName).get(0) + " = " + choice);
            rs.next();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) System.out.print(rs.getString(i) + " ");
            System.out.println("\nto:");
            for (String d : data) System.out.print(d +" ");
            System.out.println();
            do {
                System.out.println("Y/N?");
                confirmation = inOut.inString();
            } while (!confirmation.equalsIgnoreCase("Y") && !confirmation.equalsIgnoreCase("N"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //change data or cancel operation
        if (confirmation.equalsIgnoreCase("Y")) {
            try {
                updateDB(query.toString());
                System.out.println("Data Updated Successfully");
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
            }
        } else {
            System.out.println("Update canceled, data unchanged...");
        }
    }
    public void delete(int tableIndex) {
        int choice;
        String tableName = tableNames.get(tableIndex);
        do {
            System.out.println("Enter data ID:");
            choice = -1;
            try {
                choice = inOut.inInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Enter valid id number...");
            }
        } while(choice == -1);

        System.out.println("Are You sure you want to delete:");
        String confirmation = "";
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM "+ tableName + " WHERE " +  getColumnNames(tableName).get(0) + " = " + choice);
            rs.next();
            for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) System.out.print(rs.getString(i) + " ");
            System.out.println();
            do {
                System.out.println("Y/N?");
                confirmation = inOut.inString();
            } while(!confirmation.equalsIgnoreCase("Y") && !confirmation.equalsIgnoreCase("N"));
        } catch (SQLException e) {e.printStackTrace();}
        if (confirmation.equalsIgnoreCase("Y")) {
            try {
                updateDB("DELETE FROM "+ tableName + " WHERE " +  getColumnNames(tableName).get(0) + " = " + choice);
                System.out.println("Data Deleted Successfully");
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
            }
        } else {
            System.out.println("Deletion canceled, data unchanged...");
        }
    }
    public ArrayList<Account> accountInfo(){
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM dane_kont;");
            while (rs.next()){
                accounts.add(new Account(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return accounts;
    }

    public DefaultTableModel getTableModel(String tableName){
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells non-editable
                return false;
            }
        };
        try {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            //Object[] columnNames = new Object[resultSetMetaData.getColumnCount()];
            for(int i = 0; i < resultSetMetaData.getColumnCount(); i++){
                 //columnNames[i] = resultSetMetaData.getColumnLabel(i);
                 tableModel.addColumn(resultSetMetaData.getColumnLabel(i+1));
            }

            // Clear the existing data in the table model
            tableModel.setRowCount(0);
            // Populate the table model with data from the database
            while (resultSet.next()) {
                Vector<Object> rowData = new Vector<>();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    rowData.add(resultSet.getObject(i));
                }
                tableModel.addRow(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableModel;
    }
}
