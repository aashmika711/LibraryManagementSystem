package com.learnJDBC1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Book {
	public void addBook(Connection conn,String title,String author,int quantity)throws SQLException {
		String sql="INSERT INTO books(title,author,quantity)VALUES(?,?,?)";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1,title);
		ps.setString(2,author);
		ps.setInt(3,quantity);
		ps.executeUpdate();
		System.out.println("book added successfully!");
		
	}
	public void viewBooks(Connection conn)throws SQLException{
		String sql="SELECT * FROM books";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		System.out.println("\n BooksList");
		while (rs.next()) {
			System.out.println(rs.getInt("id")+"|"+rs.getString("title")+"|"+rs.getString("author")+"|Qty:"+rs.getInt("quantity"));
			
			
			
			
		}
		
	}

}