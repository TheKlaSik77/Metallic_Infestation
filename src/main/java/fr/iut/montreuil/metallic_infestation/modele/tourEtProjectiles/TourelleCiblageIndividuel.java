package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

import java.util.ArrayList;
import java.util.Map;

public abstract class TourelleCiblageIndividuel extends Tourelle{
    public TourelleCiblageIndividuel(Case position, int cout, int porteeTourelle, Environnement env, Terrain terrain) {
        super(position, cout, porteeTourelle, env, terrain);
    }

    public Ennemi ennemiLePlusProche() {
        ArrayList<Ennemi> tabDistanceEnnemiTrie = this.getEnv().getEnnemiLesPlusProchesDePosition(this.getCoordonnes(),this.getPorteeTourelle());
        return tabDistanceEnnemiTrie.get(0);
    }
}
