package fr.iut.montreuil.metallic_infastation.modele;

import java.util.ArrayList;

public class TourelleAuto extends Tourelle{
    public TourelleAuto(Case position,Environnement env,Terrain terrain) {
        super(5,position,30,5,env,terrain);
    }

    @Override
    public void infligerDegats() {
        ArrayList<Ennemi> listeEnnemis = ennemisLesPlusProches(getPosition());
        int tailleListe = Math.min(3, listeEnnemis.size());
        //System.out.println(listeEnnemis.toString());
        for (int i = 0 ; i < tailleListe; i++){
            listeEnnemis.get(i).decrementerPv(getDegats());
        }
    }

    public Laser creerLaser() {
        if (env.destEstPresent(getEnnemiVise()) != true){
            return new Laser(this, getEnnemiVise());
        }
        return null;
    }
}
