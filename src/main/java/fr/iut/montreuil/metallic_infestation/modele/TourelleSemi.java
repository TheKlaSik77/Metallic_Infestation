package fr.iut.montreuil.metallic_infestation.modele;

public class TourelleSemi extends Tourelle {
    public TourelleSemi(Case position,Environnement env,Terrain terrain){
        super(20,position,10,5,env,terrain,0);
    }

    @Override
    public void infligerDegats() {
        if(getEnnemiVise() != null) {
            getEnnemiVise().decrementerPv(getDegats());
        }
    }
}
