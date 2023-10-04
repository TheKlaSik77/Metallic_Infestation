package fr.iut.montreuil.metallic_infestation.modele.utilitaire;

import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.Tourelle;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.TourelleAuto;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.TourelleMissiles;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.TourelleSemi;

import javax.net.ssl.SSLEngineResult;

public class TourFactory {

    private Environnement environnement;
    public TourFactory(Environnement environnement){
        this.environnement = environnement;
    }

    public Tourelle creerTour(int typeTour, Case c){
        Tourelle tourelle = null;
        switch (typeTour) {
            case 1:
                tourelle = new TourelleSemi(c,this.environnement,this.environnement.getTerrain());
                break;
            case 2:
                tourelle = new TourelleAuto(c,this.environnement,this.environnement.getTerrain());
                break;
            case 3:
                tourelle = new TourelleMissiles(c,this.environnement,this.environnement.getTerrain());
                break;
            default:break;
        }
        return tourelle;
    }
}
