
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nabil
 */
public class RequetesBd {
 public static void getModele(Connection conn) throws SQLException {  
    // Get a statement from the connection
        Statement stmt = conn.createStatement();
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM Modele");
        while (rs.next()) {
            System.out.print("numModele : " + rs.getInt(1) + "");
            System.out.print("Type : " + rs.getString(2) + "");
            System.out.println("prix: " + rs.getInt(3));
        }
        System.out.println();
        // Close the result set, statement and theconnection 
        rs.close();
        stmt.close();
     
     
     
}
}
