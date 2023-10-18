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
        e.setVitesse(1);
    }


}
