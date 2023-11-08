package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;

import java.util.ArrayList;

public abstract class TourelleCiblageMultiple extends Tourelle{

    private int nbEnnemisCibles;
    private ArrayList<Ennemi> ennemisVises;

    public TourelleCiblageMultiple(Case position, int cout, int porteeTourelle, int nbEnnemisCibles,int vitesseAttaque){
        super(position,cout,porteeTourelle,vitesseAttaque);
        this.nbEnnemisCibles = nbEnnemisCibles;
        this.ennemisVises = new ArrayList<>();
    }

    public void rafraichirEnnemi(){
        this.ennemisVises = nEnnemisLesPlusProches(nbEnnemisCibles);
    }
    public abstract Projectile creerProjectile();

    public boolean testSiUnEnnemiAPortee() {
        return !ennemisVises.isEmpty();
    }
    public ArrayList<Ennemi> getEnnemisVises() {
        return ennemisVises;
    }
}
