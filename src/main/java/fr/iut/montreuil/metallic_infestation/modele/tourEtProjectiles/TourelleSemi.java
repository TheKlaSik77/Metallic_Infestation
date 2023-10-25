package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.Tourelle;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

public class TourelleSemi extends TourelleCiblageIndividuel {

    public TourelleSemi(Case position, Environnement env, Terrain terrain) {
        super(position, 10, 5, env, terrain,200);
    }

    @Override
    public Projectile creerProjectile() {
        return new ProjectileSemi();
    }

}
