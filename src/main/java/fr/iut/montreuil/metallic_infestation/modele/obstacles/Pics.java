package fr.iut.montreuil.metallic_infestation.modele.obstacles;


import fr.iut.montreuil.metallic_infestation.modele.effets.Effet;
import fr.iut.montreuil.metallic_infestation.modele.effets.Ralentissement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;

public class Pics extends Obstacle {

    public Pics(Case c){
        super(c,10,new Ralentissement(1));
    }

    @Override
    public String getTypeObstacle() {
        return TypeObstacle.Pics.name();
    }

    @Override
    public String getTypeObstacle() {
        return TypeObstacle.Pics.name();
    }
}
