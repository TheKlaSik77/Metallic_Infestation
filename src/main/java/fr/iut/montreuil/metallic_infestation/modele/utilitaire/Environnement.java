package fr.iut.montreuil.metallic_infestation.modele.utilitaire;


import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Mine;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Obstacle;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Pics;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.*;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.utilitaire.DistanceEnnemiCible;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;


public class Environnement {

    private static Environnement uniqueInstance = null;

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
    private GestionnaireVagues gestionnaireVagues;
    private ObservableList<Laser> listeLasers;
    private ObservableList<Obstacle> listeObstacles;


    private Environnement() {
        this.terrain = Terrain.getInstance();
        this.listeEnnemis = FXCollections.observableArrayList();
        this.listeTourelles = FXCollections.observableArrayList();
        this.listeProjectiles = FXCollections.observableArrayList();
        this.listeLasers = FXCollections.observableArrayList();
        this.listExplosions = FXCollections.observableArrayList();
        this.listeObstacles = FXCollections.observableArrayList();
        this.ennemisASpawn =  new ArrayList<>();
        this.parcoursBFS = new ParcoursBFS(terrain);

        parcoursBFS.remplirGrilleBFS();
        this.joueur = Joueur.getInstance(100,1000);
        vagueActuelleProperty = new SimpleIntegerProperty(0);
        this.gestionnaireVagues = new GestionnaireVagues(this);
        nbTours = 1;
    }
    public static Environnement getInstance(Terrain terrain){
        if (uniqueInstance==null){
            uniqueInstance = new Environnement();
        }
        return uniqueInstance;
    }

    public GestionnaireVagues getGestionnaireVagues(){
        return gestionnaireVagues;
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
            if (e.getCase().equals(c)) {
                return e;
            }
        }
        return null;
    }

    public void ajouterDansListeTours(Tourelle t) {
        listeTourelles.add(t);

    }
    public void ajouterDansListeObstacles(Obstacle o) {
        listeObstacles.add(o);
    }

    public Tourelle retirerTour(Case c) {
        Tourelle supprimee = null;
        for (int i = this.getListeTourelles().size() - 1; i >= 0; i--) {
            if (this.getListeTourelles().get(i).getPosition().equals(c)) {
                supprimee = this.getListeTourelles().get(i);
                this.getListeTourelles().remove(i);
            }
        }
        return supprimee;
    }

    public Obstacle retirerObstacle(Case c) {
        Obstacle supprimee = null;
        for (int i = this.getListeObstacles().size() - 1; i >= 0; i--) {
            if (this.getListeObstacles().get(i).getPosition().equals(c)) {
                supprimee = this.getListeObstacles().get(i);
                this.getListeObstacles().remove(i);
            }
        }
        return supprimee;
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

    public ObservableList<Projectile> getListeProjectiles() {
        return listeProjectiles;
    }

    public ObservableList<Explosion> getListExplosions(){return listExplosions;}

    public void ajouterProjectile(Projectile p) {
        listeProjectiles.add(p);
    }


    public void unTour(GestionnaireVagues gestionnaireVagues) {

        ArrayList<Ennemi> ennemisASupp = new ArrayList<>();
        if (this.joueur.pvJoueurProprerty().get() <= 0){


        }
        if (this.nbTours % 700 == 0 || nbTours == 100) {
            ennemisASpawn = gestionnaireVagues.lancerProchaineVague(terrain);

        }
        if (this.nbTours % 20 == 0 && !ennemisASpawn.isEmpty()) {
            this.getListeEnnemis().add(ennemisASpawn.remove(ennemisASpawn.size() - 1));
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
        //TODO: Retirer les instanceof
        /*
        inverser la ligne nbTours % et for en dessous
        this.nbTours % t.getVitesseAttaque()
        remplacer instanceof par t.tirer (les dégats seront gérés dans une autre boucle : parcourir les projectiles et si projectile.getPosition() == projectile.getEnnemiVise => infligerDégats() 
         */
        // TODO : MODIFIER (TOURS)
        if (this.nbTours % 20 == 0) {
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
        if (!listeObstacles.isEmpty()) {
            for (int i = listeObstacles.size() - 1; i >= 0; i--) {
                for (Ennemi e : listeEnnemis) {
                    if (listeObstacles.get(i).ennemisSurObstacle()) {
                        if (listeObstacles.get(i) instanceof Pics) {
                            if (listeObstacles.get(i).ennemisSurObstacle()) {
                                ((Pics) listeObstacles.get(i)).actionnerPics(e);
                            }
                        } else if (listeObstacles.get(i) instanceof Mine) {
                            terrain.setCase(listeObstacles.get(i).getPosition(), 1);
                            Explosion explosion = new Explosion(this,listeObstacles.get(i).getPosition().getCentreCase(), ((Mine) listeObstacles.get(i)).getDegats(),((Mine) listeObstacles.get(i)).getPorteeExplosion());
                            listExplosions.add(explosion);
                            explosion.infligerDegats();
                            this.listeObstacles.remove(listeObstacles.get(i));
                            break;
                        }
                    }

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
        for (Ennemi e : listeEnnemis){
            if (e.estSurChemin()){
                e.retablirVitesse();
            }
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
    public boolean estEstPresent (Ennemi e){
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


    public ObservableList<Obstacle> getListeObstacles() {
        return this.listeObstacles;

    }

    public IntegerProperty vagueActuelleProperty(){
        return this.vagueActuelleProperty;
    }
    public void setVagueActuelleProperty(int n ){
        this.vagueActuelleProperty().setValue(n);
    }

    public static void incrementerVagueActuelleProperty(){
        vagueActuelleProperty.set(vagueActuelleProperty.get()+1);
    }

    public ArrayList<Ennemi> getEnnemiLesPlusProchesDePosition(Point coordonneDepart,int portee) {
        SortedSet<DistanceEnnemiCible> distanceEnnemiCibleSortedSet = new TreeSet<>();
        ArrayList<Ennemi> ennemisTrie = new ArrayList<>();
        double distance = 0;
        for (Ennemi ennemi : listeEnnemis) {
            distance = calculerDistance(coordonneDepart, ennemi);
            if (distance / 32 <= portee) {
                DistanceEnnemiCible distanceEnnemiCible = new DistanceEnnemiCible(ennemi,distance);
                distanceEnnemiCibleSortedSet.add(distanceEnnemiCible);
            }
        }
        for (DistanceEnnemiCible dec : distanceEnnemiCibleSortedSet){
            ennemisTrie.add(dec.getEnnemi());
        }
        return ennemisTrie;
    }
    private double calculerDistance(Point coordonneeDepart, Ennemi ennemi) {
        double dx = ennemi.getCoordonnees().getX() - coordonneeDepart.getX();
        double dy = ennemi.getCoordonnees().getY() - coordonneeDepart.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }


    public Terrain getTerrain(){return this.terrain;}

}



