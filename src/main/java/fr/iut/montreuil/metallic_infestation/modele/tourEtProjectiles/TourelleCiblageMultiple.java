package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

import java.util.ArrayList;
import java.util.Map;

public abstract class TourelleCiblageMultiple extends Tourelle{

    int nbEnnemisCibles;

    public TourelleCiblageMultiple(Case position, int cout, int porteeTourelle, Environnement env, Terrain terrain, int nbEnnemisCibles,int vitesseAttaque){
        super(position,cout,porteeTourelle,env,terrain,vitesseAttaque);
        this.nbEnnemisCibles = nbEnnemisCibles;
    }

    public void raffraichirEnnemi(){
        nEnnemisLesPlusProches(nbEnnemisCibles);
    }
    public abstract Projectile creerProjectile();
}
