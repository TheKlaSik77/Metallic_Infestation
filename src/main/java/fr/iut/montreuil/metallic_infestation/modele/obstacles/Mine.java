package fr.iut.montreuil.metallic_infestation.modele.obstacles;

import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Explosion;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;

public class Mine extends Obstacle {

    private int degats;


    private int porteeExplosion;
    public Mine(Case c) {
        super(c, 20);
        this.degats = 300;
        this.porteeExplosion = 2;
    }

    public int getDegats(){
        return this.degats;
    }
    public int getPorteeExplosion() {
        return porteeExplosion;
    }

    //TODO:
    @Override
    public void actionObstacle() {
/*
        Environnement.getInstance().getTerrain().setCase(this.getPosition(), 1);
        Explosion explosion = new Explosion(this.getPosition().getCentreCase(), this.getDegats(), this.getPorteeExplosion());
        Environnement.getInstance().getListExplosions().add(explosion);
        explosion.infligerDegats();
        Environnement.getInstance().getTourDeJeu().getObstaclesASupp().add(this);
*/

    }


}
