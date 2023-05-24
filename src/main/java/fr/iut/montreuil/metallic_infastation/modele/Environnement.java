package fr.iut.montreuil.metallic_infastation.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Environnement {

    private int vagueActuelle;
    private ObservableList<Tourelle> listeTourelles;
    private ArrayList<Ennemi> listeEnnemis;
    private Terrain terrain;

    public Environnement(int vagueActuelle, Terrain terrain, ArrayList<Ennemi> listeEnnemis){
        this.vagueActuelle = vagueActuelle;
        this.terrain = terrain;
        this.listeTourelles = FXCollections.observableArrayList();
        this.listeEnnemis = listeEnnemis;
    }
    public ArrayList<Ennemi> getListeEnnemis() {
        return listeEnnemis;
    }
    public ObservableList<Tourelle> getListeTourelles(){
        return listeTourelles;
    }

    public Ennemi ennemiSurCase(Case c){
        for (Ennemi e : listeEnnemis){
            if (e.getCase().caseEgale(c)){
                return e;
            }
        }
        return null;
    }


    public void poserTour(Case c){
        if (this.terrain.videSurCase(c)){
            Tourelle t = new TourelleSemi(c,this,terrain);
            listeTourelles.add(t);
            this.terrain.setCase(c,3);
        }
    }



}
