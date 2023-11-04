package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

public class TourelleMissiles extends TourelleCiblageIndividuel {


    public TourelleMissiles(Case position, Environnement env, Terrain terrain) {
        super(position, 30, 6, env, terrain,100);
    }

    @Override
    public Projectile creerProjectile() {
        return new ProjectileMissile(this.getEnv(),this.getCoordonnes().getCase().getCentreCase(),this.ennemiLePlusProche());

    }
}
