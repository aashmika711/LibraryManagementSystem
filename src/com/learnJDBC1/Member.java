package com.learnJDBC1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Member {

    public void addMember(Connection conn, String name, String email) throws SQLException {
        String sql = "INSERT INTO members(name, email) VALUES(?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, email);
        ps.executeUpdate(); // ✅ Execute the insert
        System.out.println("Member added successfully!");
    }

    public void viewMembers(Connection conn) throws SQLException { // ✅ Name matches main class
        String sql = "SELECT * FROM members"; // ✅ Correct SQL
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        System.out.println("\nMembers List:");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " | " +
                               rs.getString("name") + " | " +
                               rs.getString("email"));
        }
    }
}