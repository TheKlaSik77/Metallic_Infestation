package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;


import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.Laser;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.Tourelle;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

public class TourelleAuto extends Tourelle {
    public TourelleAuto(Case position, Environnement env, Terrain terrain) {
        super(5,position,30,3,env,terrain, 0);
    }




    public Laser creerLaser() {
        if (!env.estEstPresent(getEnnemiVise())){
            return new Laser(this, getEnnemiVise());
        }
        return null;
    }
}
