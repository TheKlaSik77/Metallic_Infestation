package fr.iut.montreuil.metallic_infestation.modele.utilitaire;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Mine;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Obstacle;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Pics;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.*;

import java.util.ArrayList;

public class UnTour {

    private Ennemi ennemi;
    private ArrayList<Ennemi> ennemisASupp;
    private ArrayList<Projectile> listeProjectilesASupp;
    private ArrayList<Obstacle> listeObstaclesASupp;

    public int nbTours;

    public UnTour() {
        this.ennemisASupp = new ArrayList<>();
        this.listeObstaclesASupp = new ArrayList<>();
        this.listeProjectilesASupp = new ArrayList<>();
        this.ennemi = null;
        this.nbTours = 1;
    }

    public void unTour() {

        //TODO : En faire une variable (YASMINE)
        if (this.getNbTours() % 700 == 0 || this.getNbTours() == 100) {
            Environnement.getInstance().setEnnemisASpawn(Environnement.getInstance().getGestionnaireVagues().lancerProchaineVague());
        }

        if (this.getNbTours() % 20 == 0 && !Environnement.getInstance().getEnnemisASpawn().isEmpty()) {
            Environnement.getInstance().getListeEnnemis().add(Environnement.getInstance().getEnnemisASpawn().remove(Environnement.getInstance().getEnnemisASpawn().size() - 1));
        }
        actionEnnemis();
        actionTours();
        actionProjectiles();
        actionObstacle();
        this.nbTours++;
    }

    public void actionObstacle(){
        for (Obstacle o : Environnement.getInstance().getListeObstacles()){
            if (o.ennemisSurObstacle()){
                o.actionnerObstacle();
                listeObstaclesASupp.add(o);
            }
        }
    }
    public void actionTours(){
        for (Tourelle t : Environnement.getInstance().getListeTourelles()) {
            t.raffraichirEnnemi();
            if (t.ennemiEstCible() && nbTours % t.getVitesseAttaque() == 0) {
                t.creerProjectile();
            }
        }
    }
    public void actionEnnemis(){

        for (Ennemi e : Environnement.getInstance().getListeEnnemis()) {
            e.seDeplacer();
            if (e.aAtteintLaCible()) {
                Environnement.getInstance().getJoueur().debiterPvJoueurProperty(e.getDrop());
                Environnement.getInstance().getListeEnnemis().remove(e);
            } else if (e.estMort()) {
                Environnement.getInstance().getJoueur().crediterArgentProperty(e.getDrop());
                Environnement.getInstance().getListeEnnemis().remove(e);
            }
        }
    }

    public void actionProjectiles(){
        for (Projectile p : Environnement.getInstance().getListeProjectiles()){
            p.seDeplacer();
            if (p.arriveSurEnnemi()){
                p.appliquerEffet();
                Environnement.getInstance().getListeProjectiles().remove(p);
            }
        }
    }

    public Ennemi getEnnemi(){return this.ennemi;}

    public ArrayList<Obstacle> getObstaclesASupp(){return this.listeObstaclesASupp;}

    public int getNbTours(){return this.nbTours;}
    
}
