package fr.iut.montreuil.metallic_infestation.modele.obstacles;

import fr.iut.montreuil.metallic_infestation.modele.effets.Effet;
import fr.iut.montreuil.metallic_infestation.modele.effets.Explosion;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;

public class Mine extends Obstacle {
    public Mine(Case c) {
        super(c, 20, new Explosion(1,300,Environnement.getInstance()));
    }

    @Override
    public String getTypeObstacle() {
        return TypeObstacle.Mine.name();
    }

}
