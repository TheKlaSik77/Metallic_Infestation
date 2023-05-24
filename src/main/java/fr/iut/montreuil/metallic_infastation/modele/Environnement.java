package fr.iut.montreuil.metallic_infastation.modele;

import java.util.ArrayList;

public class Environnement {

    private ArrayList<Ennemi> listeEnnemis;
    public Environnement(ArrayList<Ennemi> listeEnnemis){
        this.listeEnnemis = listeEnnemis;}
    public ArrayList<Ennemi> getListeEnnemis() {
        return listeEnnemis;
    }


}
