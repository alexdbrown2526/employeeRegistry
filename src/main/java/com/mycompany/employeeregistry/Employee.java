/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.employeeregistry;

import com.mycompany.employeeregistry.ConnectionManager;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


interface EmployeeInterface {
        public void addEmployee();
        public void deleteEmployee();
        public void updateEmployee();
        public void selectEmployee();   
        public void viewAllEmployees();
}

 public class Employee implements EmployeeInterface {
    ConnectionManager conn= new ConnectionManager();
    Scanner input = new Scanner(System.in);
    PreparedStatement prepSt = null;
    

    @Override
    public void addEmployee() {
        try{                     
            //Set variables to input
            System.out.println("Enter employee's first name");
            String name = input.next();
            
            System.out.println("Enter employee's age");
            int age = input.nextInt();
            
            System.out.println("Enter employee's location");
            String address = input.next();
            
            System.out.println("Enter employee's salary");
            int salary = input.nextInt();
            
            //Take input variables and insert values into company table
            String insert = "INSERT INTO COMPANY (NAME, AGE, ADDRESS, SALARY) values (?, ?, ?, ?)";
         
                prepSt = conn.connect().prepareStatement(insert);
                    
                prepSt.setString(1, name);
                prepSt.setInt(2, age);
                prepSt.setString(3, address);
                prepSt.setInt(4, salary);  
                prepSt.executeUpdate();
        }
         catch(SQLException e) {
            e.printStackTrace();
                }
    
    }
    @Override
    public void deleteEmployee() {
        try{
        System.out.println("Please enter the name of the employee record");
                    String deleteUser = input.next();

            String delete = "DELETE FROM company WHERE name = ? ";
            prepSt = conn.connect().prepareStatement(delete);
            prepSt.setString(1, deleteUser);
            prepSt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
    }
    @Override
    public void updateEmployee() {
        try { 
            System.out.println("Enter the name of the employee to be updated");
        
                String selectedUpdateEmployee = input.next();
                System.out.println("Enter the field you would like to update(age, address, salary)");
                String selectedField = input.next();
                //Switch statement to select field to update
                switch (selectedField){
                    case ("age"):
                        System.out.println("What would you like to change " + selectedUpdateEmployee + "'s age to?");
                        int updatedAge = input.nextInt();
                        String updateAgeSQL = "UPDATE company SET age =? WHERE name =?";
                        prepSt = conn.connect().prepareStatement(updateAgeSQL);
                        prepSt.setInt(1, updatedAge);
                        prepSt.setString(2, selectedUpdateEmployee);
                        prepSt.executeUpdate();
                        break;
                    
                    case("address"):
                        System.out.println("What would you like to change " + selectedUpdateEmployee + "'s address to?");
                        String updatedAddress = input.next();
                        String updateAddressSQL = "UPDATE company SET address =? WHERE name =?";
                        prepSt = conn.connect().prepareStatement(updateAddressSQL);
                        prepSt.setString(1, updatedAddress);
                        prepSt.setString(2, selectedUpdateEmployee);
                        prepSt.executeUpdate();
                        break;
                     
                     case("salary"):
                        System.out.println("What would you like to change " + selectedUpdateEmployee + "'s salary to?");
                        int updatedSalary = input.nextInt();
                        String updateSalarySQL = "UPDATE company SET salary =? WHERE name =?";
                        prepSt = conn.connect().prepareStatement(updateSalarySQL);
                        prepSt.setInt(1, updatedSalary);
                        prepSt.setString(2, selectedUpdateEmployee);
                        prepSt.executeUpdate();
                        break;               
       }
        } catch(SQLException e) {
            e.printStackTrace();
    }
        }
    
    @Override
    public void selectEmployee(){
        try {
            System.out.println("Enter the ID of the employee you would like to view");
            int selectedEmployee = input.nextInt();
                          Statement statement = conn.connect().createStatement();

                       String select =  "SELECT * FROM company WHERE ID = " + selectedEmployee +" "; 
            prepSt.setInt(1, selectedEmployee);

            ResultSet result = statement.executeQuery(select);

            while(result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                int age = result.getInt(3);
                String address = result.getString(4);
                int salary = result.getInt(5);
                System.out.println( "ID = " + id );
                System.out.println( "NAME = " + name );
                System.out.println( "AGE = " + age );
                System.out.println( "ADDRESS = " + address );
                System.out.println( "SALARY = " + salary );
                System.out.println();
            } result.close();
              conn.connect().close();
                
        } catch(Exception e) {
            System.out.println("Error selecting employee");
        }
    } 
    
    @Override
    public void viewAllEmployees() {
    
        try {
            Statement statement = conn.connect().createStatement();
            String getAllSql = "SELECT * FROM company";
        ResultSet rs = statement.executeQuery(getAllSql);
            while(rs.next()) {
            int id = rs.getInt(1);
            String inputName = rs.getString(2);
            int inputAge = rs.getInt(3);
            String inputAddress = rs.getString(4);
            int  inputSalary = rs.getInt(5);
            System.out.println( "ID = " + id );
            System.out.println( "NAME = " + inputName );
            System.out.println( "AGE = " + inputAge );
            System.out.println( "ADDRESS = " + inputAddress );
            System.out.println( "SALARY = " + inputSalary );
            System.out.println(); 
            }
            rs.close();
            conn.connect().close();
    } catch(SQLException e) {
        System.err.println(e.getMessage());
        e.printStackTrace();
     }   

    }
 }

