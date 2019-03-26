/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;


/**
 *
 * @author nabil
 */
public class Station {
    public Station(){
        
    }
   public void getStationListe(Connection conn) throws SQLException{
       // Get a statement from the connection
        Statement stmt = conn.createStatement();
        // Execute the query
        HashMap<Integer, String> StationList = new HashMap<Integer, String>();
        ResultSet rs = stmt.executeQuery("SELECT numStation,adresse FROM station ");
        while (rs.next()) {
            StationList.put(rs.getInt(1),rs.getString(2));
            
       }
        System.out.println(StationList);
        // Close the result set, statement and theconnection 
        rs.close();
        stmt.close();
     
       
       
       
   }
    
    
}
