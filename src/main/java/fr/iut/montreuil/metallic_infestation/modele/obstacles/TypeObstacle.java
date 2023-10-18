package fr.iut.montreuil.metallic_infestation.modele.obstacles;

import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;

public enum TypeObstacle {
    Mine{
        @Override
        public Obstacle creerObstacle(Case c, Environnement environnement) {
            return new Mine(c,environnement);
        }
    },
    Pics{
        @Override
        public Obstacle creerObstacle(Case c, Environnement environnement) {
            return new Pics(c,environnement);
        }
    };
    public abstract Obstacle creerObstacle(Case c, Environnement environnement);
}
