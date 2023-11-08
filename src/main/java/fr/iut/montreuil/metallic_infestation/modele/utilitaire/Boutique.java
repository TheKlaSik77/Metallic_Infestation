package fr.iut.montreuil.metallic_infestation.modele.utilitaire;

import fr.iut.montreuil.metallic_infestation.modele.obstacles.Mine;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Obstacle;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Pics;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.TypeObstacle;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.*;

public class Boutique {
    private static Boutique uniqueInstance = null;
   private Environnement environnement;

   private TourFactory tourFactory;

   private ObstacleFactory obstacleFactory;


    private Boutique (){
        this.environnement = Environnement.getInstance();
        this.tourFactory = new TourFactory();
        this.obstacleFactory = new ObstacleFactory();
    }

     public static Boutique getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new Boutique();
        }
        return uniqueInstance;
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
    public void achatTour(TypeTourelle typeTour, Case c){
       Tourelle tourelle = this.tourFactory.creerTour(typeTour,c);

        if(this.environnement.getJoueur().achatPossible(tourelle.getCout())) {
            environnement.ajouterDansListeTours(tourelle);
            this.environnement.getJoueur().debiterArgentProperty(tourelle.getCout());
        }
    }
    public void achatObstacle(TypeObstacle typeObstacle, Case c) {
        Obstacle obstacle = this.obstacleFactory.creerObstacle(typeObstacle,c);
        if(this.environnement.getJoueur().achatPossible(obstacle.getCout())){
            environnement.ajouterDansListeObstacles(obstacle);
            this.environnement.getJoueur().debiterArgentProperty(obstacle.getCout());
        }
    }

    public void venteTour(Case c) {
        if (Terrain.getInstance().tourSurCase(c)){
            this.environnement.getJoueur().crediterArgentProperty(environnement.retirerTour(c).getCout()/2);
            Terrain.getInstance().setCase(c, 2);
        }
    }

    public void venteObstacle(Case c) {
        if (Terrain.getInstance().obstacleSurCase(c)){
            this.environnement.getJoueur().crediterArgentProperty(environnement.retirerObstacle(c).getCout()/2);
            Terrain.getInstance().setCase(c, 1);
            System.out.println(Terrain.getInstance().getTerrain()[c.getI()][c.getJ()]);
        }
    }
}
