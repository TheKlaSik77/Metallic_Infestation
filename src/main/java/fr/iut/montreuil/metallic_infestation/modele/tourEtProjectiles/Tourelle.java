package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.ProjectileMissile;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.ProjectileSemi;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Point;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public abstract class Tourelle {

    private final int id;
    private final Case position;
    private final int cout;
    private final int porteeTourelle; //En nombre de case
    private Environnement env;
    private Terrain terrain;
    private final int vitesseAttaque; // Vitesse d'attaque des tours (tous les combien de tours elle attaque)
    private ArrayList<Ennemi> ennemisCibles;
    private int compteur = 0;

    public Tourelle(Case position, int cout, int porteeTourelle,int vitesseAttaque){
        this.compteur++;
        this.id = compteur;
        this.position = position;
        this.cout = cout;
        this.porteeTourelle = porteeTourelle;
        this.env = Environnement.getInstance();
        this.terrain = Terrain.getInstance();
        this.vitesseAttaque = vitesseAttaque;
    }

    public Case getPosition(){
        return this.position;
    }

    public int getPorteeTourelle(){
        return this.porteeTourelle;
    }
    public abstract void raffraichirEnnemi();
    public ArrayList<Ennemi> getEnnemisCibles(){
        return this.ennemisCibles;
    }
    public ArrayList<Ennemi> nEnnemisLesPlusProches(int nbEnnemisCibles) {
        int cpt = 0;
        ArrayList<Ennemi> ennemisLesPlusProches = new ArrayList<>();
        ArrayList<Ennemi> tabDistanceEnnemiTrie = this.getEnv().getEnnemiLesPlusProchesDePosition(this.getCoordonnes(),this.getPorteeTourelle());
        for (Ennemi e : tabDistanceEnnemiTrie) {
            if (cpt < nbEnnemisCibles) {
                ennemisLesPlusProches.add(e);
                cpt++;
            } else {
                break;
            }
        }
        return ennemisLesPlusProches;
    }
    public int getVitesseAttaque(){
        return this.vitesseAttaque;
    }
    public int getCout() {return this.cout;}
    public void poserTourelle(){
        if (this.terrain.emplacementVideSurCase(this.getPosition())){
            // On dit que la case est occupée par une tour
            terrain.setCase(this.getPosition(),3);
        }
    }

    public Point getCoordonnes(){
        return getPosition().getCentreCase();
    }
    public abstract Projectile creerProjectile();

    public Environnement getEnv() {
        return env;
    }
    public boolean ennemiEstCible(){
        return !this.getEnnemisCibles().isEmpty();
    }
    public void setEnnemisCibles(ArrayList<Ennemi> ennemisCibles){
        this.ennemisCibles = ennemisCibles;
    }
}
