package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;


import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.Laser;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.Tourelle;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

public class TourelleAuto extends TourelleCiblageIndividuel {

    public TourelleAuto(Case position, int cout, int porteeTourelle, Environnement env, Terrain terrain) {
        super(position, cout, porteeTourelle, env, terrain,20);
    }

    @Override
    public Projectile creerProjectile() {
        return new Laser();
    }
}
