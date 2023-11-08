package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;

import java.util.ArrayList;

public abstract class TourelleCiblageMultiple extends Tourelle{

    private final int nbEnnemisCibles;

    public TourelleCiblageMultiple(Case position, int cout, int porteeTourelle, int nbEnnemisCibles,int vitesseAttaque){
        super(position,cout,porteeTourelle,vitesseAttaque);
        this.nbEnnemisCibles = nbEnnemisCibles;
    }

    public void rafraichirEnnemi(){
        setEnnemisCibles(nEnnemisLesPlusProches(nbEnnemisCibles));
    }
    public abstract Projectile creerProjectile();
}
