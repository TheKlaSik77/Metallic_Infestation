package fr.iut.montreuil.metallic_infestation.modele.utilitaire;


import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Obstacle;
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
    private UnTour tour;

    private Environnement() {
        this.terrain = Terrain.getInstance();
        this.listeEnnemis = FXCollections.observableArrayList();
        this.listeTourelles = FXCollections.observableArrayList();
        this.listeProjectiles = FXCollections.observableArrayList();
        this.listeLasers = FXCollections.observableArrayList();
        this.listExplosions = FXCollections.observableArrayList();
        this.listeObstacles = FXCollections.observableArrayList();
        this.ennemisASpawn =  new ArrayList<>();
        this.parcoursBFS = ParcoursBFS.getInstance();
        parcoursBFS.remplirGrilleBFS();
        this.joueur = Joueur.getInstance(100,1000);
        vagueActuelleProperty = new SimpleIntegerProperty(0);
        this.gestionnaireVagues = new GestionnaireVagues(this);
        this.tour = new UnTour();
        nbTours = 1;
    }
    public static Environnement getInstance(){
        if (uniqueInstance==null){
            uniqueInstance = new Environnement();
        }
        return uniqueInstance;
    }

    //GETTERS
    public Terrain getTerrain(){return this.terrain;}
    public ObservableList<Ennemi> getListeEnnemis() {
        return listeEnnemis;
    }
    public ObservableList<Tourelle> getListeTourelles() {return listeTourelles;}
    public ObservableList<Projectile> getListeProjectiles() {return listeProjectiles;}
    public ObservableList<Laser> getListeLasers(){
        return listeLasers;
    }
    public ObservableList<Explosion> getListExplosions(){return listExplosions;}
    public ObservableList<Obstacle> getListeObstacles() {return this.listeObstacles;}
    public ParcoursBFS getParcoursBFS(){
        return parcoursBFS;
    }
    public Joueur getJoueur() {return this.joueur;}
    public IntegerProperty vagueActuelleProperty(){return vagueActuelleProperty;}
    public GestionnaireVagues getGestionnaireVagues(){
        return gestionnaireVagues;
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

    public void ajouterProjectile(Projectile p) {
        listeProjectiles.add(p);
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


    public void setVagueActuelleProperty(int n ){
        this.vagueActuelleProperty().setValue(n);
    }

    public static void incrementerVagueActuelleProperty(){
        vagueActuelleProperty.set(vagueActuelleProperty.get()+1);
    }

    //Déplacement méthode unTour
    public void unTour() {
        this.tour.unTour();
    }
    public int getNbTours(){return this.nbTours;}

    public UnTour getTourDeJeu(){return this.tour;}
    public void incrementeNbTours(){this.nbTours++;}
    public ArrayList<Ennemi> getEnnemisASpawn(){return this.ennemisASpawn;}
    public void setEnnemisASpawn(ArrayList<Ennemi> ennemisASpawn){ this.ennemisASpawn = ennemisASpawn;}

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

}



