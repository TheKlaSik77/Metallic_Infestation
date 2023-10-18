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

    private int id;
    private Case position;
    private int cout;
    private int porteeTourelle; //En nombre de case
    private Environnement env;
    private Terrain terrain;
    private ArrayList<Ennemi> ennemisCibles;
    private int nbEnnemisCibles;
    private int compteur = 0;

    public Tourelle(Case position, int cout, int porteeTourelle, Environnement env, Terrain terrain){
        this.compteur++;
        this.id = compteur;
        this.position = position;
        this.cout = cout;
        this.porteeTourelle = porteeTourelle;
        this.env = env;
        this.terrain = terrain;
    }

    public Case getPosition(){
        return this.position;
    }

    public int getPorteeTourelle(){
        return this.porteeTourelle;
    }
    public void raffraichirEnnemiVise(){
        this.ennemisCibles = this.nEnnemisLesPlusProches(nbEnnemisCibles);
    }
    public ArrayList<Ennemi> getEnnemisCibles(){
        return this.ennemisCibles;
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
}
