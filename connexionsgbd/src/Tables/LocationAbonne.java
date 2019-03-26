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
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author nabil
 */
public class LocationAbonne {

    private int numClientAbonne;
    private int numVelo;
    private LocalDate DebutLocation;
    private LocalDate FinLocation;
    private int Duree;
    private int StationDepart;
    private int StationArrivee;

    public LocationAbonne() {
    }

    public void Ajout_Location_Abonne(Connection conn, int id_Client, int Id_velo, int id_StationDepart) throws SQLException {
        this.numClientAbonne = id_Client;
        this.numVelo = Id_velo; // Reste a verifier 
        this.DebutLocation = LocalDate.now();
        this.FinLocation = null;
        this.Duree = 0;
        this.StationDepart = id_StationDepart;
        this.StationArrivee = -1;
        Statement st = conn.createStatement();
        int nb = st.executeUpdate("INSERT INTO LocationAbonne(numClientAbonne,numVelo,DebutLocation,FinLocation,Duree,StationDepart,StationArrivee)VALUES("
                + this.numClientAbonne + "," + this.numVelo + ",TO_DATE('" + this.DebutLocation + "','yyyy-MM-dd')," + this.FinLocation + ","
                + this.Duree + "," + this.StationDepart + "," + this.StationArrivee + ")");
        if (nb > 0) {
            System.out.println("Location enregistrée ....");

        } else {
            System.out.println("Erreur dans l'insertion ??? ");
        }

    }

    public int get_NumClient_Abonne(Connection conn, String nom, String codeSecret) throws SQLException {
        int numClientAbonne = -1;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(" select numClientAbonne from clientabonne where nom = '" + nom + "' and codesecret = '" + codeSecret + "' ");
        System.out.println("SELECT numClientAbonne FROM ClientAbonne WHERE nom ='" + nom + "'and codeSecret ='" + codeSecret + "'");
        while (rs.next()) {
            numClientAbonne = rs.getInt(1);
        }
        rs.close();
        st.close();
        return numClientAbonne;

    }

    public int get_NumStation(Connection conn, String adresseStation) throws SQLException {
        int numStation = -1;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT numStation FROM station WHERE adresse like '%" + adresseStation + "%'");
        while (rs.next()) {
            numStation = rs.getInt(1);
        }
        rs.close();
        st.close();
        return numStation;

    }
}
