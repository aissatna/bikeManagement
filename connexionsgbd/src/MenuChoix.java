
import Enum.Sexe;
import Tables.ClientAbonne;
import Tables.Station;
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

    Scanner in = new Scanner(System.in);

    public MenuChoix() {
    }

    public void menu(Connection conn) throws SQLException {

        boolean quit = false;
        boolean Déconnexion = false;

        int menuItem;

        do {
           System.out.println("1. Client Abonne ~~~~~~ 2.Client Non Abonne~~~~~~~ 3.S'abonne ~~~~~ 4. Quit");
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
                    int idClientLogin = c_abonne.loginClient(conn, nomid, CodeSecret);
                    if (idClientLogin == -1) {
                        System.out.println("~~~~~~~~~~~~~~ client non identifie~~~~~~~~~~~~~ ");
                        break;
                    } else {
                        System.out.println("~~~~~~~~ client identifie avec l id~~~~~~~~~~~~ " + idClientLogin);

                        do {
                            System.out.println("Entrez votre choix !");
                            System.out.println("1.Louer  ~~~~  2.Rendre ~~~~~ 3. Réserver ~~~~~ 4.Déconnexion");
                            int Choix = in.nextInt();
                            switch (Choix) {
                                case 1:
                                    System.out.println("Louer");
                                    Station st = new Station();
                                    st.getStationListe(conn);
                                    break;
                                case 2:
                                    System.out.println(" Rendre");
                                    break;
                                case 3:
                                    System.out.println("Réserver");
                                    break;
                                case 4:
                                    System.out.println("Déconnexion");
                                    Déconnexion = true;
                                    quit =true;
                                    break;
                                default:

                                    System.out.println("Choix invalid .");
                            }
                        } while (!Déconnexion);

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
                    System.out.println("Entrez votre sexe :H/F");
                    char sexeIN = in.next().charAt(0);
                    Sexe s=(sexeIN=='H')? Sexe.H:Sexe.F;
                    System.out.println("Entrez votre adresse");
                    String adresse = in.next();
                    System.out.println("Entrez votre NumCB");
                    String NumCB = in.next();
                    ClientAbonne c = new ClientAbonne(nom, prenom, dateDeNaissance, s, adresse, NumCB);
                    c.Ajout_Client_Abonne(conn, c);
                    break;
                case 4:

                    quit = true;

                    break;

                default:

                    System.out.println("Choix invalid .");

            }

        } while (!quit);

        System.out.println("Bye-bye!");
    }

 }
