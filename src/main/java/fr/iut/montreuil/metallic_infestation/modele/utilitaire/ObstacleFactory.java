package fr.iut.montreuil.metallic_infestation.modele.utilitaire;

import fr.iut.montreuil.metallic_infestation.modele.obstacles.Mine;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Obstacle;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Pics;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.TypeObstacle;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.Tourelle;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.TourelleAuto;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.TourelleMissiles;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.TourelleSemi;

public class ObstacleFactory {
    
    public ObstacleFactory(){}


    public Obstacle creerObstacle(TypeObstacle type, Case c){
        Obstacle obstacle = type.creerObstacle(c);
        return obstacle;
    }
}
