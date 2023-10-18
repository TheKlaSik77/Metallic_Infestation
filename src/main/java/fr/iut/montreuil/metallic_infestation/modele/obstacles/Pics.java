package fr.iut.montreuil.metallic_infestation.modele.obstacles;


import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;

public class Pics extends Obstacle {

    private Ennemi ennemi;

    public Pics(Case c){
        super(c,10);
        this.ennemi = null;
    }

    public void actionnerPics(Ennemi e){
        if(e != null){e.setVitesse(1);}
    }
    
    @Override
    public void actionObstacle(){
        actionnerPics(this.ennemi);
    }
}
