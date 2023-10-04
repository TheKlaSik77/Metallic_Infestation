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

   private TourFactory tourFactory;

   private ObstacleFactory obstacleFactory;


    public Boutique (Joueur joueur, Environnement environnement, Terrain terrain){
        this.environnement = environnement;
        this.tourFactory = new TourFactory(environnement);
        this.obstacleFactory = new ObstacleFactory(environnement);
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
       Tourelle tourelle = this.tourFactory.creerTour(typeTour,c);

        if(this.environnement.getJoueur().achatPossible(tourelle.getCout())) {
            environnement.ajouterDansListeTours(tourelle);
            this.environnement.getJoueur().debiterArgentProperty(tourelle.getCout());
        }
    }
    public void achatObstacle(int typeObstacle, Case c) {
        Obstacle obstacle = this.obstacleFactory.creerObstacle(typeObstacle,c);
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
