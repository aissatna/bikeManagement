/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nabil
 */
public class Station {

    public static int getIDStation(Connection conn) throws SQLException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT numStation,adresse FROM station ");
        List<Integer> StationList = new ArrayList<Integer>();
        while (rs.next()) {
            System.out.print(rs.getInt(1) + "-->");
            System.out.print(rs.getString(2));
            System.out.println("");
            StationList.add(rs.getInt(1));
        }
        int IDstation = LectureClavier.lireEntier("Donner le numéro de la station dans la liste ci-dessus");
        while (!StationList.contains(IDstation)) {
            IDstation = LectureClavier.lireEntier("Donner un la station dans la liste SVP : ");
        }
        rs.close();
        stmt.close();
        return IDstation;
    }
    
   public static String getStationType(Connection conn , int IDstation ) throws SQLException{
       Statement stmt = conn.createStatement();
       Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String dateStr = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
        System.out.println("" + dateStr);
        String TypeStation = "null";
        ResultSet rs2 = stmt.executeQuery("SELECT distinct type from station NATURAL JOIN plageHoraire where "
                + "TO_DATE('" + dateStr + "','hh24:mi:ss') > HeureDebut AND "
                + "TO_DATE('" + dateStr + "','hh24:mi:ss') < HeureFin AND "
                + "numStation = " + IDstation);
        while (rs2.next()) {
           
            TypeStation = rs2.getString(1);
        }
        rs2.close();
        stmt.close();
        return TypeStation;
   }
}
