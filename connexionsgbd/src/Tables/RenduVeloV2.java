package Tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jefferson
 */
public class RenduVeloV2 {

    /*
    public int numStation;
    public int numBornette;
    public int numVelo;
    public String CodeSecret;
     */
    public static int listeStationLibre(Connection conn) throws SQLException {
        Statement st;
        st = conn.createStatement();

        ResultSet rs = st.executeQuery("select distinct numstation, adresse from bornette b natural join station s where disponibilite = 'Libre'");

        while (rs.next()) {
            System.out.println("station dispo : " + rs.getInt(1)
                    + "  |  d'adresse : " + rs.getString(2));
        }

        int numStation = LectureClavier.lireEntier("Choisir une Station");

        rs.close();
        st.close();
        return numStation;
    }

    public static int numeroBornetteLibre(Connection conn, int numStation) throws SQLException {

        numStation = listeStationLibre(conn);

        Statement st;
        st = conn.createStatement();
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
        int numBornette = Bornette.get(0);
        rs.close();
        st.close();

        return numBornette;
    }

    /*ETAT CLIENT NON ABONNEE*/
    public static void clientNonAbonne(Connection conn) throws SQLException {
        //definition des attributs 
        int numStation = listeStationLibre(conn);
        int numBornette = numeroBornetteLibre(conn, numStation);

        //traitement du numéro de velo
        Statement stmtVelo = conn.createStatement();

        ResultSet rsVelo = stmtVelo.executeQuery("SELECT numVelo from LocationNonAbonne WHERE finLocation IS NULL");
        //place toute les mots de passe dans une araylist
        List<Integer> Velo = new ArrayList<>();

        //ajout de velo dans la liste
        while (rsVelo.next()) {
            Velo.add(rsVelo.getInt(1));
            System.out.println("Velo====>" + rsVelo.getInt(1));
        }
        //recuperation de velo
        int numVelo = LectureClavier.lireEntier("Entrer numVelo : ");
        //si le numero de velo n'existe pas
        while (!Velo.contains(numVelo)) {
            numVelo = LectureClavier.lireEntier("Le velo n'existe pas reesayer : ");
        }

        Statement stmt = conn.createStatement();

        ResultSet rsVeloBornete = stmt.executeQuery("SELECT CodeSecret from LocationNonAbonne");
        //place toute les mots de passe dans une araylist
        List<String> CodeSecrets = new ArrayList<>();

        while (rsVeloBornete.next()) {
            CodeSecrets.add(rsVeloBornete.getString(1));
            System.out.println("mdp ====>" + rsVeloBornete.getString(1));
        }
        //recuperation de code secret
        System.out.println("entrer votre code secret :");
        String CodeSecret = LectureClavier.lireChaine();

        //si le mot de passe n'existe pas
        while (!CodeSecrets.contains(CodeSecret)) {
            System.out.println("reesayer :");
            CodeSecret = LectureClavier.lireChaine();
        }

        /*///////////////////*/
 /*DEBUT DE TRAITEMENT*/
 /*\\\\\\\\\\\\\\\\\\*/
        int remarque = LectureClavier.lireEntier("Réussi : des problèmes au niveau du vélo ? 1-(oui) ou 2-(non)");

        switch (remarque) {
            case (1):
                System.out.println("nous vous remercions de votre coopération.");

                /*REQUETE UPDATE DE L'ETAT DU VELO*/
                Statement stVelo;
                stVelo = conn.createStatement();

                int uptVelo;
                uptVelo = stVelo.executeUpdate("UPDATE Velo SET EtatVelo = 'HorsService' WHERE numVelo ='" + numVelo + "'");

                /*REQUETE UPDATE DE LA BORNETTE DANS LE CAS OU LE VELO EST EN PANNE*/
                Statement stBornette;
                stBornette = conn.createStatement();
                int uptBornetteVeloEnpane;
                uptBornetteVeloEnpane = stBornette.executeUpdate("UPDATE Bornette SET disponibilite = 'Occupe',"
                        + "numVelo =" + numVelo + ", numStation = " + numStation + ""
                        + "WHERE numBornette = " + numBornette + "");
                //fermeture statement de bornette
                stBornette.close();
                break;
            case 2:

                /*REQUETE UPDATE DE LA BORNETTE DANS LE CAS OU LE VELO N'EST PAS EN PANNE*/
                Statement stBornette1;
                stBornette1 = conn.createStatement();

                int uptBornetteVeloOk;
                uptBornetteVeloOk = stBornette1.executeUpdate("UPDATE Bornette SET disponibilite = 'Occupe',"
                        + "numVelo =" + numVelo + ", numStation = " + numStation + ""
                        + "WHERE numBornette = " + numBornette + "");

                System.out.println("Vpick vous remercie :) au revoir!");
                //fermeture de statement bornette 1
                stBornette1.close();
                break;
            default:
                System.out.println("ERREUR de choix réessayer");
                break;
        }

        Statement stt;
        stt = conn.createStatement();

        stmtVelo.close();
        stmt.close();

        rsVelo.close();
        rsVeloBornete.close();
    }

    /*ETAT CLIENT NON ABONNEE*/
    public static void clientAbonne(Connection conn, int IdClient) throws SQLException {
        //definition des attributs 
        int numStation = listeStationLibre(conn);
        int numBornette = numeroBornetteLibre(conn, numStation);
        //traitement du numéro de velo
        Statement stmtVelo = conn.createStatement();

        ResultSet rsVelo = stmtVelo.executeQuery("SELECT numVelo from LocationAbonne WHERE finLocation IS NULL");
        //place toute les mots de passe dans une araylist
        List<Integer> Velo = new ArrayList<>();

        //ajout de velo dans la liste
        while (rsVelo.next()) {
            Velo.add(rsVelo.getInt(1));
            System.out.println("Velo====>" + rsVelo.getInt(1));
        }
        //recuperation de velo
        int numVelo = LectureClavier.lireEntier("Entrer numVelo : ");
        //si le numero de velo n'existe pas
        while (!Velo.contains(numVelo)) {
            numVelo = LectureClavier.lireEntier("Le velo n'existe pas reesayer : ");
        }

        /*///////////////////*/
 /*DEBUT DE TRAITEMENT*/
 /*\\\\\\\\\\\\\\\\\\*/
        int remarque = LectureClavier.lireEntier("Réussi : des problèmes au niveau du vélo ? 1-(oui) ou 2-(non)");

        switch (remarque) {
            case (1):
                System.out.println("nous vous remercions de votre coopération.");

                /*REQUETE UPDATE DE L'ETAT DU VELO*/
                Statement stVelo;
                stVelo = conn.createStatement();

                int uptVelo;
                uptVelo = stVelo.executeUpdate("UPDATE Velo SET EtatVelo = 'HorsService' WHERE numVelo ='" + numVelo + "'");

                /*REQUETE UPDATE DE LA BORNETTE DANS LE CAS OU LE VELO EST EN PANNE*/
                Statement stBornette;
                stBornette = conn.createStatement();
                int uptBornetteVeloEnpane;
                uptBornetteVeloEnpane = stBornette.executeUpdate("UPDATE Bornette SET disponibilite = 'Occupe',"
                        + "numVelo =" + numVelo + ", numStation = " + numStation + ""
                        + "WHERE numBornette = " + numBornette + "");
                //fermeture statement de bornette
                stBornette.close();
                break;
            case 2:

                /*REQUETE UPDATE DE LA BORNETTE DANS LE CAS OU LE VELO N'EST PAS EN PANNE*/
                Statement stBornette1;
                stBornette1 = conn.createStatement();

                int uptBornetteVeloOk;
                uptBornetteVeloOk = stBornette1.executeUpdate("UPDATE Bornette SET disponibilite = 'Occupe',"
                        + "numVelo =" + numVelo + ", numStation = " + numStation + ""
                        + "WHERE numBornette = " + numBornette + "");

                System.out.println("Vpick vous remercie :) au revoir!");
                //fermeture de statement bornette 1
                stBornette1.close();
                break;
            default:
                System.out.println("ERREUR de choix réessayer");
                break;
        }
        stmtVelo.close();
        rsVelo.close();
    }
}
