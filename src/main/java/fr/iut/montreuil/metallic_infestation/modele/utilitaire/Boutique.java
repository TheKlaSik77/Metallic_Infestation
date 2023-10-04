package fr.iut.montreuil.metallic_infestation.modele.utilitaire;

import fr.iut.montreuil.metallic_infestation.modele.obstacles.Mine;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Obstacle;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Pics;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.Tourelle;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.TourelleAuto;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.TourelleMissiles;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.TourelleSemi;

public class Boutique {
   private Environnement environnement;

    public Boutique (Environnement environnement){
        this.environnement = environnement;
    }

    public void AchatPv (int montant, int pv) {
        if (this.environnement.getJoueur().achatPossible(montant)){
            this.environnement.getJoueur().crediterPvJoueurProperty(pv);
            this.environnement.getJoueur().debiterArgentProperty(montant);
        }
    }
    /**
     * typeTour == 1 -> TourelleSemi
     * typeTour == 2 -> TourelleAuto
     * typeTour == 3 -> TourelleMissiles
     */
    public void achatTour(int typeTour, Case c){

        Tourelle tourelle = null;
        switch(typeTour){
            case 1 :
                tourelle = new TourelleSemi(c, environnement, this.environnement.getTerrain());
                break;
            case 2 :
                tourelle = new TourelleAuto(c, environnement, this.environnement.getTerrain());
                break;
            case 3 :
                tourelle = new TourelleMissiles(c, environnement, this.environnement.getTerrain());
                break;
            default: break;
        }

        if(this.environnement.getJoueur().achatPossible(tourelle.getCout())) {
            environnement.ajouterDansListeTours(tourelle);
            this.environnement.getJoueur().debiterArgentProperty(tourelle.getCout());
        }
    }

    //Créer un nouvel obstable en fonction de ce qui a été appelé par la vue
    public void achatObstacle(int typeObstacle, Case c) {

        Obstacle obstacle = null;
        switch (typeObstacle){
            case 1:
                obstacle = new Pics(c,environnement,this.environnement.getTerrain());
                break;
            case 2:
                obstacle = new Mine(c,environnement,this.environnement.getTerrain());
                break;
            default: break;
        }
        
        if(this.environnement.getJoueur().achatPossible(obstacle.getCout())){
            environnement.ajouterDansListeObstacles(obstacle);
            this.environnement.getJoueur().debiterArgentProperty(obstacle.getCout());
        }
    }

    public void venteTour(Case c) {
        if (this.environnement.getTerrain().tourSurCase(c)){
            this.environnement.getJoueur().crediterArgentProperty(environnement.retirerTour(c).getCout()/2);
            this.environnement.getTerrain().setCase(c, 2);
        }
    }

    public void venteObstacle(Case c) {
        if (this.environnement.getTerrain().obstacleSurCase(c)){
            this.environnement.getJoueur().crediterArgentProperty(environnement.retirerObstacle(c).getCout()/2);
            this.environnement.getTerrain().setCase(c, 1);
            System.out.println(this.environnement.getTerrain().getTerrain()[c.getI()][c.getJ()]);
        }
    }
}
