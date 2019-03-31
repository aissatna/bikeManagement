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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emre
 */
public class StationsComponents {
    private static Connection con;
    private int numStation = 0;
    
    public StationsComponents(Connection c, int numStation) throws SQLException{
        this.con = c;
        this.numStation = numStation;
    }
    
    
    /**
     * Cette fonction permet de récupérer le nombre de vélos par station
     * 
     * 
     * Resultat : 
     *  Premier indice : id de la station
     *  Deuxieme indice : nombre de vélos 
     * @throws SQLException 
     */
    public int countBikes() throws SQLException{
        
               
        Statement stmt = con.createStatement();
        
        ResultSet rs = stmt.executeQuery("SELECT count(*) as nb FROM station "
                + " NATURAL JOIN bornette"
                + " NATURAL JOIN VELO"
                + " WHERE VELO.disponibilitevelo = 'Disponible' and EtatVelo ='EnService' "
                + " and numstation = "+this.numStation);
        
        int nbRes = 0;
             
        while(rs.next()) {   
            nbRes = Integer.parseInt(rs.getString("nb"));
        }
        
        return nbRes;
    }
    
    /**
     * Cette fonction permet de récupérer le nombre de place libre par station
     * 
     * 
     * Resultat : 
     *  Premier indice : id de la station
     *  Deuxieme indice : nombre de place libre
     * @throws SQLException 
     */
    public int countFreePlaces() throws SQLException{
           
        Statement stmt = con.createStatement();
        
        ResultSet rs = stmt.executeQuery("SELECT count(*) as nb FROM station "
                + " NATURAL JOIN bornette"
                + " WHERE bornette.disponibilite = 'Libre' "
                + " and numstation = "+this.numStation);
        
        int nbRes = 0;
             
        while(rs.next()) {           
            nbRes = Integer.parseInt(rs.getString("nb"));
        }
               
       
        return nbRes;
    }
    /**
     * Cette fonction permet de récupérer le nombre de vélos endommagés par station
     * 
     * 
     * Resultat : 
     *  Premier indice : id de la station
     *  Deuxieme indice : nombre de vélos endommagés
     * @throws SQLException 
     */
    public int countDamagedBikes() throws SQLException{
        
        String[][] tableauTemporaire = new String[99][4]; 
           
        Statement stmt = con.createStatement();
        
        ResultSet rs = stmt.executeQuery("SELECT count(*) as nb FROM station "
                + " NATURAL JOIN bornette"
                + " NATURAL JOIN VELO"
                + " WHERE VELO.etatvelo = 'HorsService' "
                + " and numstation = "+this.numStation);
        
        int nbRes = 0;
             
        while(rs.next()) {           
            nbRes = Integer.parseInt(rs.getString("nb"));
        }
               
       
        return nbRes;
    }
    
    @Override
    public String toString() {
        try {
            String res = "";
            
            res = "N°station " + this.numStation + " => "
                    + "Nombre de vélos : "+this.countBikes()+" | "
                    + " Nombre de vélos endommagés : "+this.countDamagedBikes()+" | "
                    + " Nombre de places libres : "+this.countFreePlaces();
            
            return res;
        } catch (SQLException ex) {
            Logger.getLogger(StationsComponents.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
