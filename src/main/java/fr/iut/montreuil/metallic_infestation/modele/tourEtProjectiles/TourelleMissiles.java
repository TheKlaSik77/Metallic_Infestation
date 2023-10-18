package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.Tourelle;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

import java.util.ArrayList;

public class TourelleMissiles extends TourelleCiblageIndividuel {


    public TourelleMissiles(Case position, int cout, int porteeTourelle, Environnement env, Terrain terrain) {
        super(position, cout, porteeTourelle, env, terrain);
    }

    @Override
    public Projectile creerProjectile() {
        return new ProjectileMissile();
    }
}
