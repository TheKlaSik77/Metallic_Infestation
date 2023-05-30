package fr.iut.montreuil.metallic_infastation.modele;

import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Random;

public class Environnement {

    final static int NOMBRE_VAGUES_POUR_ENNEMI_DIFFICILE = 3;
    final static int NOMBRE_ENNEMIS_DIFFICILES_SUPPLEMENTAIRES = 2;
    public static int vagueActuelle;
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

    public ObservableList<Tourelle> getListeTourelles() {
        return listeTourelles;
    }

    public Ennemi ennemiSurCase(Case c) {
        for (Ennemi e : listeEnnemis) {
            if (e.getCase().caseEgale(c)) {
                return e;
            }
        }
        return null;
    }


    public void lancerVague(Terrain terrain) {
        Random random = new Random();
        int nombreEnnemis = 10;

        for (int i = 0; i < nombreEnnemis; i++) {
            int typeEnnemi = random.nextInt(3);
            System.out.println("Type d'ennemi : " + typeEnnemi);

            switch (typeEnnemi) {
                case 0:
                    EnnemiFacile ennemiFacile = new EnnemiFacile(terrain);
                    listeEnnemis.add(ennemiFacile);
                    break;
                case 1:
                    EnnemiMoyen ennemiMoyen = new EnnemiMoyen(terrain);
                    listeEnnemis.add(ennemiMoyen);
                    break;
                case 2:
                    EnnemiDifficile ennemiDifficile = new EnnemiDifficile(terrain);
                    listeEnnemis.add(ennemiDifficile);
                    break;
            }
        }
    }

        public boolean listeEnnemiEstVide() {
        return listeEnnemis.isEmpty();
    }


}
