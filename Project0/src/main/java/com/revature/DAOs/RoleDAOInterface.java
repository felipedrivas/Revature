package com.revature.DAOs;

import com.revature.models.Role;

import java.util.List;

/* This Interface will lay out all of the methods that RoleDAO must implement
   Why take this extra step? This is a great way to document what method are found in RoleDAO
  Imagine a DAO Class with 100 JDBC methods - That would be really long. This is a good quick reference */
public interface RoleDAOInterface {

    //A method that get Roles by their Id
    Role getRoleById(int id);

    //A method that updates a Role Balance
    int updateRoleBalance(int id, int newBalance);


    //a hypothetical method that would return all Roles in the DB
//    List<Role> getAllRoles();

    //a hypothetical method that would insert a new Role
//    Role insertRole(Role r);

}