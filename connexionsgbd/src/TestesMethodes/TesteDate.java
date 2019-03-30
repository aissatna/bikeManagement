package TestesMethodes;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nabil
 */
public class TesteDate {

    public static void main(String args[]) throws ParseException {

        String dateStart = "28-03-2019 10:31:58";
        String dateStop = "30-03-2019 10:31:48";

        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            double diffSeconds = diff / 1000 % 60;
            double diffMinutes = diff / (60 * 1000) % 60;
            double diffHours = diff / (60 * 60 * 1000) % 24;
            double diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.println(diffDays + " days, ");
            System.out.println(diffHours + " hours, ");
            System.out.println(diffMinutes + " minutes, ");
            System.out.println(diffSeconds + " seconds.\n");

            double resultDec = (((diffDays * 24) + diffHours + (diffMinutes / 60)) + (diffSeconds / 3600));

            DecimalFormat df = new DecimalFormat("#.##");
            System.out.println("valeur de l'abonnement arrondi ===>" + df.format(resultDec));

            System.out.println("dif 1 -> " + resultDec + "  Minutes ");
            System.out.println("dif 2 -> " + d2.getTime() / 60000);

            System.out.println("traitement jours en heure: " + ((diff / (60000l)) * 60));
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
