package com.revature.models;

/*What is a DTO? Data Transfer Object. It's meant to model some data that doesn't pertain to a DB table
For instance, maybe we have login functionality that only takes username/password
We want a user to be able to log in with ONLY their username/password instead of an entire user object
NOTE: we never store DTOs in the DB - they're solely for DATA TRANSFER */
public class LoginDTO {

    //We just need this object to store user_id and first_name to help with login
    private int user_id;
    private String first_name;

    //boilerplate code--------------------------------

    //no args, all args, getters/setters, toString
    public LoginDTO() {
    }

    public LoginDTO(int user_id, String first_name) {
        this.user_id = user_id;
        this.first_name = first_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "user_id=" + user_id +
                ", first_name='" + first_name + '\'' +
                '}';
    }
}
