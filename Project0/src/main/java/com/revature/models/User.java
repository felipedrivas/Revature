package com.revature.models;

import java.util.Objects;

//Remember, our model Classes should directly model our DB tables
public class User extends Object{

    private int user_id;
    private String first_name;
    private String last_name;

    /*Users will contain entire Role objects instead of just an int foreign key
      an int FK is less useful to us than an entire Role object
      If we have an entire Role object, we have access to all of the Role's DATA as well (not just an int)*/
    private Role role;

    //Another role field - just the id this time
    //This will be useful when inserting a new Users - we can skip making a Role object when inserting users
    //This could also be helpful when updating an User's Role!
    private int role_id_fk;

    //boilerplate code------------------------------

    //no args, all args, all args minus id, getters/setters, toString

    public User() {
    }

    //all args (should NOT have the FK field)
    public User(int user_id, String first_name, String last_name, Role role) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.role = role;
    }

    //all args minus id (SHOULD have the FK field instead of the Role object)
    //We'll be using this constructor to insert Users only!!
    //no ID since it's serial. The DB will generate it for us.
    public User(String first_name, String last_name, int role_id_fk) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.role_id_fk = role_id_fk;
    }

    //getters/setters
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

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getRole_id_fk() {
        return role_id_fk;
    }

    public void setRole_id_fk(int role_id_fk) {
        this.role_id_fk = role_id_fk;
    }

    //toString()
    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", role=" + role +
                ", role_id_fk=" + role_id_fk +
                '}';
    }

}