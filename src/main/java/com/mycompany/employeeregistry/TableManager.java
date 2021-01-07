/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.employeeregistry;

import java.sql.*;

public class TableManager {
    Statement stmt = null;
    ConnectionManager conn = new ConnectionManager();
    
    public void createTable() {
        try{
            stmt = conn.connect().createStatement();
        String createCompanyTable = "CREATE TABLE IF NOT EXISTS COMPANY " +
            "(ID SERIAL PRIMARY KEY ," +
            " NAME           TEXT    NOT NULL, " +
            " AGE            INT     NOT NULL, " +
            " ADDRESS        TEXT, " +
            " SALARY         INT)";
        
        stmt.executeUpdate(createCompanyTable);
        System.out.println("Table created successfully");
        } catch(SQLException e){
                        System.out.println(e.getMessage());

        } 
        
    }
        
}
