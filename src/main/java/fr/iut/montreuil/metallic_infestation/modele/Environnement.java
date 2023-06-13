package fr.iut.montreuil.metallic_infestation.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Environnement {

    final static int NOMBRE_VAGUES_POUR_ENNEMI_DIFFICILE = 3;
    final static int NOMBRE_ENNEMIS_DIFFICILES_SUPPLEMENTAIRES = 5;
    public static IntegerProperty vagueActuelleProperty;
    private final Joueur joueur;
    private Terrain terrain;
    private ObservableList<Ennemi> listeEnnemis;
    private ObservableList<Tourelle> listeTourelles;
    private ObservableList<Projectile> listeProjectiles;

    private ObservableList<Explosion> listExplosions;
    private ArrayList<Ennemi> ennemisASpawn;

    private ParcoursBFS parcoursBFS;
    public int nbTours;
    private ObservableList<Laser> listeLasers;

    public Environnement(Terrain terrain) {
        this.terrain = terrain;
        this.listeEnnemis = FXCollections.observableArrayList();
        this.listeTourelles = FXCollections.observableArrayList();
        this.listeProjectiles = FXCollections.observableArrayList();
        this.listeLasers = FXCollections.observableArrayList();
        this.listExplosions = FXCollections.observableArrayList();
        this.ennemisASpawn =  new ArrayList<>();
        this.parcoursBFS = new ParcoursBFS(terrain);
        this.joueur = new Joueur(100,1000);
        vagueActuelleProperty = new SimpleIntegerProperty(0);
        nbTours = 1;
    }

    /**
     * public unTour(){
     * if(nbTours%vitesse==0)
     * }
     */
    public ParcoursBFS getParcoursBFS(){
        return parcoursBFS;
    }

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

    public ObservableList<Explosion> getListExplosions(){return listExplosions;}

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
        if (this.joueur.pvJoueurProprerty().get() <= 0){
            // TODO: Ecran de loose

        }
        if (this.nbTours % 500 == 0 || nbTours == 1) {
            ennemisASpawn = gestionnaireVagues.lancerProchaineVague(terrain);
            System.out.println(ennemisASpawn);
            System.out.println(ennemisASpawn.size());
        }
        if (this.nbTours % 20 == 0 && !ennemisASpawn.isEmpty()) {
            this.getListeEnnemis().add(ennemisASpawn.remove(ennemisASpawn.size() - 1));
            System.out.println(ennemisASpawn.size());
        }

        if (this.nbTours % 2 == 0) {

            for (int idEnnemi = this.getListeEnnemis().size() - 1; idEnnemi >= 0; idEnnemi--) {
                Ennemi e = this.getListeEnnemis().get(idEnnemi);
                e.seDeplacer();
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
                if (p.arriveSurEnnemi()) {
                    if (p instanceof ProjectileMissile){
                        listExplosions.add(((ProjectileMissile) p).creerExplosion());
                    }
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

        if (this.nbTours % 100 == 0){
            for (Tourelle t: this.getListeTourelles()){
                if (t instanceof TourelleMissiles) {
                    t.raffraichirEnnemiVise();
                    if (t.getEnnemiVise() != null) {
                        Projectile p = t.creerProjectileMissile();
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
        if(nbTours % 2 == 0) {
            listeLasers.clear();
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

    public void retirerExplosion(Explosion e){
        for (int i = listExplosions.size()-1 ; i >= 0 ; i--){
            if (listExplosions.get(i).equals(e)){
                this.listExplosions.remove(listExplosions.get(i));
            }
        }
    }




    public IntegerProperty vagueActuelleProperty(){
        return this.vagueActuelleProperty;
    }

    public static void incrementerVagueActuelleProperty(){
        vagueActuelleProperty.set(vagueActuelleProperty.get()+1);
    }
}


