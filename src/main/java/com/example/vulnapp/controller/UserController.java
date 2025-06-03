package com.example.vulnapp.controller;

import org.springframework.web.bind.annotation.*;
import java.sql.*;

@RestController
public class UserController {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/users";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "password123";

    @GetMapping("/user")
    public String getUser(@RequestParam String username) throws Exception {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        String query = "SELECT * FROM users WHERE username = '" + username + "'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        if (rs.next()) {
            return "Welcome " + rs.getString("username");
        } else {
            return "User not found";
        }
    }

    @PostMapping("/echo")
    public String echoInput(@RequestParam String input) {
        return "<html><body>You said: " + input + "</body></html>";
    }
}
