package fr.iut.montreuil.metallic_infastation.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Environnement {



    static int vagueActuelle;
    private Terrain terrain;
    private ObservableList<Ennemi> listeEnnemis;
    private ObservableList<Tourelle> listeTourelles;

    public Environnement(Terrain terrain) {
        this.terrain = terrain;
        this.listeEnnemis = FXCollections.observableArrayList();
        this.listeTourelles = FXCollections.observableArrayList();
        vagueActuelle = 0;
    }

    public ObservableList<Ennemi> getListeEnnemis() {

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


    public void poserTour(Case c, Tourelle t){
            listeTourelles.add(t);
            this.terrain.setCase(c, 3);
    }


    /**
     *
     * @param terrain
     * Choisi un nombre aléatoire entre 10 et 20 ennemis
     * Génère un nombre entre 0 et 2 pour choisir le type d'ennemi
     * ajoute dans le terrain les ennemis correspondants
     */
    public void lancerVague (Terrain terrain){
        vagueActuelle++;
        System.out.println("vague n° " + vagueActuelle);
        Random random = new Random();
        int nombreEnnemis = random.nextInt(11) + 10;

        for (int i = 0; i < nombreEnnemis; i++) {
            int typeEnnemi = random.nextInt(3);

            switch (typeEnnemi) {
                case 0 -> this.getListeEnnemis().add(new EnnemiFacile(terrain));
                case 1 -> this.getListeEnnemis().add(new EnnemiMoyen(terrain));
                case 2 -> this.getListeEnnemis().add(new EnnemiDifficile(terrain));
            }
        }
    }
}

