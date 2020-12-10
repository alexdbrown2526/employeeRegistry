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
        Scanner input = new Scanner(System.in);
        Connection conn = null;
        PreparedStatement  prepSt = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
            Statement statement = conn.createStatement();

            //Create company table
            String create = "CREATE TABLE IF NOT EXISTS COMPANY " +
            "(ID SERIAL PRIMARY KEY ," +
            " NAME           TEXT    NOT NULL, " +
            " AGE            INT     NOT NULL, " +
            " ADDRESS        TEXT, " +
            " SALARY         INT)";
            
            statement.executeUpdate(create);
  
                     
            System.out.println("Would you like to create, update or delete an employee record?");
            String createDeleteUpdateEmployee = input.next();
                     
        switch (createDeleteUpdateEmployee) {
            case "create" :
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
         
                prepSt = conn.prepareStatement(insert);
                    
                prepSt.setString(1, name);
                prepSt.setInt(2, age);
                prepSt.setString(3, address);
                prepSt.setInt(4, salary);
                
                prepSt.executeUpdate();
                
                break;
                
            //Delete an employee by name
            case ("delete"):
            System.out.println("Please enter the name of the employee record");
            String delete = "DELETE FROM company WHERE name = ? ";
            prepSt = conn.prepareStatement(delete);
            String deleteUser = input.next();
            prepSt.setString(1, deleteUser);

            prepSt.executeUpdate();
            
            break;
            //Update employee information
            case ("update"):
                System.out.println("Enter the name of the employee to be updated");
                String selectedEmployee = input.next();
                System.out.println("Enter the field you would like to update(age, address, salary)");
                String selectedField = input.next();
                //Switch statement to select field to update
                switch (selectedField){
                    case ("age"):
                        System.out.println("What would you like to change " + selectedEmployee + "'s age to?");
                        int updatedAge = input.nextInt();
                        String updateAgeSQL = "UPDATE company SET age =? WHERE name =?";
                        prepSt = conn.prepareStatement(updateAgeSQL);
                        prepSt.setInt(1, updatedAge);
                        prepSt.setString(2, selectedEmployee);
                        prepSt.executeUpdate();
                        break;
                    
                    case("address"):
                        System.out.println("What would you like to change " + selectedEmployee + "'s address to?");
                        String updatedAddress = input.next();
                        String updateAddressSQL = "UPDATE company SET address =? WHERE name =?";
                        prepSt = conn.prepareStatement(updateAddressSQL);
                        prepSt.setString(1, updatedAddress);
                        prepSt.setString(2, selectedEmployee);
                        prepSt.executeUpdate();
                        break;
                     
                     case("salary"):
                        System.out.println("What would you like to change " + selectedEmployee + "'s salary to?");
                        int updatedSalary = input.nextInt();
                        String updateSalarySQL = "UPDATE company SET salary =? WHERE name =?";
                        prepSt = conn.prepareStatement(updateSalarySQL);
                        prepSt.setInt(1, updatedSalary);
                        prepSt.setString(2, selectedEmployee);
                        prepSt.executeUpdate();
                        break;

                        default: System.out.println("Exiting");
                }
                       
                default: System.out.println("Exiting");  
        }
                //Return company table data
         ResultSet rs = statement.executeQuery("SELECT * FROM COMPANY;");
            while(rs.next()) {
            int id = rs.getInt("ID");
            String inputName = rs.getString("NAME");
            int inputAge = rs.getInt("AGE");
            String inputAddress = rs.getString("ADDRESS");
            int  inputSalary = rs.getInt("SALARY");
            System.out.println( "ID = " + id );
            System.out.println( "NAME = " + inputName );
            System.out.println( "AGE = " + inputAge );
            System.out.println( "ADDRESS = " + inputAddress );
            System.out.println( "SALARY = " + inputSalary );
            System.out.println(); 
            }
            rs.close();
          
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
