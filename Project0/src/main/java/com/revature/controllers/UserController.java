package com.revature.controllers;

import com.revature.DAOs.UserDAO;
import com.revature.models.User;
import io.javalin.http.Handler;


import java.util.ArrayList;

/* The Controller Layer is where HTTP Requests get sent after Javalin directs them from main()
 * It's in this layer that JSON comes in and gets translated to Java and vice versa
 * We'll be taking in HTTP Requests from the client, and sending back HTTP Reponses
 * The Controller's job is to process HTTP Requests and respond to them appropriately*/
public class UserController {

    //We need an UserDAO to use the JDBC methods (get all users, insert user)
    UserDAO eDAO = new UserDAO();

    //This Handler will handle GET requests to /Users
    public Handler getUsersHandler = ctx -> {

        //Populate an ArrayList of User objects from the DAO!
        ArrayList<User> users = eDAO.getAllUsers();

        //PROBLEM: We can't send plain Java in an HTTP response - we want to use JSON

        //SOLUTION: We can use the ctx.json() method to convert this ArrayList to JSON
        //Note: This also returns the object to the client once the code block completes. Convenient!
        ctx.json(users);

        //We can also set the status code ctx.status()
        ctx.status(200); //OK

    };

    //This Handler will handle POST requests to /users
    public Handler insertUserHandler = ctx -> {

        //TODO: we could have some check to make sure the request body has data in the first place
        //ctx.body() == a valid object? If not, return a 400 status code
        //ctx.body() accesses the request body, but doesn't attempt any class conversion etc.

        //We have JSON coming in (we're sending an User object through Postman)
        //We need to convert that JSON into a Java object before we can send it to the DAO
        //We can use ctx.bodyAsClass() to do this (HTTP Request body -> Java Object)
        User newUser = ctx.bodyAsClass(User.class);

        //Let's show off some error handling - make sure the new User has a first and last name

        //.isBlank() checks if the String is empty or just whitespace
        if(newUser.getFirst_name() == null || newUser.getFirst_name().isBlank()){
            ctx.result("First name is required!!");
            ctx.status(400); //Bad Request - the user needs to include a first name!
        } else if(newUser.getLast_name() == null || newUser.getLast_name().isBlank()){
            ctx.result("Last name is required!!");
            ctx.status(400); //Bad Request - the user needs to include a last name!
        } else {
            //if the "if"s don't trigger, then the inputted User is good!
            User insertedUser = eDAO.insertUser(newUser);
            ctx.status(201); //Created - we created some data in the DB successfully
            ctx.json(insertedUser); //Send the User back to the Client
        }

        //NOTE: This error handling will actually get stuffed into the Service layer
        //The service layer will have try/catches for DAO methods
        //And the controller layer will have try/catches for the Service layer and send error codes

        //But we're not there yet :)

    };

}