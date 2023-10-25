package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;


import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.Laser;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.Tourelle;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

public class TourelleAuto extends Tourelle {
    public TourelleAuto(Case position) {
        super(5,position,30,3, 0);
    }

    @Override
    public void infligerDegats() {
        if(getEnnemiVise() != null) {
            getEnnemiVise().decrementerPv(getDegats());
            System.out.println(getEnnemiVise().getPv());

        }
    }

    public Laser creerLaser() {
        if (!environnement.estEstPresent(getEnnemiVise())){
            return new Laser(this, getEnnemiVise());
        }
        return null;
    }
}
