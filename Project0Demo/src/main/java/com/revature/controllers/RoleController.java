package com.revature.controllers;

import com.revature.DAOs.RoleDAO;
import com.revature.models.Role;
import io.javalin.http.Handler;

public class RoleController {

    //We need a RoleDAO to use its methods
    RoleDAO rDAO = new RoleDAO();

    //This Handler will handle GET requests to /roles/{id}
    public Handler getRoleByIdHandler = ctx -> {

        //extract the path parameter from the HTTP Request URL
        //NOTE: pathParam() returns a String, but we need it as an int
        int role_id = Integer.parseInt(ctx.pathParam("id"));

        //instantiate a Role that holds the data from the specific role ID
        Role role = rDAO.getRoleById(role_id);

        //Make sure the Role that came back isn't null, if so, send a 404 (NOT FOUND)
        //Make sure the role_id passed in is greater than zero

        if(role_id <= 0){
            ctx.result("ID must be greater than zero!");
            ctx.status(400); //Bad Request
        } else if (role == null){
            ctx.result("Role ID: " + role_id + " not found");
            ctx.status(404);
        } else {
            //Send the Role back to the client in an HTTP Response
            ctx.json(role);
            ctx.status(200); //200 - OK
        }

    };


    //This Handler will handle PATCH requests to roles/{id} and we'll put the new salary in the body
    public Handler updateRoleSalary = ctx -> {

        //The user will include the Role id in the path parameter
        //And they'll include the new salary in the Request body
        int role_id = Integer.parseInt(ctx.pathParam("id"));
        int salary = Integer.parseInt(ctx.body());
        //NOTE: remember, we use body() for single variables and bodyAsClass() for objects

        //TODO: checks on Role Id (won't do these here, cause you can see them in getRoleByIdHandler)

        //TODO: check to make sure the new salary is an int (as opposed to a long, with more digits than an int can hold)

        //User input checks (salary must be greater than 20,000, salary can't be greater than 1,000,000)
        if(salary < 20000){
            ctx.result("Salary must be greater than 20,000");
            ctx.status(400);
        } else if(salary > 1000000){
            ctx.result("Salary must be less than 1,000,000");
            ctx.status(400);
        } else {
            //Call the DAO, try to save the new salary in an int
            int newSalary = rDAO.updateRoleSalary(role_id, salary);
            //TODO: we could check to make sure this doesn't return zero (which means something failed in the DB side)
            ctx.result("Role ID " + role_id + " salary updated to " + newSalary);
            ctx.status(200);
        }

    };


}
