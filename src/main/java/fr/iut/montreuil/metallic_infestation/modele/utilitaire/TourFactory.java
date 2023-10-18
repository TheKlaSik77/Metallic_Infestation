package fr.iut.montreuil.metallic_infestation.modele.utilitaire;

import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.*;

import javax.net.ssl.SSLEngineResult;

public class TourFactory {

    private Environnement environnement;
    public TourFactory(Environnement environnement){
        this.environnement = environnement;
    }

    public Tourelle creerTour(TypeTourelle type, Case c){
        Tourelle tourelle = type.creerTourelle(c, environnement);
        return tourelle;
    }

    /*Tourelle tourelle = null;
        switch (typeTour) {
            case 1:
                tourelle = new TourelleSemi(c,this.environnement);
                break;
            case 2:
                tourelle = new TourelleAuto(c,this.environnement);
                break;
            case 3:
                tourelle = new TourelleMissiles(c,this.environnement);
                break;
            default:break;
        }
        return tourelle;*/
}
