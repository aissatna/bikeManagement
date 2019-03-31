package Tables;

import java.security.SecureRandom;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nabil
 */
public class FunctionUtil {

    /**
     * different dictionaries used
     */
    private static SecureRandom random = new SecureRandom();
    private static final int length = 6;
    private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*_=+-/";
    private static final String dic = ALPHA_CAPS + NUMERIC + ALPHA;

    /**
     *
     * @param date date to caste as string
     * @return sqlDate format
     * @throws ParseException
     */
    public static java.sql.Date castDate(String date) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = format.parse(date);
        return new java.sql.Date(parsed.getTime());
    }

    /**
     *
     * Method will generate random string based on the parameters
     *
     * @return the random password
     */
    public static String generatePassword() {
        String result = "";
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(dic.length());
            result += dic.charAt(index);
        }
        return result;

    }

    public static double calculduree(Connection conn, int IdClient) throws SQLException, ParseException {

        double resultat = 0.0;
        long res = 0;
        int numVelo = 0;
        String Modele;
        int price = 0;
        int prime = 0;

        Statement st;
        st = conn.createStatement();
        ResultSet rts = st.executeQuery("SELECT debutlocation,finlocation,numvelo from LocationAbonne WHERE NUMCLIENTABONNE = " + IdClient + "");

        List<String> Debutlocation = new ArrayList<String>();
        List<String> FinLocation = new ArrayList<String>();
        List<Integer> Velo = new ArrayList<Integer>();

        //ajout de Bornette dans la liste
        while (rts.next()) {
            Debutlocation.add(rts.getString("DEBUTLOCATION"));
            FinLocation.add(rts.getString("FINLOCATION"));
            Velo.add(rts.getInt("NUMVELO"));
        }

        String debut = FinLocation.get(FinLocation.size() - 1);

        String fin = Debutlocation.get(Debutlocation.size() - 1);

        int velos = Velo.get(Velo.size() - 1);
        Timestamp timestamp = Timestamp.valueOf(debut);
        Timestamp timestamp1 = Timestamp.valueOf(fin);

        System.out.println(timestamp.toString());

        long diff = timestamp1.getTime() - timestamp.getTime();

        res = TimeUnit.MILLISECONDS.toMinutes(diff);

        System.out.println(res + " minutes");

        numVelo = velos;
        System.out.println("numVelo=>" + numVelo);

        st.close();
        rts.close();

        /*Traitement du prix par modele*/
        Statement stt;
        stt = conn.createStatement();

        ResultSet rs;
        rs = stt.executeQuery("SELECT prix"
                + " FROM Modele natural join velo"
                + " WHERE numVelo = " + numVelo + "");

        while (rs.next()) {
            price = rs.getInt("PRIX");
            System.out.println("le prix est " + price + " € ");
        }
        rs.close();
        stt.close();

        /*Traitement du prime*/
        Statement stp;
        stp = conn.createStatement();
        ResultSet rsp;
        rsp = stp.executeQuery("SELECT prime"
                + " FROM ClientAbonne"
                + " WHERE numClientAbonne = " + IdClient + "");

        while (rsp.next()) {
            prime = rsp.getInt(1);
        }
        rsp.close();
        stp.close();

        //TRAITEMENT DE L'OPERATION
        int choix = LectureClavier.lireEntier("Utiliser prime OUI(1)-NON(2)");
        switch (choix) {
            case 1: //dans le cas ou il veut utiliser le prime
                System.out.println("prime == " + prime);
                System.out.println("resultat == " + res);
                System.out.println("prix == " + price);
                Statement stpp;
                stpp = conn.createStatement();
                int rspp = stp.executeUpdate("UPDATE ClientAbonne SET prime = 0"
                        + " FROM ClientAbonne"
                        + " WHERE numClientAbonne = " + IdClient + "");

                stpp.close();
                resultat = ((res - prime) * price) / 30; //30 = 1/2 heure
                break;
            case 2: //dans le cas ou il ne veut pas utilier le prime
                System.out.println("prime == " + prime);
                System.out.println("resultat == " + res);
                System.out.println("prix == " + price);
                resultat = (res * price) / 30;  //30 = 1/2 heure
                break;
            default:
                break;
        }

        return resultat;
    }

    public static void abonneAlert(Connection con, int idPerson, int idVelo) {

        try {
            System.out.println("idPerson"+idPerson);
            System.out.println("Idvelo"+idVelo);
            Statement stmt = con.createStatement();

            Timestamp CurrentTimestamp = new Timestamp(System.currentTimeMillis());

            ResultSet rs = stmt.executeQuery("SELECT * FROM locationabonne "
                    + " WHERE numclientabonne = " + idPerson + ""
                    + " and numvelo = " + idVelo + " "
                    + " and finlocation IS NULL");

            int res = 0;
            //DEBUTLOCATION
            String debutLocation = "";
            while (rs.next()) {
                debutLocation = rs.getString("DEBUTLOCATION");
            }

            Timestamp timeSampClientAbonne = Timestamp.valueOf(debutLocation);

            long diff = CurrentTimestamp.getTime() - timeSampClientAbonne.getTime();

            long resultatDiff = TimeUnit.MILLISECONDS.toMinutes(diff);

            if (resultatDiff <= 3) {

                ResultSet rsd = stmt.executeQuery("UPDATE locationabonne"
                        + " SET etatfacture = 'NonFacturee'"
                        + " WHERE numclientabonne = " + idPerson + ""
                        + " and numvelo = " + idVelo + " "
                        + " and finlocation IS NULL");
                System.out.println("Vous ne serez pas facturer");    
            }
            else {
                System.out.println("Vous ne serez pas facturer");    
            }
            
        } catch (Exception e) {
            System.out.println("Vélo déjà rendu");
        }

    }
    public static void nonAbonneAlert(Connection con,String codeSecret, int idVelo) {
        
        
        try {
            Statement stmt = con.createStatement();
        
            Timestamp CurrentTimestamp = new Timestamp(System.currentTimeMillis());

             ResultSet rs = stmt.executeQuery("SELECT * FROM locationnonabonne "
                    + " WHERE codesecret = '"+codeSecret+"'"
                    + " and numvelo = "+idVelo+" "
                            + " and finlocation IS NULL");

            int res = 0;
            //DEBUTLOCATION
            String debutLocation ="";
            while(rs.next()) {                     
                debutLocation = rs.getString("DEBUTLOCATION");  
                System.out.println("===> "+debutLocation);
            }   

            Timestamp timeSampClientAbonne = Timestamp.valueOf(debutLocation);    

            long diff = CurrentTimestamp.getTime() - timeSampClientAbonne.getTime();

            long resultatDiff = TimeUnit.MILLISECONDS.toMinutes(diff);        

            if(resultatDiff <= 3){

                ResultSet rsd = stmt.executeQuery("UPDATE locationnonabonne"
                        + " SET etatfacture = 'NonFacturee'" 
                        + " WHERE codesecret = '"+codeSecret+"'"
                        + " and numvelo = "+idVelo+" "
                            + " and finlocation IS NULL");
               System.out.println("Vous ne serez pas facturer");    
            } 
       
        }
        catch(Exception e) {
            System.out.println("Vous avez déjà rendu ce vélo");
        }
        
    }

}
