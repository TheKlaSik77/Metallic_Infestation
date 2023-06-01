package fr.iut.montreuil.metallic_infastation.modele;

public class TourelleMissiles extends Tourelle{
    public TourelleMissiles(Case position,Environnement env,Terrain terrain) {
        super(1,position,50,5,env,terrain);
    }

    @Override
    public void infligerDegats() {
        if(getEnnemiVise() != null) {
            getEnnemiVise().decrementerPv(getDegats());
            System.out.println(getEnnemiVise().getPv());
        }
    }
}
