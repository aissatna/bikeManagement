/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables;

import static Tables.FunctionUtil.generatePassword;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nabil
 */
public class Location {
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
            String codeSecret = generatePassword();
            
            CallableStatement cstmt1 = conn.prepareCall("{ call AjoutLocationNonAbonne(?,?,?,?,?,?,?,?) }");
            CallableStatement cstmt2 = conn.prepareCall("{ call updateEtatVeloBornette(?,?,?,?) }");
            cstmt1.setInt(1, IDclient);
            cstmt1.setInt(2, IDVelo);
            cstmt1.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
            cstmt1.setNull(4, 0);
            cstmt1.setInt(5, 0);
            cstmt1.setInt(6, IDstation);
            cstmt1.setInt(7, -1);
            cstmt1.setString(8, codeSecret);
            // updateEtatVeloBornette
            cstmt2.setInt(1, IDVelo);
            cstmt2.setInt(2, BornetteList.get(0));
            cstmt2.setString(3, "Louer");
            cstmt2.setString(4, "Libre");

            if (cstmt1.executeUpdate() > 0 && cstmt2.executeUpdate() > 0) {

                System.out.println("Location enregistrée .... avec MAJ ");
                System.out.println(" Recuperer le velo Numero--> " +IDVelo + " sur la Bornette--> "+BornetteList.get(0)+"code Secret--> "+codeSecret);

            } else {
                System.out.println("Erreur dans l'insertion ??? ");
            }

            cstmt.close();
            cstmt1.close();
            cstmt2.close();
            stmt.close();
            
        }

    }
//------------------------------------------------------------------------------------------
  public static void AjoutLocationAbonne(Connection conn, int IdClient) throws SQLException {
        int IDstation = Station.getIDStation(conn);
        System.out.println("ID Station-->" + IDstation);
        // get Le type de la Station 
        String TypeStation = Station.getStationType(conn, IDstation);
        System.out.println("Vous louez a une station de type: " + TypeStation);
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
        rs4.close();
            CallableStatement cstmt = conn.prepareCall("{ call AjoutLocationAbonne(?,?,?,?,?,?,?) }");
            CallableStatement cstmt1 = conn.prepareCall("{ call updateEtatVeloBornette(?,?,?,?) }");
            cstmt.setInt(1, IdClient);
            cstmt.setInt(2, IDVelo);
            cstmt.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
            cstmt.setNull(4, 0);
            cstmt.setInt(5, 0);
            cstmt.setInt(6, IDstation);
            cstmt.setInt(7, -1);
            // updateEtatVeloBornette
            cstmt1.setInt(1, IDVelo);
            cstmt1.setInt(2, BornetteList.get(0));
            cstmt1.setString(3, "Louer");
            cstmt1.setString(4, "Libre");

            if (cstmt.executeUpdate() > 0 && cstmt1.executeUpdate() > 0) {

                System.out.println("Location enregistrée .... avec MAJ ");
                System.out.println("Recuperer Le velo Numero " + IDVelo + " sur la Bornette " + BornetteList.get(0));

            } else {
                System.out.println("Erreur dans l'insertion ??? ");
            }

            cstmt.close();
            cstmt1.close();
            
        }
       
        // Close the result set, statement and theconnection 

        stmt.close();
    }

}
