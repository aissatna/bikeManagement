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

/**
 *
 * @author jefferson
 */
public class RemiseVplus {
    public int numClient;
    
    
    public void AfficherVplus(Connection conn) throws SQLException{
        
        //test de numéro : 
        numClient = 21;
        Statement st;
        st = conn.createStatement();
        
        ResultSet rs;
        rs = st.executeQuery("SELECT prime"
                + " FROM ClientAbonne"
                + " WHERE numClientAbonne = "+ numClient +"");
        
        while (rs.next()){
            System.out.println("Vous disposez de " + rs.getInt(1) + " minutes "
                    + "pour votre prochain trajet");
        }
        rs.close();
        st.close();
    }
}
