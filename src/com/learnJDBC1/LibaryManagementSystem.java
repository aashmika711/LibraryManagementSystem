package com.learnJDBC1;



import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

import java.util.Scanner;



public class LibaryManagementSystem {



			    public static void main(String[] args) {

		        String url = "jdbc:mysql://localhost:3306/library";

		        String user = "root"; 

		        String password = "Aash@0711"; // change if needed



		        try (Connection conn = DriverManager.getConnection(url, user, password);

		             Scanner sc = new Scanner(System.in)) {



		            Book bookObj = new Book();

		            Member memberObj = new Member();

		            IssueReturn issueObj = new IssueReturn();



		            while (true) {

		                System.out.println("\n===== 📚 Library Management Menu =====");

		                System.out.println("1. Add Book");

		                System.out.println("2. View Books");

		                System.out.println("3. Add Member");

		                System.out.println("4. View Members");

		                System.out.println("5. Issue Book");

		                System.out.println("6. Return Book");

		                System.out.println("0. Exit");

		                System.out.print("Enter choice: ");

		                int choice = sc.nextInt();

		                sc.nextLine();



		                switch (choice) {

		                    case 1:

		                        System.out.print("Enter title: ");

		                        String title = sc.nextLine();

		                        System.out.print("Enter author: ");

		                        String author = sc.nextLine();

		                        System.out.print("Enter quantity: ");

		                        int qty = sc.nextInt();

		                        bookObj.addBook(conn, title, author, qty);

		                        break;



		                    case 2:

		                        bookObj.viewBooks(conn);

		                        break;



		                    case 3:

		                        System.out.print("Enter name: ");

		                        String name = sc.nextLine();

		                        System.out.print("Enter email: ");

		                        String email = sc.nextLine();

		                        memberObj.addMember(conn, name, email);

		                        break;


		                    case 4:

		                        memberObj.viewMembers(conn);

		                        break;



		                    case 5:

		                        System.out.print("Enter Book ID: ");

		                        int bId = sc.nextInt();

		                        System.out.print("Enter Member ID: ");

		                        int mId = sc.nextInt();

		                        issueObj.issueBook(conn, bId, mId);

		                        break;



		                    case 6:

		                        System.out.print("Enter Issue ID: ");

		                        int iId = sc.nextInt();

		                        issueObj.returnBook(conn, iId);

		                        break;



		                    case 0:

		                        System.out.println("👋 Exiting...");

		                        return;



		                    default:

		                        System.out.println("❌ Invalid choice!");

		                }

		            }

		        } catch (SQLException e) {

		            e.printStackTrace();

		        }

		    }

		

	}