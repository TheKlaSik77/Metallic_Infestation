package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

import java.util.ArrayList;
import java.util.Map;

public abstract class TourelleCiblageMultiple extends Tourelle{

    int nbEnnemisCibles;

    public TourelleCiblageMultiple(Case position, int cout, int porteeTourelle, Environnement env, Terrain terrain, int nbEnnemisCibles){
        super(position,cout,porteeTourelle,env,terrain);
        this.nbEnnemisCibles = nbEnnemisCibles;
    }

    public ArrayList<Ennemi> nEnnemisLesPlusProches() {
        int cpt = 0;
        ArrayList<Ennemi> ennemisLesPlusProches = new ArrayList<>();
        ArrayList<Ennemi> tabDistanceEnnemiTrie = this.getEnv().getEnnemiLesPlusProchesDePosition(this.getCoordonnes(),this.getPorteeTourelle());
        for (Ennemi e : tabDistanceEnnemiTrie) {
            if (cpt < this.nbEnnemisCibles) {
                ennemisLesPlusProches.add(e);
                cpt++;
            } else {
                break;
            }
        }
        return ennemisLesPlusProches;
    }
    public void raffraichirEnnemi(){
        nEnnemisLesPlusProches();
    }
    public abstract Projectile creerProjectile();
}
