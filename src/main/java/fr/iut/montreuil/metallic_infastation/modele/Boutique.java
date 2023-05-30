package fr.iut.montreuil.metallic_infastation.modele;

import javafx.collections.ObservableList;

import java.util.Iterator;

public class Boutique {
   private Joueur joueur;
   private Environnement environnement;

   private Terrain terrain;

   private ObservableList<Tourelle> tourelles;




    public Boutique (Joueur joueur, Environnement environnement, Terrain terrain){
        this.environnement = environnement;
        this.joueur = joueur;
        this.terrain = terrain;
        this.tourelles = environnement.getListeTourelles();

    }

    public void AchatPv (int montant, int pv) {
        if (joueur.achatPossible(montant) == true){
            joueur.crediterPvJoueurProperty(pv);
            joueur.debiterArgentProperty(montant);
        }
    }
    /**
     * idTour == 1 -> TourelleSemi
     * idTour == 2
     *
     */
    public void achatTour(int typeTour, Case c){
        if (typeTour == 1){
            Tourelle tourelle1 = new TourelleSemi(c, environnement, terrain);
            if(joueur.achatPossible(tourelle1.getCout())) {
                tourelles.add(tourelle1);
                environnement.poserTour(c,tourelle1);
                joueur.debiterArgentProperty(tourelle1.getCout());
            }
        }
        else if (typeTour == 2) {
            //TODO
        }
        else {
            //TODO
        }
    }

    public void venteTour(Case c) {
        for (int i = environnement.getListeTourelles().size() - 1 ; i >= 0 ; i--){
            if (environnement.getListeTourelles().get(i).getPosition().caseEgale(c)){
                environnement.getListeTourelles().remove(i);
            }
        }
        terrain.setCase(c, 2);
    }

}
