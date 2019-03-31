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
            System.out.print(rs.getInt(1) + "-->" + rs.getString(2));
            System.out.println("");
            StationList.add(rs.getInt(1));
        }
        int IDstation = LectureClavier.lireEntier("Donner le numéro de la station dans la liste ci-dessus");
        while (!StationList.contains(IDstation)) {
            IDstation = LectureClavier.lireEntier("Choisir une station dans la liste SVP: ");
        }
        rs.close();
        stmt.close();
        return IDstation;
    }

    public static String getStationType(Connection conn, int IDstation) throws SQLException {
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

    public static int getlisteStationLibre(Connection conn) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT distinct numstation, adresse FROM bornette natural join station WHERE disponibilite = 'Libre' AND etatBornette ='EnService'");
        List<Integer> StationList = new ArrayList<Integer>();
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " -->" + rs.getString(2));
            StationList.add(rs.getInt(1));
        }

        int numStation = LectureClavier.lireEntier("Choisir une Station");
        while (!StationList.contains(numStation)) {
            numStation = LectureClavier.lireEntier("Choisir une station dans la liste SVP: ");
        }
        rs.close();
        st.close();
        return numStation;
    }
    public static int getBornetteLibreINStation(Connection conn, int numStation) throws SQLException {
        Statement st= conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT numBornette"
                + " FROM bornette"
                + " WHERE numStation = + '" + numStation + "' AND EtatBornette = 'EnService' "
                + " AND disponibilite = 'Libre'");

        List<Integer> Bornette = new ArrayList<>();
        //ajout de Bornette dans la liste
        while (rs.next()) {
            Bornette.add(rs.getInt(1));
        }
        //traitement de la bornette
        rs.close();
        st.close();
        return Bornette.get(0);
    }
    //type 0 = VNul
    //type 1 = VMoins
    //type 2 = VPlus
      public static void stationsDeType(Connection conn, int type) throws SQLException {

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);  
        String dateStr = cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
        
        ArrayList<String> result = new ArrayList<String>();
        
        String typeStr = "";
        if(type == 0){
            typeStr = "Vnul";
        }else if(type == 1){
            typeStr = "Vmoins";
        }else if(type == 2){
            typeStr = "Vplus";
        }

        
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT UNIQUE adresse FROM Station "
                + "NATURAL JOIN PlageHoraire WHERE TO_DATE("
                + "'"+dateStr+"','hh24:mi:ss') > HeureDebut "
                        + "AND TO_DATE("
                + "'"+dateStr+"','hh24:mi:ss') < HeureFin AND Type = '"+typeStr+"'");
        while (rs.next()) {
            result.add(rs.getString("adresse"));
        }
         
            System.out.println(result);
        
       rs.close();
       st.close();

       
    }

}
