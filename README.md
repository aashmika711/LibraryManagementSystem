Library Management System
A console-based Library Management System developed using Java, JDBC, and MySQL. It allows users to manage books and library members, and to issue and return books through a menu-driven interface.
Features
- Add new books with title, author, and quantity
- View all available book records
- Register library members with name and email
- View registered members
- Issue a book to a member
- Return an issued book
- Automatically decrease book quantity when a book is issued
- Automatically increase book quantity when a book is returned
Technologies Used
- Java
- JDBC (Java Database Connectivity)
- MySQL
- Eclipse IDE (project configuration is included)
Project Structure
LibraryManagementSystem/
├── src/
│   ├── com/learnJDBC1/
│   │   ├── Book.java
│   │   ├── Member.java
│   │   ├── IssueReturn.java
│   │   └── LibaryManagementSystem.java
│   └── module-info.java
└── README.md
Database Setup
1. Install MySQL and start the MySQL server.
2. Create a database:
CREATE DATABASE library;
USE library;
3. Create the tables used by the application:
CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    quantity INT NOT NULL
);

CREATE TABLE members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE issued_books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT NOT NULL,
    member_id INT NOT NULL,
    issue_date DATE NOT NULL,
    return_date DATE,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (member_id) REFERENCES members(id)
);
Configuration
The main Java class connects to the local MySQL database named library.

Update the connection settings in LibaryManagementSystem.java to match your MySQL setup:
String url = "jdbc:mysql://localhost:3306/library";
String user = "your_mysql_username";
String password = "your_mysql_password";
Security note: Do not commit real database passwords to GitHub. For a public repository, load credentials from environment variables or a separate configuration file that is excluded from version control.

Make sure the MySQL Connector/J driver is added to the project's build path. The project uses the java.sql module.
How to Run
1. Open the project in Eclipse or another Java IDE.
2. Make sure MySQL is running and the database and tables are created.
3. Add MySQL Connector/J to the project build path.
4. Update the database connection details.
5. Run LibaryManagementSystem.java as a Java application.
6. Choose an option from the menu displayed in the console.
Application Menu
===== Library Management Menu =====
1. Add Book
2. View Books
3. Add Member
4. View Members
5. Issue Book
6. Return Book
0. Exit
Enter the corresponding number and follow the prompts.
How Book Issue and Return Work
- When issuing a book, the application checks whether the book record exists and its quantity is greater than zero.
- If available, it creates an issue record with the current date and reduces the book quantity by one.
- When returning a book, enter its issue record ID. The application records the return date and increases the book quantity by one.
Limitations
- The application is console-based; it does not include a graphical user interface.
- It does not currently include fine calculation or overdue-book notifications.
- Input validation and transaction handling can be improved for a more robust application.
Future Enhancements
- Add search functionality for books and members.
- Prevent returning the same issue record more than once.
- Add input validation and database transaction management.
- Add a graphical user interface or web interface.
- Add due dates and fine calculation.
