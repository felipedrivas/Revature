package com.revature.DAOs;

import com.revature.models.Employee;

import java.util.ArrayList;

//Remember Interfaces are good for laying out what methods a Class must implement
public interface EmployeeDAOInterface {

    //A method to insert a new Employee
    Employee insertEmployee(Employee emp);

    //A method to get all Employees
    ArrayList<Employee> getAllEmployees();

    //TODO: We could do delete too, but I'll leave that for you to figure out
    //(If you know how to do Update, you can do Delete)

}
