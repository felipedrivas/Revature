package com.revature.DAOs;

import com.revature.models.User;

import java.util.ArrayList;

//Remember Interfaces are good for laying out what methods a Class must implement
public interface UserDAOInterface {

    //A method to insert a new User
    User insertUser(User emp);

    //A method to get all Users
    ArrayList<User> getAllUsers();

    //TODO: We could do delete too, but I'll leave that for you to figure out
    //(If you know how to do Update, you can do Delete)

}