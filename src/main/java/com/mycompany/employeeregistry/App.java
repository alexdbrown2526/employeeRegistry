/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.employeeregistry;

/**
 *
 * @author Alex
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author postgresqltutorial.com
 */
public class App{
    

    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "user";
    private final String password = "password";

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
            
            Statement statement = conn.createStatement();
         String sql = "CREATE TABLE IF NOT EXISTS COMPANY " +
            "(ID INT PRIMARY KEY     NOT NULL," +
            " NAME           TEXT    NOT NULL, " +
            " AGE            INT     NOT NULL, " +
            " ADDRESS        TEXT, " +
            " SALARY         INT)";
         
         String insert =  "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
            + "VALUES (1, 'John', 30, 'Arizona', 120000.00 );" +
                 "VALUES (2, 'Jane', 22, 'New York', 20000.00 ); " + 
                 "VALUES (3, 'Alex', 43, 'Vermont', 50000.00 );" +
                 "VALUES (4, 'Matt', 26, 'California', 2000000.00 );"; 
         System.out.println("inserted successfully");
         
         ResultSet rs = statement.executeQuery("SELECT * FROM COMPANY;");
            while(rs.next()) {
               int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String address = rs.getString("address");
            float  salary = rs.getFloat("salary");
            System.out.println( "ID = " + id );
            System.out.println( "NAME = " + name );
            System.out.println( "AGE = " + age );
            System.out.println( "ADDRESS = " + address );
            System.out.println( "SALARY = " + salary );
            System.out.println(); 
            }
            rs.close();
         
         statement.executeUpdate(sql);
         statement.executeUpdate(insert);
         statement.close();
         conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
    
    public static void main(String[] args) {
        App app = new App();
        app.connect();
        
       
    }
}
