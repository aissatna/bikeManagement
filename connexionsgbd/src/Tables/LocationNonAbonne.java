/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables;

import java.sql.*;
import java.util.*;

/**
 *
 * @author nabil
 */
public class LocationNonAbonne {

    public static void AjoutLocationNonAbonne(Connection conn) throws SQLException {

        int IDstation = Station.getIDStation(conn);
        System.out.println("ID Station-->" + IDstation);
        // Liste Of modele velo disponible 
        int IDModele = Modele.getModeleID(conn);
        System.out.println("ID Modele-->" + IDModele);
        // get Bornette &  velo disponible 
        Statement stmt = conn.createStatement();
        ResultSet rs4 = stmt.executeQuery("Select numBornette From Velo NATURAL JOIN Modele NATURAL JOIN Bornette where  EtatVelo = 'EnService' AND DisponibiliteVelo = 'Disponible' AND numModele= " + IDModele
                + " AND etatBornette = 'EnService' AND disponibilite = 'Occupe'"
                + " AND numStation= " + IDstation);;

        List<Integer> BornetteList = new ArrayList<Integer>();
        int IDVelo = -1;
        while (rs4.next()) {
            BornetteList.add(rs4.getInt(1));
        }
        if (BornetteList.isEmpty()) {
            System.out.println("Pas du velo disponible dans ce modele a cette station");
        } else {
            rs4 = stmt.executeQuery("Select numVelo from Bornette where numBornette = " + BornetteList.get(0));
            while (rs4.next()) {
                IDVelo = rs4.getInt(1);
            }
            System.out.print("Entrez votre Numero CB:");
            String NumCB = LectureClavier.lireChaine();
            CallableStatement cstmt = conn.prepareCall("{?=call AjoutClientNonAbonne(?)}");

            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setString(2, NumCB);
            cstmt.execute();
            // Get the new ID client back
            int IDclient = cstmt.getInt(1);

            System.out.println("ID cleint Non abonnne --->" + IDclient);

            System.out.println("Recuperer Le velo Numero " + IDVelo + " sur la Bornette " + BornetteList.get(0));
        }

    }

}
