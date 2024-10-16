package com.revature.controllers;

import com.revature.DAOs.EmployeeDAO;
import com.revature.models.Employee;
import io.javalin.http.Handler;


import java.util.ArrayList;

/* The Controller Layer is where HTTP Requests get sent after Javalin directs them from main()
 * It's in this layer that JSON comes in and gets translated to Java and vice versa
 * We'll be taking in HTTP Requests from the client, and sending back HTTP Reponses
 * The Controller's job is to process HTTP Requests and respond to them appropriately*/
public class EmployeeController {

    //We need an EmployeeDAO to use the JDBC methods (get all employees, insert employee)
    EmployeeDAO eDAO = new EmployeeDAO();

    //This Handler will handle GET requests to /employees
    public Handler getEmployeesHandler = ctx -> {

        //Populate an ArrayList of Employee objects from the DAO!
        ArrayList<Employee> employees = eDAO.getAllEmployees();

        //PROBLEM: We can't send plain Java in an HTTP response - we want to use JSON

        //SOLUTION: We can use the ctx.json() method to convert this ArrayList to JSON
        //Note: This also returns the object to the client once the code block completes. Convenient!
        ctx.json(employees);

        //We can also set the status code ctx.status()
        ctx.status(200); //OK

    };

    //This Handler will handle POST requests to /employees
    public Handler insertEmployeeHandler = ctx -> {

        //TODO: we could have some check to make sure the request body has data in the first place
        //ctx.body() == a valid object? If not, return a 400 status code
        //ctx.body() accesses the request body, but doesn't attempt any class conversion etc.

        //We have JSON coming in (we're sending an Employee object through Postman)
        //We need to convert that JSON into a Java object before we can send it to the DAO
        //We can use ctx.bodyAsClass() to do this (HTTP Request body -> Java Object)
        Employee newEmployee = ctx.bodyAsClass(Employee.class);

        //Let's show off some error handling - make sure the new Employee has a first and last name

        //.isBlank() checks if the String is empty or just whitespace
        if(newEmployee.getFirst_name() == null || newEmployee.getFirst_name().isBlank()){
            ctx.result("First name is required!!");
            ctx.status(400); //Bad Request - the user needs to include a first name!
        } else if(newEmployee.getLast_name() == null || newEmployee.getLast_name().isBlank()){
            ctx.result("Last name is required!!");
            ctx.status(400); //Bad Request - the user needs to include a last name!
        } else {
            //if the "if"s don't trigger, then the inputted Employee is good!
            Employee insertedEmployee = eDAO.insertEmployee(newEmployee);
            ctx.status(201); //Created - we created some data in the DB successfully
            ctx.json(insertedEmployee); //Send the employee back to the Client
        }

        //NOTE: This error handling will actually get stuffed into the Service layer
        //The service layer will have try/catches for DAO methods
        //And the controller layer will have try/catches for the Service layer and send error codes

        //But we're not there yet :)

    };

}
