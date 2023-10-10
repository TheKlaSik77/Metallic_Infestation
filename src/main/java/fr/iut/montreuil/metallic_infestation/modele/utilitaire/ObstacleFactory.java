package fr.iut.montreuil.metallic_infestation.modele.utilitaire;

import fr.iut.montreuil.metallic_infestation.modele.obstacles.Mine;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Obstacle;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Pics;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.Tourelle;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.TourelleAuto;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.TourelleMissiles;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.TourelleSemi;

public class ObstacleFactory {
    private Environnement environnement;
    public ObstacleFactory(Environnement environnement){
        this.environnement = environnement;
    }

    public Obstacle creerObstacle(int typeTour, Case c){
        Obstacle obstacle = null;
        switch (typeTour) {
            case 1:
                obstacle = new Pics(c,this.environnement);
                break;
            case 2:
                obstacle = new Mine(c,this.environnement);
                break;

            default:break;
        }
        return obstacle;
    }
}
