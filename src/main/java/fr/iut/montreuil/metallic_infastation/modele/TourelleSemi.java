package fr.iut.montreuil.metallic_infastation.modele;

public class TourelleSemi extends Tourelle {



    public TourelleSemi(Case position,Environnement env,Terrain terrain){
        super(1,position,1,5,env,terrain);
    }
    public void poserTourelle(){
        if (this.terrain.videSurCase(this.getPosition())){

            this.env.getListeTourelles().add(this);
            this.terrain.setCase(this.getPosition(),4);
        }
    }
}
