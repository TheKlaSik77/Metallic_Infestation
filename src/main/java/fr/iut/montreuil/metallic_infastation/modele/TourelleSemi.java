package fr.iut.montreuil.metallic_infastation.modele;

public class TourelleSemi extends Tourelle {
    public TourelleSemi(Case position,Environnement env,Terrain terrain){
        super(10,position,10,5,env,terrain);
    }

    @Override
    public void infligerDegats() {
        if(getEnnemiVise() != null) {
            getEnnemiVise().decrementerPv(getDegats());
            System.out.println(getEnnemiVise().getPv());
        }
    }
}
