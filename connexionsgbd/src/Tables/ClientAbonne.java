/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables;

/**
 *
 * @author nabil
 */
import Enum.Sexe;
import java.sql.*;
import java.time.LocalDate;
import static Tables.FunctionUtil.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientAbonne {

    private int numClientAbonne;
    private String nom;
    private String prenom;
    private Date dateDeNaissance;
    private Sexe sexe;
    private String adresse;
    private String NumCB;
    private String codeSecret;
    private int prime;
    private LocalDate DebutAbonnement;
    private LocalDate FinAbonnement;

    public ClientAbonne(String nom, String prenom, String dateDeNaissance, Sexe sexe, String adresse, String NumCB) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.adresse = adresse;
        this.NumCB = NumCB;
        this.prime = 0;
        this.DebutAbonnement = LocalDate.now();
        this.FinAbonnement = DebutAbonnement.plusDays(365);
        this.codeSecret = generatePassword();
        try {
            this.dateDeNaissance = castDate(dateDeNaissance);
        } catch (ParseException ex) {
            Logger.getLogger(ClientAbonne.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ClientAbonne() {

    }

    public void Ajout_Client_Abonne(Connection conn, ClientAbonne client) throws SQLException {
        // Get a statement from the connection
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT MAX(numClientAbonne) FROM ClientAbonne");
        while (rs.next()) {
            client.numClientAbonne = (rs.getInt(1)) + 1;
        }

        int nb = st.executeUpdate("INSERT INTO ClientAbonne(numClientAbonne,Nom,Prenom,DateDeNaissance,Sexe,Adresse,NumCB,CodeSecret"
                + ",Prime,DebutAbonnement,FinAbonnement) VALUES('" + client.numClientAbonne + "','" + client.nom + "','" + client.prenom + "', TO_DATE("
                + "'" + client.dateDeNaissance + "','yyyy-MM-dd'),'" + client.sexe + "','" + client.adresse + "','" + client.NumCB + "','" + client.codeSecret + "',"
                + "'" + client.prime + "', TO_DATE("
                + "'" + client.DebutAbonnement + "','yyyy-MM-dd'),TO_DATE("
                + "'" + client.FinAbonnement + "','yyyy-MM-dd'))");
        if (nb > 0) {
            System.out.println("Client enregistrée ....");
            System.out.println("Fin d'abonnement:" + this.FinAbonnement + " votre code secret:" + this.codeSecret);
        } else {
            System.out.println("erreur dans l'insertion");
        }
        rs.close();
        st.close();

    }

    public int loginClient(Connection conn , String nom, String CodeSecret) throws SQLException {
        int numClientAbonne = -1;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(" select numClientAbonne from clientabonne where nom = '" + nom + "' and codesecret = '" + CodeSecret + "' ");
        while (rs.next()) {
            numClientAbonne = rs.getInt(1);
        }
        rs.close();
        st.close();
        return numClientAbonne;
    }
}
