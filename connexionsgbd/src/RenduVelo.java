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
 * @author jefferson
 */
public class RenduVelo {

    public RenduVelo() {

    }

    public void listeStationLibre(Connection conn) throws SQLException {
        // create new statement
        Statement st;
        st = conn.createStatement();
        ResultSet rs = st.executeQuery("select distinct numstation, adresse from bornette b natural join station s where disponibilite = 'Libre'");
        while (rs.next()) {
            System.out.println("le numero de station libre est le : " + rs.getInt(1)
                    + " | d'adresse : " + rs.getString(2));
        }

        /*
             Lecture clavier du coté client pour choisir la station:
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer le numero de station =>  ");
        String entree = sc.nextLine();

        Statement st1;
        st1 = conn.createStatement();
        ResultSet rs1 = st1.executeQuery("SELECT numBornette"
                + " FROM bornette"
                + " WHERE numStation = + '" + entree + "'"
                + " AND disponibilite = 'Libre'");
        while (rs1.next()) {
            System.out.println("les bornettes libres : " + rs1.getInt(1));
        }

        rs.close();
        rs1.close();

        st.close();
        st1.close();

    }

    public void choisirBornette(Connection conn) throws SQLException {
        /*
             Le client choisi une bornette libre
         */

        Scanner sc = new Scanner(System.in);
        //traitement de la bornette
        System.out.println("Veuillez choisir un numero de bornette libre = ");
        String numBornette = sc.nextLine();
        //traitement du numéro de velo
        System.out.println("Etrer le numero de velo que vous allez rendre s'il vous plait ==> ");
        String numVelo = sc.nextLine();

        System.out.println("---------------------------------------------");
        System.out.println("appuier sur 1 si vous etes abonnee, sinon 2 : ");
        String choice = sc.nextLine();

        switch (choice) {
            case "1":
                //cas de client abonnée
                System.out.println("-----------------------CLIENT ABONNE----------------------");
                System.out.println("---------------------------------------------");

                break;
            case "2":
                //cas de client non abonnée
                System.out.println("-----------------------CLIENT NON ABONNE----------------------");
                System.out.println("Veuillez renseigner votre code secret : ");
                String codeSecret = sc.nextLine();
                System.out.println("---------------------------------------------");

                //creation de statement pour Client non abonnée
                Statement stCode;
                stCode = conn.createStatement();

                //authentification du code secret
                ResultSet rsCode = stCode.executeQuery("SELECT *"
                        + "FROM LocationNonAbonne");

                while (rsCode.next()) {
                    //dans le cas ou le code n'existe pas
                    if ((rsCode.getString(8)).equals(codeSecret)) {
                        //creation de statement pour Client non abonnée
                        Statement stt;
                        stt = conn.createStatement();
                        //test code secret : DFx548 MP8d45
                        ResultSet rss;
                        rss = stt.executeQuery("SELECT *"
                                + " FROM LocationNonAbonne"
                                + " WHERE codeSecret = '" + codeSecret + "'");

                        while (rss.next()) {
                            System.out.println("details abonnement :" + rss.getInt(1)
                                    + " " + rss.getInt(2)
                                    + " " + rss.getString(3));

                            //test de num velo
                            if (rss.getInt(2) != (Integer.parseInt(numVelo))) {
                                System.out.println("votre numero de velo ne correspond pas au numero de velo qu'on a enregistree");
                            } //danns le cas ou c'est vraie
                            else {
                                System.out.println("Réussi : des problèmes au niveau du vélo ? O ou N");
                                String remarque = sc.nextLine();
                                switch (remarque) {
                                    case ("O"):
                                        System.out.println("nous vous remercions de votre coopération.");
                                        /*REQUETE UPDATE DE L'ETAT DU VELO*/
                                        Statement stVelo;
                                        stVelo = conn.createStatement();
                                        int rsVelo = stVelo.executeUpdate("UPDATE Velo SET EtatVelo = 'HorsService' WHERE numVelo ='" + numVelo + "'");
                                        break;
                                    case "N":
                                        System.out.println("Vpick vous remercie :) au revoir!");
                                        break;
                                    default:
                                        System.out.println("ERREUR de choix réessayer");
                                        break;
                                }
                            }
                        }
                        //fermeture
                        rss.close();
                        stt.close();
                        break;
                    } //dans le cas ou le code secret existe
                    else {
                        System.out.println("*!* CODE SECRET INTROUVABLE *!*");
                        break;
                    }
                }
                rsCode.close();
                stCode.close();
                break;
            default:
                System.out.println("erreur de saisie");
                break;
        }

    }

}
