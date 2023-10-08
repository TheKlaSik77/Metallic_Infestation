package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

public abstract class TourelleCiblageIndividuel extends Tourelle{

    public TourelleCiblageIndividuel(int degats, Case position, int cout, int porteeTourelle, Environnement env, Terrain terrain, int porteeMissile) {
        super(degats, position, cout, porteeTourelle, env, terrain, porteeMissile);
    }
    @Override
    public void infligerDegats() {
        if(getEnnemiVise() != null) {
            getEnnemiVise().decrementerPv(getDegats());
        }
    }
}
