package com.revature.DAOs;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//This DAO will handle Login (select where user_id = ? and first_name = ?)
//We should have had username/password columns in the users table,
//but I didn't think we would get this far in week 3 and I don't want to do a major refactor
public class AuthDAO {

    public User login(int user_id, String first_name) {
        // Open a connection
        try (Connection conn = ConnectionUtil.getConnection()) {
            // Create our SQL String
            String sql = "SELECT * FROM users WHERE user_id = ? AND first_name = ?";

            // PreparedStatement and fill in the blanks
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setString(2, first_name);

            ResultSet rs = ps.executeQuery();

            // Instantiate a RoleDAO to use the getRoleById method
            RoleDAO rDAO = new RoleDAO();

            // Check if a user is found
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rDAO.getRoleById(rs.getInt("role_id_fk")) // Ensure this method handles nulls properly
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Consider throwing a custom exception or handling it
            throw new RuntimeException("Login failed due to a database error", e);
        }

        // Return null if no user is found
        return null;
    }


}
