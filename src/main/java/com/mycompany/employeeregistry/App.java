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
import java.util.Scanner;
import java.sql.PreparedStatement;

import com.mycompany.employeeregistry.Employee;
import com.mycompany.employeeregistry.ConnectionManager;
import com.mycompany.employeeregistry.TableManager;

public class App {
    
    Employee employee = new Employee();
    TableManager tblMgr = new TableManager();
    
       public void appStart(){

            Scanner input = new Scanner(System.in);
            System.out.println("Would you like to add, delete, update or select an employee record?");
            String userSelection = input.next();
            switch (userSelection) {
                
            case "add":
            employee.addEmployee();
            break;
            
            case"delete":
            employee.deleteEmployee();
            break;
            
            case "update":
            employee.updateEmployee();
            break;
            
            case"select":
            employee.selectEmployee();
            break;
            
            case "all":
                employee.viewAllEmployees();
                break;
                
            case "table": 
                tblMgr.createTable();
                break;     
            
            default: System.out.println("Exiting");
        } 
            } 

        public static void main(String[] args) {
            App app = new App();
            app.appStart();
            
            
    }
}
