package fr.iut.montreuil.metallic_infastation.modele;

import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Random;

public class Environnement {

    public static int vagueActuelle;
    private Terrain terrain;
    private ObservableList<Ennemi> listeEnnemis;
    private ObservableList<Tourelle> listeTourelles;
    private ObservableList<Projectile> listeProjectiles;
    private ParcoursBFS parcoursBFS;



    public Environnement(Terrain terrain) {
        this.terrain = terrain;
        this.listeEnnemis = FXCollections.observableArrayList();
        this.listeTourelles = FXCollections.observableArrayList();
        this.listeProjectiles = FXCollections.observableArrayList();
        this.parcoursBFS = new ParcoursBFS(terrain);
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

    public void ajouterDansListeTours(Tourelle t){
            listeTourelles.add(t);

    }

    public void lancerVague(Terrain terrain) {
        Random random = new Random();
        int nombreEnnemis = 10;

        for (int i = 0; i < nombreEnnemis; i++) {
            int typeEnnemi = random.nextInt(3);

            switch (typeEnnemi) {
                case 0:
                    EnnemiFacile ennemiFacile = new EnnemiFacile(parcoursBFS,terrain);
                    listeEnnemis.add(ennemiFacile);
                    break;
                case 1:
                    EnnemiMoyen ennemiMoyen = new EnnemiMoyen(parcoursBFS,terrain);
                    listeEnnemis.add(ennemiMoyen);
                    break;
                case 2:
                    EnnemiDifficile ennemiDifficile = new EnnemiDifficile(parcoursBFS, terrain);
                    listeEnnemis.add(ennemiDifficile);
                    break;
            }
        }
    }
    public Tourelle retirerTour(Case c) {
        Tourelle supprimee = null;
        for (int i = this.getListeTourelles().size() - 1 ; i >= 0 ; i--){
            if (this.getListeTourelles().get(i).getPosition().caseEgale(c)){
                supprimee = this.getListeTourelles().get(i);
                this.getListeTourelles().remove(i);
            }
        }
        return supprimee;
    }

    public ObservableList<Projectile> getListeProjectiles(){
        return listeProjectiles;
    }

    public void ajouterProjectile(Projectile p){
        listeProjectiles.add(p);
    }

    public Projectile retirerProjectile(Projectile p){
        Projectile supprime = null;
        for (int i = this.getListeProjectiles().size() - 1 ; i >= 0 ; i--){
            if (this.getListeProjectiles().get(i).getCoordonnees().equals(p.getCoordonnees())){
                supprime = this.getListeProjectiles().get(i);
                this.getListeProjectiles().remove(i);
            }
        }
        return supprime;
    }
}


