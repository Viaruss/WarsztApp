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
    public boolean updateDB(String query) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return false;
        }
    }

    public boolean insert(Vector<String> data, String tableName){
        ArrayList<String> columns = getColumnNames(tableName);
        StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " (");
        for (int i = 1; i < columns.size(); i++){
            query.append(columns.get(i)).append(", ");
        }
        query = new StringBuilder(query.substring(0, query.length() - 2));
        query.append(") VALUES(");
        for (String d : data){
            try {
                Double.parseDouble(d);
                query.append(d).append(", ");
            } catch (NumberFormatException nfe) {
                query.append("'").append(d).append("', ");
            }
        }
        query = new StringBuilder(query.substring(0, query.length() - 2));
        query.append(")");
        boolean outcome = false;
        try {
            outcome = updateDB(query.toString());
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
        return outcome;
    }
    public boolean update(Vector<String> data, String tableName) {
        ArrayList<String> columns = getColumnNames(tableName);
        StringBuilder query = new StringBuilder("UPDATE " + tableName + " set ");
        for (int i = 1; i < columns.size(); i++) {
            query.append(columns.get(i)).append(" = ");
            try {
                Double.parseDouble(data.get(i));
                query.append(data.get(i)).append(", ");
            } catch (NumberFormatException nfe) {
                query.append("'").append(data.get(i)).append("', ");
            }
        }
        query = new StringBuilder(query.substring(0, query.length() - 2));
        query.append(" WHERE ").append(columns.get(0)).append(" = ").append(data.get(0));
        boolean outcome = false;
        try {
            outcome = updateDB(query.toString());
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
        return outcome;
    }
    public boolean delete(int primaryId, String tableName) {
        boolean result;
            try {
                result = updateDB("DELETE FROM "+ tableName + " WHERE " +  getColumnNames(tableName).get(0) + " = " + primaryId);
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
                result = false;
            }
            return result;
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
