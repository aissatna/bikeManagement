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
import java.util.Scanner;

/**
 *
 * @author nabil
 */
public class UpdatePlage {
    private static Connection con;
    public UpdatePlage (Connection conn){
        this.con=conn;
    }
       public  String readClavier(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Saisir :");
        String str = sc.nextLine();
        return str;                                                                                                             
    }
    
    public  boolean existPlageHorraire(int numPlage) throws SQLException{
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT numplagehoraire FROM plagehoraire "
                + " where numplagehoraire = "+numPlage);
        
        int res = 0;
        
        while(rs.next()) {         
            res = Integer.parseInt(rs.getString("numplagehoraire"));
        }   
              
        
        return (res == numPlage);
    }
    public  void modifierPlageHorraire(PlageHorraire instancePlageHorraire, boolean debut) throws SQLException{
        String date = "";
        
        
        if(debut){ // on modifie plage horraire début
            System.out.println("+--- modification de date début ---+");
            System.out.println("+--- Format Heure:Minute:Seconde ---+");
            date = readClavier();
            instancePlageHorraire.setHeureDebut(date);
            System.out.println("Heure de début modifiée");
        }else { // on modifie plage horraire fin
            System.out.println("+--- modification de date fin ---+");
            System.out.println("+--- Format Heure:Minute:Seconde ---+");
            date = readClavier();
            instancePlageHorraire.setHeureFin(date);
            System.out.println("Heure de fin modifiée");

        }
    }
    
    public  boolean stationExist(int idStation) throws SQLException{
        
        Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT numstation FROM station "
                + " where numstation = "+idStation);
        
        int res = 0;
        
        while(rs.next()) {         
            res = Integer.parseInt(rs.getString("numstation"));
        }   
                      
        return (res == idStation);
    }
    
    public  String traitementSaisit(String option1, String option2){
        
        boolean sortie = false;
        String res = "";
        
        while(!sortie){         
            
            res = readClavier();
            
            if(res.equals(option1)){
                res = option1;
                sortie = true;
            } else if (res.equals(option2)){
                res = option2;
                sortie = true;
            } else {
                sortie = false;
            }
            
        }
        
        return res;
    }
    public  void modifierNumStation(PlageHorraire plageHorraire) throws SQLException{
        
        boolean sortie = false;        
        int idStation = 0;
        while(!sortie){
            idStation = Integer.parseInt(readClavier());
            
            if(stationExist(idStation)){
                plageHorraire.setNumStation(idStation);
                sortie = true;
            }else{
               sortie = false; 
            }
        }
        
        System.out.println("Le numero de station est modifiée");
    }
    public  void modifierTypePlageHorraire(PlageHorraire plageHorraire) throws SQLException{
        boolean sortie = false;
        String res = "";
        while(!sortie){
            switch(res = readClavier()){
                case "Vmoins": 
                    sortie = true;
                    break;
                case "Vplus" : 
                    sortie = true;
                    break;
                case "Vnull" : 
                    sortie = true;
                    break;
                default : 
                    sortie = false;
            }
        }
        
        plageHorraire.setType(res);
        
        System.out.println("Modification du type terminée");
    }
    
    public  void modifyPlageHorraire() throws SQLException{
        int numPlageHorraire = 0;
        
        System.out.println("Selectionner le numero d'une plage horraire : ");
        numPlageHorraire = Integer.parseInt(readClavier());
        if(existPlageHorraire(numPlageHorraire)){    
            PlageHorraire p = new PlageHorraire(numPlageHorraire, con); // création de l'instance
            
            System.out.println("Voulez vous changer l'heure de début de cette plage horraire ? [o/n]");
            
            if(traitementSaisit("o", "n") == "o"){
                modifierPlageHorraire(p, true);
            }
            
            System.out.println("Voulez vous changer l'heure de fin de cette plage horraire ? [o/n]");
            
            if(traitementSaisit("o", "n") == "o"){
                modifierPlageHorraire(p, false);
            }
            
            
            System.out.println("Voulez vous changer le type de cette plage horraire ? [o/n]");
            
            if(traitementSaisit("o", "n") == "o"){
                modifierTypePlageHorraire(p);
            }
            
            System.out.println("Voulez vous changer le numStation ? [o/n]");
            
            if(traitementSaisit("o", "n") == "o"){
                modifierNumStation(p);
            }
            
            
        } else {
            System.out.println("La plage horraire selectionnée n'existe pas !");
        }
        
    }
   
     public void showAllPlagesHorraires() throws SQLException{
        
        String[][] tableauTemporaire = new String[99][4]; 
           
        Statement stmt = con.createStatement();
        
        ResultSet rs = stmt.executeQuery("SELECT numplagehoraire FROM plagehoraire");
        
        int res = 0;
        
        while(rs.next()) {      
            res = Integer.parseInt(rs.getString("numplagehoraire"));
            PlageHorraire t = new PlageHorraire(res, con);
            System.out.println(t);
        }      
     
    }
 
    
}
