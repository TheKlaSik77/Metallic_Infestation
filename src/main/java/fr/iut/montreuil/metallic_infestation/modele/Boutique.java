package fr.iut.montreuil.metallic_infestation.modele;

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
     * typeTour == 1 -> TourelleSemi
     * typeTour == 2 -> TourelleAuto
     * typeTour == 3 -> TourelleMissiles
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
            Tourelle tourelle2 = new TourelleAuto(c, environnement, terrain);
            if(joueur.achatPossible(tourelle2.getCout())) {
                environnement.ajouterDansListeTours(tourelle2);
                joueur.debiterArgentProperty(tourelle2.getCout());
            }
        }
        else {
            Tourelle tourelle3 = new TourelleMissiles(c, environnement, terrain);
            if(joueur.achatPossible(tourelle3.getCout())) {
                environnement.ajouterDansListeTours(tourelle3);
                joueur.debiterArgentProperty(tourelle3.getCout());
            }
        }
    }

    public void venteTour(Case c) {
        if (terrain.tourSurCase(c)){
            joueur.crediterArgentProperty(environnement.retirerTour(c).getCout()/2);
            terrain.setCase(c, 2);

        }
    }

}