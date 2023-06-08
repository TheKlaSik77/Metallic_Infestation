package fr.iut.montreuil.metallic_infastation.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


import java.util.ArrayList;
import java.util.Random;

public class Environnement {

    final static int NOMBRE_VAGUES_POUR_ENNEMI_DIFFICILE = 3;
    final static int NOMBRE_ENNEMIS_DIFFICILES_SUPPLEMENTAIRES = 5;
    public static int vagueActuelle;
    private final Joueur joueur;
    private Terrain terrain;
    private ObservableList<Ennemi> listeEnnemis;
    private ObservableList<Tourelle> listeTourelles;
    private ObservableList<Projectile> listeProjectiles;
    private ParcoursBFS parcoursBFS;
    public int nbTours;
    private ObservableList<Laser> listeLasers;

    public Environnement(Terrain terrain) {
        this.terrain = terrain;
        this.listeEnnemis = FXCollections.observableArrayList();
        this.listeTourelles = FXCollections.observableArrayList();
        this.listeProjectiles = FXCollections.observableArrayList();
        this.listeLasers = FXCollections.observableArrayList();
        this.parcoursBFS = new ParcoursBFS(terrain);
        this.joueur = new Joueur(100,1000);
        vagueActuelle = 0;
        nbTours = 1;
    }

    /**
     * public unTour(){
     * if(nbTours%vitesse==0)
     * }
     */


    public ObservableList<Ennemi> getListeEnnemis() {
        return listeEnnemis;
    }

    public ObservableList<Tourelle> getListeTourelles() {
        return listeTourelles;
    }

    public ObservableList<Laser> getListeLasers(){
        return listeLasers;
    }

    public Ennemi ennemiSurCase(Case c) {
        for (Ennemi e : listeEnnemis) {
            if (e.getCase().caseEgale(c)) {
                return e;
            }
        }
        return null;
    }

    public void ajouterDansListeTours(Tourelle t) {
        listeTourelles.add(t);

    }


    public void lancerVague(Terrain terrain) {
        Random random = new Random();
        int nombreEnnemis = 10;


        if (vagueActuelle % NOMBRE_VAGUES_POUR_ENNEMI_DIFFICILE == 0) {
            nombreEnnemis += NOMBRE_ENNEMIS_DIFFICILES_SUPPLEMENTAIRES;
        }
        for (int i = 0; i < nombreEnnemis; i++) {
            int typeEnnemi = random.nextInt(3);

            switch (typeEnnemi) {
                case 0:
                    EnnemiFacile ennemiFacile = new EnnemiFacile(parcoursBFS, terrain);
                    listeEnnemis.add(ennemiFacile);
                    break;
                case 1:
                    EnnemiMoyen ennemiMoyen = new EnnemiMoyen(parcoursBFS, terrain);
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
        for (int i = this.getListeTourelles().size() - 1; i >= 0; i--) {
            if (this.getListeTourelles().get(i).getPosition().caseEgale(c)) {
                supprimee = this.getListeTourelles().get(i);
                this.getListeTourelles().remove(i);
            }
        }
        return supprimee;
    }

    public ObservableList<Projectile> getListeProjectiles() {
        return listeProjectiles;
    }

    public void ajouterProjectile(Projectile p) {
        listeProjectiles.add(p);
    }

    public Projectile retirerProjectile(Projectile p) {
        Projectile supprime = null;
        for (int i = this.getListeProjectiles().size() - 1; i >= 0; i--) {
            if (this.getListeProjectiles().get(i).equals(p)) {
                supprime = this.getListeProjectiles().get(i);
                this.getListeProjectiles().remove(i);
                break;
            }
        }
        return supprime;
    }
    public Ennemi retirerEnnemi(Ennemi e){
        Ennemi supprime = null;
        for (int i = this.listeEnnemis.size() - 1 ; i >= 0 ; i--){
            if (this.listeEnnemis.get(i).equals(e)) {
                supprime = this.listeEnnemis.get(i);
                this.listeEnnemis.remove(i);
            }
        }
        return supprime;
    }
    public void unTour(GestionnaireVagues gestionnaireVagues) {
        ArrayList<Ennemi> ennemisASupp = new ArrayList<>();

        if (this.nbTours % 2 == 0) {
            if (this.getListeEnnemis().isEmpty()) {
                gestionnaireVagues.lancerProchaineVague(terrain);
            }
            for (int idEnnemi = this.getListeEnnemis().size() - 1; idEnnemi >= 0; idEnnemi--) {
                Ennemi e = this.getListeEnnemis().get(idEnnemi);
                e.seDeplacer();
                listeLasers.clear();
                if (e.aAtteintLaCible()) {
                    ennemisASupp.add(e);
                    joueur.debiterPvJoueurProperty(e.getDrop());
                } else if (e.estMort()) {
                    ennemisASupp.add(e);
                    joueur.crediterArgentProperty(e.getDrop());
                }
            }
        }

        ArrayList<Projectile> listeProjectilesASupp = new ArrayList<>();
        if (this.nbTours % 2 == 0) {
            for (Projectile p : this.getListeProjectiles()) {
                p.seDeplacer();
                System.out.println(p.getEnnemiVise().getCoordonnees());
                if (p.arriveSurEnnemi()) {
                    p.getTourelle().infligerDegats();
                    listeProjectilesASupp.add(p);
                }
            }

        }
        if (this.nbTours % 50 == 0) {
            for (Tourelle t : this.getListeTourelles()) {
                if (t instanceof TourelleSemi) {
                    t.raffraichirEnnemiVise();
                    if (t.getEnnemiVise() != null) {
                        Projectile p = t.creerProjectile();
                        this.ajouterProjectile(p);
                    }
                }
            }
        }
        for (Tourelle t : this.getListeTourelles()) {
            if (t instanceof TourelleAuto) {
                t.raffraichirEnnemiVise();
                if (t.getEnnemiVise() != null) {
                    Laser l = ((TourelleAuto) t).creerLaser();
                    this.ajouterLaser(l);
                    t.infligerDegats();
                }
            } else {
                if (nbTours % 50 == 0)
                    t.infligerDegats();
            }

        }
        for (Laser l : listeLasers){
            if (l.getEnnemiVise() == null){
                listeLasers.clear();
            }
        }
        for (Projectile p : listeProjectilesASupp) {
            this.retirerProjectile(p);
        }
        for (Ennemi e : ennemisASupp){
            this.retirerEnnemi(e);
        }
        nbTours++;
    }

    public Joueur getJoueur() {
        return this.joueur;
    }

    public void ajouterLaser(Laser p){
        if (p != null) {
            if (p.getEnnemiVise() != null && p.getTourelle() != null) {
                listeLasers.add(p);
            }
        }
    }

    /**
     * Regarde si ennemi est déjà visé par un laser
     * @param e
     * @return
     */
    public boolean destEstPresent (Ennemi e){
        for (Laser l: listeLasers) {
            if (e == l.getEnnemiVise()){
                return true;
            }
        }
        return false;
    }

    public void retirerLaser(Laser l){
        for (int i = listeLasers.size()-1 ; i >= 0 ; i--){
            if (listeLasers.get(i).equals(l)){
                this.listeLasers.remove(listeLasers.get(i));
            }
        }
    }
}


