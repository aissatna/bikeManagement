/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codegenerator;


/**
 *
 * @author jefferson
 */
public class Initiale {

    public String nom = "JEFFERSON";
    public String prenom = "cessna";
    public String dateNaissance = "21-MAY-1996";
    public String Sexe = "H";
    public int numero = 001;

    public String takeName() {
        String res;
        res = nom.substring(0, 2).toUpperCase();
        return res;
    }

    public String takePrenom() {
        String res;
        res = prenom.substring(0, 2).toLowerCase();
        return res;
    }

    public String takeDOB() {
        String res;
        res = dateNaissance.substring(0, 2);
        return res;
    }

    public String takeSexe() {
        String res;
        res = Sexe;
        return res;
    }

    public String takeNum() {
        String res;
        res = Integer.toString(numero);
        return res;
    }
    
     public String resultatFinal(){
        String resultat;
        resultat = "" + takeDOB() + takeName() + takeNum()+ takePrenom() + takeSexe();
        return resultat;
    }
    
}
