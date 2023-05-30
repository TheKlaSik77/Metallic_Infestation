package fr.iut.montreuil.metallic_infastation.modele;

public class TourelleMissiles extends Tourelle{
    public TourelleMissiles(int degats, Case position, int cout, int portee, Environnement env, Terrain terrain) {
        super(1,position,50,5,env,terrain);
    }
}
