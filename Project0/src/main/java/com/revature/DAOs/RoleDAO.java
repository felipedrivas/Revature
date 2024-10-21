package com.revature.DAOs;

import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//This is the Class that will actually talk to the Roles DB table
public class RoleDAO implements RoleDAOInterface{

    @Override
    public Role getRoleById(int id) {

        //Try to open a Connection to the DB
        try(Connection conn = ConnectionUtil.getConnection()){

            //A String that represents our SQL query
            //Note the "?", which just means it's a variable we need to fill in
            String sql = "SELECT * FROM roles WHERE role_id = ?";

            //We need a PreparedStatement to fill in the variable (id)
            //It takes the SQL String we made above
            PreparedStatement ps = conn.prepareStatement(sql);

            //We can now use the id parameter to set the variable with ps.set() methods
            ps.setInt(1, id);

            //Execute the query, save the results in ResultSet
            ResultSet rs = ps.executeQuery(); //executing the query stored in the PreparedStatement

            //Extract the data from the ResultSet into a Role object
            //"if there is a value in the next index of the resultset..."
            if(rs.next()){
                //Then extract the data into Java Role object! Using the all-args constructor
                //we can use rs.get() to get values from the ResultSet!
                //NOTE: you must use the exact names found in the DB column
                Role role = new Role(
                        rs.getInt("role_id"),
                        rs.getString("role_title"),
                        rs.getInt("role_balance")
                );

                //Return the new Role!
                return role;

            }

        } catch (SQLException e){
            e.printStackTrace(); //tells us what went wrong
            System.out.println("Couldn't get Role by ID");
        }

        //This is just a catch-all. If nothing gets returned (bad SQL query?) we get null
        return null;
    }

    @Override
    public int updateRoleBalance(int id, int newBalance) {

        //Try to open a Connection to the DB
        try(Connection conn = ConnectionUtil.getConnection()){

            //SQL statement
            String sql = "UPDATE roles SET role_balance = ? WHERE role_id = ?";

            //Create a PreparedStatement to fill in the variables
            PreparedStatement ps = conn.prepareStatement(sql);

            //ps.set() to set the variables values
            ps.setInt(1, newBalance);
            ps.setInt(2, id);

            //execute the update!
            ps.executeUpdate();

            //return the new balance
            return newBalance;

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Couldn't update Role balance");
        }

        //catch-all, if the update fails, we'll return 0
        return 0;
    }

}