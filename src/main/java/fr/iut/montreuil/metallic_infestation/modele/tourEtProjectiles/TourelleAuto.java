package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;


import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

public class TourelleAuto extends TourelleCiblageIndividuel {

    public TourelleAuto(Case position) {
        super(position, 20, 4, Environnement.getInstance(), Terrain.getInstance(),20);
    }

    @Override
    public Projectile creerProjectile() {
        return new ProjectileAuto(this.getCoordonnes(),this.ennemiLePlusProche());
    }
}
