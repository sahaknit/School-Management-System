package form;

import java.sql.*;
import javax.swing.JOptionPane;

public class connectionDB {
    // This static variable stays alive in the background
    private static Connection con = null; 
    
    public static Connection getConnection() {
        try {
            // THE FIX: Only log in if we don't already have an open connection!
            if (con == null || con.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // IMPORTANT: Put your actual Aiven password back in the quotes here!
                con = DriverManager.getConnection("jdbc:mysql://school-management-db-school-management-db.l.aivencloud.com:28400/defaultdb?verifyServerCertificate=false&useSSL=true&allowPublicKeyRetrieval=true", "avnadmin", "YOUR_PASSWORD_HERE");
                
                System.out.println("New Cloud Connection Opened!"); // Just to help you test
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Form Database Error: " + e.getMessage());
        }
        
        // Return the ultra-fast, already-open connection
        return con; 
    }
}