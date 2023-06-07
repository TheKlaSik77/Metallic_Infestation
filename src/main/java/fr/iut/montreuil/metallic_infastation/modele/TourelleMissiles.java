package fr.iut.montreuil.metallic_infastation.modele;

import java.util.ArrayList;

public class TourelleMissiles extends Tourelle{
    public TourelleMissiles(Case position,Environnement env,Terrain terrain) {
        super(10,position,50,5,env,terrain);
    }

    @Override
    public void infligerDegats() {
        if(getEnnemiVise() != null) {
            getEnnemiVise().decrementerPv(getDegats());
            System.out.println(getEnnemiVise().getPv());
        }
    }

    public void tirerMissile(Case position){
        ArrayList<Ennemi> listeEnnemis = ennemisLesPlusProches(position);
        for (Ennemi e: listeEnnemis) {
            e.decrementerPv(getDegats());
        }
    }

}
