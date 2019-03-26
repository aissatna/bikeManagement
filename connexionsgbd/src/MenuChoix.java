
import Enum.Sexe;
import Tables.ClientAbonne;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nabil
 */
import java.util.Scanner;

public class MenuChoix {

    public MenuChoix() {
    }

    public void menu(Connection conn) throws SQLException {
        Scanner in = new Scanner(System.in);

        // print menu
        System.out.println("1. Client Abonne ~~~~~~ 2.Client Non Abonne~~~~~~~ 3.S'abonne ~~~~~ 4. Quit" );
       // handle user commands
        boolean quit = false;

        int menuItem;

        do {

            System.out.print("Choose menu item: ");

            menuItem = in.nextInt();

            switch (menuItem) {

                case 1:

                    System.out.println("Choix 1~~~~~|~~~~~~ identification ");
                    ClientAbonne c_abonne = new ClientAbonne();
                    System.out.println("Entrez votre nom");
                    String nomid = in.next();
                    System.out.println("Entrez votre codeSecret");
                    String CodeSecret = in.next();
                    int id_client = c_abonne.loginClient(conn, nomid, CodeSecret);
                    if (id_client == -1) {System.out.println(" client non identifie ");break;}
                    else {System.out.println("client identifie avec l id "+id_client);
                    traitement_client_abonne(conn);
                    
                    }

                    break;

                case 2:

                    System.out.println("Choix 2|Client Non Abonne");

                    // do something...
                    break;

                case 3:

                    System.out.println("Choix 3 ! s'abonne");
                    System.out.println("Entrez votre nom");
                    String nom = in.next();
                    System.out.println("Entrez votre prenom");
                    String prenom = in.next();
                    System.out.println("Entrez votre dateDeNaissance! yyyy-MM-dd");
                    String dateDeNaissance = in.next();
                    //System.out.println("Entrez votre sexe");
                    //String sexeIN = in.nextLine();
                    //Sexe sexe = (sexeIN='H')? sexe.H:Sexe.F;
                    System.out.println("Entrez votre adresse");
                    String adresse = in.next();
                    System.out.println("Entrez votre NumCB");
                    String NumCB = in.next();
                    ClientAbonne c = new ClientAbonne(nom, prenom, dateDeNaissance, Sexe.H, adresse, NumCB);
                    c.Ajout_Client_Abonne(conn, c);
                    break;
                case 4:

                    quit = true;

                    break;

                default:

                    System.out.println("Invalid choice.");

            }

        } while (!quit);

        System.out.println("Bye-bye!");
}

   

        public void traitement_client_abonne(Connection conn) {
            
            
        }
    

}
