

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nabil
 */
import Enum.Sexe;
import Tables.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class Connexionsgbd {

    private static final String configurationFile = "BD.properties";

    public static void main(String args[]) {
        try {
            String jdbcDriver, dbUrl, username, password;
            DatabaseAccessProperties dap = new DatabaseAccessProperties(configurationFile);
            jdbcDriver
                    = dap.getJdbcDriver();
            dbUrl = dap.getDatabaseUrl();
            username = dap.getUsername();
            password = dap.getPassword();
// Load the database driver
            Class.forName(jdbcDriver);// Get a connection to the database
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("Connecté");
            // get informations 
            RequetesBd.getClient(conn);
            MenuChoix mn = new MenuChoix();
            mn.menu(conn);
            
            RenduVeloV2 r = new RenduVeloV2();
            
            
            
            
// Print information about connection warnings
            SQLWarningsExceptions.printWarnings(conn);
            conn.close();
        } catch (SQLException se) {
// Print information about SQL exceptions
            SQLWarningsExceptions.printExceptions(se);
            return;
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
            return;
        }
    }
}
