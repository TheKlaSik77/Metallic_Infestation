package fr.iut.montreuil.metallic_infestation.modele.obstacles;

import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;

public enum TypeObstacle {
    Mine{
        @Override
        public Obstacle creerObstacle(Case c) {
            return new Mine(c);
        }
    },
    Pics{
        @Override
        public Obstacle creerObstacle(Case c) {
            return new Pics(c);
        }
    };
    public abstract Obstacle creerObstacle(Case c);
}
