package fr.iut.montreuil.metallic_infestation.modele.utilitaire;

import fr.iut.montreuil.metallic_infestation.modele.obstacles.Mine;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Obstacle;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Pics;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.Tourelle;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.TourelleAuto;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.TourelleMissiles;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.TourelleSemi;

public class Boutique {
   private Joueur joueur;
   private Environnement environnement;

   private Terrain terrain;

   private TourFactory tourFactory;

   private ObstacleFactory obstacleFactory;


    public Boutique (Joueur joueur, Environnement environnement, Terrain terrain){
        this.environnement = environnement;
        this.joueur = joueur;
        this.terrain = terrain;
        this.tourFactory = new TourFactory(environnement);
        this.obstacleFactory = new ObstacleFactory(environnement);
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
       Tourelle tourelle = this.tourFactory.creerTour(typeTour,c);

        if(joueur.achatPossible(tourelle.getCout())) {
            environnement.ajouterDansListeTours(tourelle);
            joueur.debiterArgentProperty(tourelle.getCout());
        }
    }

    public void achatObstacle(int typeObstacle, Case c) {
        Obstacle obstacle = this.obstacleFactory.creerObstacle(typeObstacle,c);
        if(joueur.achatPossible(obstacle.getCout())){
            environnement.ajouterDansListeObstacles(obstacle);
            joueur.debiterArgentProperty(obstacle.getCout());
        }
    }

    public void venteTour(Case c) {
        if (terrain.tourSurCase(c)){
            joueur.crediterArgentProperty(environnement.retirerTour(c).getCout()/2);
            terrain.setCase(c, 2);

        }
    }

    public void venteObstacle(Case c) {
        if (terrain.obstacleSurCase(c)){
            joueur.crediterArgentProperty(environnement.retirerObstacle(c).getCout()/2);
            terrain.setCase(c, 1);
            System.out.println(terrain.getTerrain()[c.getI()][c.getJ()]);
        }
    }
}
