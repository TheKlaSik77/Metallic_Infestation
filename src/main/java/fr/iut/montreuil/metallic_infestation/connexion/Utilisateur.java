package fr.iut.montreuil.metallic_infestation.connexion;

import fr.iut.montreuil.metallic_infestation.connexion.Parametres.ParametresUtilisateur;

public class Utilisateur {

    ParametresUtilisateur parametresUtilisateur;

    public Utilisateur(ParametresUtilisateur parametresUtilisateur) {
        this.parametresUtilisateur = parametresUtilisateur;
    }

    public void chargerPartie(){}

    public void enregistrerPartie(){}

    public ParametresUtilisateur getParametresUtilisateur() {
        return parametresUtilisateur;
    }
}
