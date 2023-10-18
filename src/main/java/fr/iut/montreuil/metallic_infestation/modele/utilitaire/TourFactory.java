package fr.iut.montreuil.metallic_infestation.modele.utilitaire;

import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.*;

import javax.net.ssl.SSLEngineResult;

public class TourFactory {


    public TourFactory(){}

    public Tourelle creerTour(TypeTourelle type, Case c){
        Tourelle tourelle = type.creerTourelle(c);
        return tourelle;
    }

}
