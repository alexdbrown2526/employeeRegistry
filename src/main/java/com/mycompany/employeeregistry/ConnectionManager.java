/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.employeeregistry;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.SQLException;
import java.sql.Connection;


public class ConnectionManager {
  private  static final   ConnectionManager   INSTANCE = new ConnectionManager();
  private  final String      url_s_c      = "jdbc:postgresql://localhost:5432/postgres";
  private  final String      user     = "user";
  private  final String      password = "password";
  private        Connection  conn     = null;

    public Connection getConnection() {
      Connection connection_m = null;
        if (conn != null) {
            return conn;
        } 
        else {
                try {
                    connection_m = DriverManager.getConnection(url, user, password);

            System.out.println("Connected to the PostgreSQL server successfully.");

        }   
        catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
          
          return(connection_m);
        } 
    }
  
    public static ConnectionManager getInstance() {
      return(INSTANCE); 
    }
  
  public void returnConnection(Connection connection_p) {
    if(connection_p != null) {
      connection_p.close(); 
    }
  }
  
  
 }

