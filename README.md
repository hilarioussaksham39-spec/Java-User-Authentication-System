# Java User Authentication System

A console-based user authentication system built using Java and MySQL. The project demonstrates user registration, login verification, password hashing, salt generation, and database connectivity through JDBC.

## Features

- **User Registration:** Register a user with a username and password.
- **User Login:** Verify login credentials against stored database records.
- **Salt Generation:** Generate a unique salt for each user.
- **Password Verification:** Recalculate the hash using the supplied password and stored salt.
- **MySQL Integration:** Store and retrieve user authentication data using JDBC.
- **Menu-Driven Interface:** Interact with the application through a simple console menu.

## Technologies Used

- Java
- MySQL
- JDBC (Java Database Connectivity)
- MySQL Connector/J
- SQL

## Project Structure

```text
Java-User-Authentication-System/
├── src/
│   └── Main.java
├── lib/
│   └── mysql-connector-j.jar
├── database/
│   └── schema.sql
├── README.md
└── .gitignore
```

## Prerequisites

Before running the project, install:

- Java JDK
- MySQL Server
- MySQL Connector/J
- A Java IDE such as Visual Studio Code, IntelliJ IDEA, or Eclipse

## Database Setup

1. Start your MySQL server.
2. Open MySQL Workbench or another MySQL client.
3. Execute the SQL script in `database/schema.sql` to create the database and users table.
4. Configure the database connection details in the Java application to match your local MySQL setup.

## How It Works

### Registration

1. The user enters a username and password.
2. The application generates a random salt.
3. It combines the password, salt, and configured pepper.
4. The application calculates a hash and stores the username, hash, and salt in MySQL.

### Login

1. The user enters their username and password.
2. The application retrieves the stored hash and salt.
3. It calculates a hash using the entered password and stored salt.
4. It compares the calculated hash with the stored hash and reports whether the login succeeds.

## Security Notes

This project was developed as a learning exercise to understand authentication, hashing, salts, and database integration.

**Important:** The current custom hashing function is not suitable for protecting real user passwords. A production implementation should use a dedicated password-hashing algorithm such as Argon2id, scrypt, or PBKDF2, together with securely generated salts. Secrets should not be hardcoded or committed to version control.

Do not use real or sensitive passwords with the current implementation.

## Future Improvements

- Replace the custom hash with a secure password-hashing algorithm.
- Use `SecureRandom` for salt generation.
- Move database credentials and secret values into environment-based configuration.
- Improve database resource management and error handling.
- Add username validation and duplicate-registration handling.
- Introduce unit tests and a graphical user interface.

## Learning Objectives

This project explores:

- Java classes and constructors
- User input handling
- Hashing and salt concepts
- SQL queries and prepared statements
- JDBC database connectivity
- Basic authentication workflows

## License

This project is available for educational and portfolio purposes. Add a license file if you intend to specify formal reuse and distribution terms.
