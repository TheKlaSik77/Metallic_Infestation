package fr.iut.montreuil.metallic_infastation.modele;

import java.util.ArrayList;

public class Environnement {

    private int pvJoueur;
    private int vagueActuelle;
    private int argent;


    private ArrayList<Ennemi> listeEnnemis;

    public Environnement(int pvJoueur, int vagueActuelle, int argent, ArrayList<Ennemi> listeEnnemis){
        this.pvJoueur = pvJoueur;
        this.vagueActuelle = vagueActuelle;
        this.argent = argent;
        this.listeEnnemis = listeEnnemis;
    }
    public ArrayList<Ennemi> getListeEnnemis() {
        return listeEnnemis;
    }


}
