
import Enum.Sexe;
import Tables.ClientAbonne;
import static Tables.FunctionUtil.abonneAlert;
import static Tables.FunctionUtil.nonAbonneAlert;
import static Tables.FunctionUtil.generatePassword;
import Tables.LectureClavier;
import Tables.Location;
import java.sql.Connection;
import java.sql.SQLException;
import Tables.RenduVeloV2;
import Tables.Station;
import static Tables.Station.*;
import Tables.StationsComponents;
import Tables.UpdatePlage;
import java.sql.Connection;
import java.text.ParseException;


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

    public void menu(Connection conn) throws SQLException, ParseException {

        boolean quit = false;
        boolean sortir = false;
        boolean sortir1 = false;
        boolean Déconnexion = false;

        int menuItem;

        do {
            System.out.println("1.Client Abonne 2.Client Non Abonne  3.S'abonne 4.Superviseur 5.ConsulterType 6.Quit");
            menuItem = LectureClavier.lireEntier("Choose menu item: ");

            switch (menuItem) {

                case 1:
                    System.out.println("Choix 1|Client Non Abonne");
                    System.out.println("~~~~~|identification|~~~~~~");
                    ClientAbonne c_abonne = new ClientAbonne();
                    System.out.print("Entrez votre nom: ");
                    String NomId = in.next();
                    System.out.print("Entrez votre codeSecret: ");
                    String CodeSecret = in.next();
                    int idClientLogin = c_abonne.loginClient(conn, NomId, CodeSecret);
                    if (idClientLogin == -1) {
                        System.out.println("~~~~~~~~~~~~~~ client non identifie~~~~~~~~~~~~~ ");
                        break;
                    } else {
                        System.out.println("~~~~~~~~ client identifie avec ID~~~~~~~~~~~~ " + idClientLogin);

                        do {
                            System.out.println("Entrez votre choix !");
                            System.out.println("1.Louer~~~~ 2.Rendre~~~ 3.Réserver~~~ 4.Alerter ~~~ 5.Déconnexion");
                            int Choix = LectureClavier.lireEntier("");
                            switch (Choix) {
                                case 1:
                                    System.out.println("Louer");
                                    Location.AjoutLocationAbonne(conn, idClientLogin);
                                    break;
                                case 2:
                                    System.out.println(" Rendre");
                                    RenduVeloV2.clientAbonne(conn,idClientLogin);
                                    break;
                                case 3:
                                    System.out.println("Réserver");
                                    System.out.println("Entrez l'id de station");
                                    int IdStation = getIDStation(conn);
                                    System.out.println("Entrez La date du debut 'yyyy-mm-dd HH:MI:SS'");
                                    String dateDebut = LectureClavier.lireChaine();
                                    System.out.println("Entrez La date de fin");
                                    String dateFin = LectureClavier.lireChaine();
                                    if (c_abonne.reserver(conn, idClientLogin, IdStation, dateDebut, dateFin) > 0) {
                                        System.out.println("Réservation Ajoutée");;
                                    } else {
                                        System.out.println("Réservation Non Ajoutée");;
                                    }

                                    break;
                                case 4:
                                    System.out.println("Alerter");
                                    int IdVelo = LectureClavier.lireEntier("Donnez L'ID du velo ");
                                    abonneAlert(conn,idClientLogin,IdVelo);
                                    break;
                                case 5:
                                    System.out.println("Déconnexion");
                                    Déconnexion = true;
                                    quit = true;
                                    break;
                                default:

                                    System.out.println("Choix invalid .");
                            }
                        } while (!Déconnexion);

                    }

                    break;

                case 2:
                    do {
                        System.out.println("1.Louer~~2.Rendre ~~ 3.Alerter ~~4.Sortir");
                        int Choix1 = LectureClavier.lireEntier("Entrez votre choix !");
                        switch (Choix1) {
                            case 1:
                                System.out.println("Louer");
                                Location.AjoutLocationNonAbonne(conn);

                                break;
                            case 2:
                                System.out.println(" Rendre");
                                RenduVeloV2.clientNonAbonne(conn);
                                break;
                            case 3:
                                System.out.println("Alerter");
                                int IdVelo = LectureClavier.lireEntier("Donnez L'ID du velo ");
                                System.out.println("Donnez Le code secret");
                                String CodeSecretAlerte = LectureClavier.lireChaine();
                                nonAbonneAlert(conn,CodeSecretAlerte,IdVelo);
                                break;
                            case 4:
                                sortir = true;
                                quit = true;
                                break;
                            default:
                                System.out.println("Choix invalid .");
                                break;

                        }
                    } while (!sortir);
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
                    Sexe s = (sexeIN == 'H') ? Sexe.H : Sexe.F;
                    System.out.println("Entrez votre adresse");
                    String adresse = in.next();
                    System.out.println("Entrez votre NumCB");
                    String NumCB = in.next();
                    ClientAbonne c = new ClientAbonne(nom, prenom, dateDeNaissance, s, adresse, NumCB);
                    c.Ajout_Client_Abonne(conn, c);
                    break;
                case 4:
                    System.out.println("Choix 4|Superviseur");
                    do {
                        System.out.println("1.Consulter~~2.Modifier ~~3.Sortir");
                        int Choix1 = LectureClavier.lireEntier("Entrez votre choix !");
                        switch (Choix1) {
                            case 1:
                                System.out.println("Consulter");
                                int IdStation = getIDStation(conn);
                                StationsComponents stc = new StationsComponents(conn, IdStation);
                                System.out.println(stc.toString());
                                ;
                                break;
                            case 2:
                                System.out.println(" Modifier");
                                UpdatePlage up = new UpdatePlage(conn);
                                up.showAllPlagesHorraires();
                                up.modifyPlageHorraire();
                                break;

                            case 3:
                                sortir1 = true;
                                quit = true;
                                break;
                            default:
                                System.out.println("Choix invalid .");
                                break;

                        }
                    } while (!sortir1);
                    break;
                case 5:

                    System.out.println("Consulter Type");
                    System.out.println("0-->Vnull , 1-->VMoins , 2-->Vplus");
                    int Type = LectureClavier.lireEntier("Entrez Votre Choix:");
                    Station.stationsDeType(conn, Type);
                    break;

                case 6:

                    quit = true;

                    break;

                default:

                    System.out.println("Choix invalid .");

            }

        } while (!quit);

        System.out.println("Bye-bye!");
    }

}
