package com.learnJDBC1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class IssueReturn {

    public void issueBook(Connection conn, int bookId, int memberId) throws SQLException {
        // Check if book is available
        String checkQty = "SELECT quantity FROM books WHERE id=?";
        PreparedStatement checkPs = conn.prepareStatement(checkQty);
        checkPs.setInt(1, bookId);
        ResultSet rs = checkPs.executeQuery();

        if (rs.next() && rs.getInt("quantity") > 0) {
            // Insert into issued_books
            String sql = "INSERT INTO issued_books(book_id, member_id, issue_date) VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bookId);
            ps.setInt(2, memberId);
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            ps.executeUpdate();

            // Update book quantity
            String updateQtyString = "UPDATE books SET quantity = quantity - 1 WHERE id = ?";
            PreparedStatement ps2 = conn.prepareStatement(updateQtyString);
            ps2.setInt(1, bookId);
            ps2.executeUpdate();

            System.out.println("Book issued successfully!");
        } else {
            System.out.println("Book not available.");
        }
    }

    public void returnBook(Connection conn, int issueId) throws SQLException {
        // First get the book_id from issued_books
        String getBookId = "SELECT book_id FROM issued_books WHERE id=?";
        PreparedStatement ps1 = conn.prepareStatement(getBookId);
        ps1.setInt(1, issueId);
        ResultSet rs = ps1.executeQuery();

        if (rs.next()) {
            int bookId = rs.getInt("book_id");

            // Update return_date
            String sql = "UPDATE issued_books SET return_date=? WHERE id=?";
            PreparedStatement ps2 = conn.prepareStatement(sql);
            ps2.setDate(1, Date.valueOf(LocalDate.now()));
            ps2.setInt(2, issueId);
            ps2.executeUpdate();

            // Increment book quantity
            String updateQtyString = "UPDATE books SET quantity = quantity + 1 WHERE id=?";
            PreparedStatement ps3 = conn.prepareStatement(updateQtyString);
            ps3.setInt(1, bookId);
            ps3.executeUpdate();

            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Issue record not found.");
        }
    }
}