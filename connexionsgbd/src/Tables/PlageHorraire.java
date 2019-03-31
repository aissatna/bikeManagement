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
public class PlageHorraire {
    private int numPlage;
    
    private String heureDebut;
    private String heureFin;
    private String type;
    private int numStation;

    private Connection con;
    
    public PlageHorraire(int i, Connection c){
        this.numPlage = i;
        this.con = c;
    }
    
    public int countPlages() throws SQLException{
        Statement stmt = con.createStatement();        
        int res = -1;
        ResultSet rs = stmt.executeQuery("select MAX(numplagehoraire) as max from plagehoraire");    
        
        while(rs.next()) {           
            res = Integer.parseInt(rs.getString("max"));
        }
        
        res++;
        return res;
    }
    
    public PlageHorraire(Connection c, String heureD, String heureF, String type, int numStation) throws SQLException{
        this.con = c;
        Statement stmt = con.createStatement();
        this.numPlage = countPlages();
        
        ResultSet rs = stmt.executeQuery("INSERT INTO plagehoraire" +
                                         " VALUES ("+this.numPlage+",TO_DATE('"+heureD+"', 'hh24:mi:ss'), "
                                                 + "TO_DATE('"+heureF+"', 'hh24:mi:ss'),  "
                                                         + "'"+type+"', "+numStation+" )");     
    }  
    
    public int getNumStation() throws SQLException {
        Statement stmt = con.createStatement();        
        int res = -1;
        ResultSet rs = stmt.executeQuery("SELECT numstation FROM plagehoraire "
                + " WHERE numplagehoraire = "+this.numPlage);    
        
        while(rs.next()) {           
            res = Integer.parseInt(rs.getString("numstation"));
        }
        
        return res;
    }

    public void setNumStation(int numStation) throws SQLException {
        Statement stmt = con.createStatement();   
        
        ResultSet rs = stmt.executeQuery("UPDATE plagehoraire "
                + " SET numstation = " + numStation
                + " WHERE numplagehoraire = "+this.numPlage);
    }    

    public String getHeureDebut() throws SQLException {
        Statement stmt = con.createStatement();        
        String res = "rien";
        ResultSet rs = stmt.executeQuery("SELECT heuredebut FROM plagehoraire "
                + " WHERE numplagehoraire = "+this.numPlage);        
             
        while(rs.next()) {           
            res = rs.getString("heuredebut");
        }
        
        return res;
    }

    public void setHeureDebut(String heureDebut) throws SQLException {
        Statement stmt = con.createStatement();   
        
        ResultSet rs = stmt.executeQuery("UPDATE plagehoraire"
                + " SET heuredebut = TO_DATE('" +heureDebut+"','hh24:mi:ss')"
                + " WHERE numplagehoraire = "+this.numPlage);
    }

    public String getHeureFin() throws SQLException {
        Statement stmt = con.createStatement();        
        String res = "rien";
        ResultSet rs = stmt.executeQuery("SELECT heurefin FROM plagehoraire "
                + " WHERE numplagehoraire = "+this.numPlage);
             
        while(rs.next()) {           
            res = rs.getString("heurefin");
        }
        
        return res;
    }

    public void setHeureFin(String heureFin) throws SQLException {
        Statement stmt = con.createStatement();   
        
        ResultSet rs = stmt.executeQuery("UPDATE plagehoraire"
                + " SET heurefin = TO_DATE('" +heureFin+"','hh24:mi:ss')"
                + " WHERE numplagehoraire = "+this.numPlage);
    }

    public String getType() throws SQLException {
        Statement stmt = con.createStatement();        
        String res = "rien";
        ResultSet rs = stmt.executeQuery("SELECT type FROM plagehoraire "
                + " WHERE numplagehoraire = "+this.numPlage);
        
        while(rs.next()) {           
            res = rs.getString("type");
        }
        
        return res;
    }

    public void setType(String type) throws SQLException {
        Statement stmt = con.createStatement();   
        
        ResultSet rs = stmt.executeQuery("UPDATE plagehoraire"
                + " SET type = '" +type 
                + "' WHERE numplagehoraire = "+this.numPlage);
    } 
    
    @Override
    public String toString(){
        try {
            String res = "";
            
            res = "[numPlageHorraire] : "+this.numPlage+""
                    + " [heureDebut] : "+this.getHeureDebut()+""
                    + " [heurFin] : "+this.getHeureFin()+""
                    + " [type] : "+this.getType()+""
                    + " [numStation] : "+this.getNumStation();
            return res;
        } catch (SQLException ex) {
            Logger.getLogger(PlageHorraire.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
