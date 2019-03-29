package TestesMethodes;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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
        
        LocalDate today = LocalDate.now();
        System.out.println(today); //2016/11/16
        System.out.println(today.plusDays(365));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse("2011-02-10");
        java.sql.Date sql = new java.sql.Date(parsed.getTime());
       Date dateDeNaissance=(Date) sql;
        System.out.println(dateDeNaissance);

        /*Calendar c = Calendar.getInstance();
        System.out.println(c.);
        c.add(Calendar.YEAR, 2);
        Date newDate = c.getTime();*/
 /* DateFormat dateformat = new SimpleDateFormat("yyyy-MMM-dd");
        Calendar cal = Calendar.getInstance();

        System.out.println("nouveau date => " + dateformat.format(cal.getTime()));

        cal.add(Calendar.DAY_OF_MONTH, 365);
        Date date = dateformat.format(cal.getTime()));*/
    }

}
