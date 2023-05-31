package fr.iut.montreuil.metallic_infastation.modele;

import javafx.collections.ObservableList;

public class Boutique {
   private Joueur joueur;
   private Environnement environnement;

   private Terrain terrain;





    public Boutique (Joueur joueur, Environnement environnement, Terrain terrain){
        this.environnement = environnement;
        this.joueur = joueur;
        this.terrain = terrain;
    }

    public void AchatPv (int montant, int pv) {
        if (joueur.achatPossible(montant)){
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
                environnement.ajouterDansListeTours(tourelle1);
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
        if (terrain.tourSurCase(c)){
            joueur.crediterArgentProperty(environnement.retirerTour(c).getCout());
            terrain.setCase(c, 2);

        }
    }

}
