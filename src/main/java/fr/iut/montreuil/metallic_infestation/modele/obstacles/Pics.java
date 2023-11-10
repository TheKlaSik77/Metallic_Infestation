package fr.iut.montreuil.metallic_infestation.modele.obstacles;


import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;

public class Pics extends Obstacle {

    public Pics(Case c){
        super(c,10);
    }

    public void actionnerPics(Ennemi e){
        if(e != null){e.setVitesse(1);}
    }
    
    @Override
    public void actionObstacle(){
        actionnerPics(Environnement.getInstance().getTourDeJeu().getEnnemi());
    }

    @Override
    public String getTypeObstacle() {
        return TypeObstacle.Pics.name();
    }
}
