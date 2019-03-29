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
import java.util.List;

/**
 *
 * @author nabil
 */
public class Modele {
    public static int getModeleID(Connection conn) throws SQLException{
         Statement stmt = conn.createStatement();
         ResultSet rs3 = stmt.executeQuery("SELECT numModele,TypeVelo FROM Modele ");
        List<Integer> ModelelList = new ArrayList<Integer>();
        while (rs3.next()) {
            System.out.print(rs3.getInt(1) + "-->");
            System.out.print(rs3.getString(2));
            System.out.println("");
            ModelelList.add(rs3.getInt(1));
        }
        int IDModele = LectureClavier.lireEntier("Donner le numéro du modele dans la liste ci-dessus");
        while (!ModelelList.contains(IDModele)) {
            IDModele = LectureClavier.lireEntier("Donner un numéro dans la liste SVP : ");
        }
        return IDModele;
    }
    
}
