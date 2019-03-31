package Tables;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static Tables.FunctionUtil.calculduree;
import java.text.ParseException;

/**
 *
 * @author jefferson
 */
public class RenduVeloV2 {

    /*ETAT CLIENT NON ABONNEE*/
    public static void clientNonAbonne(Connection conn) throws SQLException {
        int numStation = Station.getlisteStationLibre(conn);
        int numBornette = Station.getBornetteLibreINStation(conn, numStation);
        //traitement du numéro de velo
        Statement stmt = conn.createStatement();

        ResultSet rsVelo = stmt.executeQuery("SELECT numVelo from LocationNonAbonne WHERE finLocation IS NULL");
        //place toute les mots de passe dans une araylist
        List<Integer> Velo = new ArrayList<Integer>();

        //ajout de velo dans la liste
        while (rsVelo.next()) {
            Velo.add(rsVelo.getInt(1));
        }
        rsVelo.close();
        //recuperation de velo
        int numVelo = LectureClavier.lireEntier("Entrer numVelo:");
        //si le numero de velo n'existe pas
        while (!Velo.contains(numVelo)) {
            numVelo = LectureClavier.lireEntier("Le velo n'existe pas reesayer: ");
        }

        ResultSet rsVeloBornete = stmt.executeQuery("SELECT CodeSecret from LocationNonAbonne");
        //place toute les mots de passe dans une araylist
        List<String> CodeSecrets = new ArrayList<>();
        while (rsVeloBornete.next()) {
            CodeSecrets.add(rsVeloBornete.getString(1));
        }
        //recuperation de code secret
        System.out.println("Entrer votre code secret :");
        String CodeSecret = LectureClavier.lireChaine();
        //si le mot de passe n'existe pas
        while (!CodeSecrets.contains(CodeSecret)) {
            System.out.println("Reesayer :");
            CodeSecret = LectureClavier.lireChaine();
        }
        rsVeloBornete.close();
        /*///////////////////*/
 /*DEBUT DE TRAITEMENT*/
 /*\\\\\\\\\\\\\\\\\\*/
        PreparedStatement pstmt = conn.prepareStatement("UPDATE LocationNonAbonne SET FinLocation = ?, StationArrivee =? "
                + "Where numVelo = ? and CodeSecret = ?");
        pstmt.setTimestamp(1, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
        pstmt.setInt(2, numStation);
        pstmt.setInt(3, numVelo);
        pstmt.setString(4, CodeSecret);
        pstmt.executeUpdate();
        pstmt.close();
        int remarque = LectureClavier.lireEntier("Réussi : Etat du velo Rendu  ? 1-(HorsService) ou 2-(EnService)");
        switch (remarque) {
            case (1):
                // REQUETE UPDATE
                System.out.println("Deposer le velo sur la bournette"+numBornette);
                
                CallableStatement cstmt1 = conn.prepareCall("{ call updateEtatVeloBornetteRendu(?,?,?,?,?,?) }");
                cstmt1.setInt(1, numVelo);
                cstmt1.setInt(2, numBornette);
                cstmt1.setString(3,"Disponible");
                cstmt1.setString(4, "Occupe");
                cstmt1.setInt(5, numStation);
                cstmt1.setString(6, "HorsService");
                if (cstmt1.executeUpdate()>0)System.out.println("MAJ Reussi");

              
                cstmt1.close();
                break;
            case 2:
                System.out.println("Deposer le velo sur la bournette"+numBornette);
                CallableStatement cstmt2 = conn.prepareCall("{ call updateEtatVeloBornetteRendu(?,?,?,?,?,?) }");
                cstmt2.setInt(1, numVelo);
                cstmt2.setInt(2, numBornette);
                cstmt2.setString(3, "Disponible");
                cstmt2.setString(4, "Occupe");
                cstmt2.setInt(5, numStation);
                cstmt2.setString(6, "EnService");
                if (cstmt2.executeUpdate()>0)System.out.println("MAJ Reussi");
                cstmt2.close();
               
                break;
            default:
                System.out.println("ERREUR de choix réessayer");
                break;
        }
        stmt.close();
    }

    /*ETAT CLIENT NON ABONNEE*/
    public static void clientAbonne(Connection conn, int IdClient) throws SQLException, ParseException {
        //definition des attributs 
        int numStation = Station.getlisteStationLibre(conn);
        int numBornette = Station.getBornetteLibreINStation(conn, numStation);
        //traitement du numéro de velo
        Statement stmt = conn.createStatement();
        ResultSet rsVelo = stmt.executeQuery("SELECT numVelo from LocationAbonne WHERE finLocation IS NULL and numClientAbonne = "
                + IdClient);
        //place toute les mots de passe dans une araylist
        List<Integer> Velo = new ArrayList<>();

        //ajout de velo dans la liste
        while (rsVelo.next()) {
            Velo.add(rsVelo.getInt(1));

        }
        //recuperation de velo
        int numVelo = LectureClavier.lireEntier("Entrer numVelo : ");
        //si le numero de velo n'existe pas
        while (!Velo.contains(numVelo)) {
            numVelo = LectureClavier.lireEntier("Le velo n'existe pas reesayer : ");
        }
        rsVelo.close();
        /*///////////////////*/
 /*DEBUT DE TRAITEMENT*/
 /*\\\\\\\\\\\\\\\\\\*/
       
        PreparedStatement pstmt = conn.prepareStatement("UPDATE LocationAbonne SET FinLocation = ?, StationArrivee =? "
                + "Where numVelo = ? and numClientAbonne = ?");
        pstmt.setTimestamp(1, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
        pstmt.setInt(2, numStation);
        pstmt.setInt(3, numVelo);
        pstmt.setInt(4, IdClient);
        pstmt.executeUpdate();
        pstmt.close();
        int remarque = LectureClavier.lireEntier("Réussi : des problèmes au niveau du vélo ? 1-(oui) ou 2-(non)");
        switch (remarque) {
            case (1):

                System.out.println("Deposer le velo sur la bournette"+numBornette);
                CallableStatement cstmt1 = conn.prepareCall("{ call updateEtatVeloBornetteRendu(?,?,?,?,?,?) }");
                cstmt1.setInt(1, numVelo);
                cstmt1.setInt(2, numBornette);
                cstmt1.setString(3, "Disponible");
                cstmt1.setString(4, "Occupe");
                cstmt1.setInt(5, numStation);
                cstmt1.setString(6, "HorsService");
                if (cstmt1.executeUpdate()>0)System.out.println("MAJ Reussi");
                cstmt1.close();
               
                break;
            case 2:
                System.out.println("Deposer le velo sur la bournette"+numBornette);
                CallableStatement cstmt2 = conn.prepareCall("{ call updateEtatVeloBornetteRendu(?,?,?,?,?,?) }");
                cstmt2.setInt(1, numVelo);
                cstmt2.setInt(2, numBornette);
                cstmt2.setString(3, "Disponible");
                cstmt2.setString(4, "Occupe");
                cstmt2.setInt(5, numStation);
                cstmt2.setString(6, "EnService");
                if (cstmt2.executeUpdate()>0)System.out.println("MAJ Reussi");
                cstmt2.close();
                /*REQUETE UPDATE DE LA BORNETTE DANS LE CAS OU LE VELO N'EST PAS EN PANNE*/

                break;
            default:
                System.out.println("ERREUR de choix réessayer");
                break;
        }
        stmt.close();
        double prix =calculduree(conn,IdClient);
        System.out.println("Votre prix est : "+prix);
        
    }
    
}
