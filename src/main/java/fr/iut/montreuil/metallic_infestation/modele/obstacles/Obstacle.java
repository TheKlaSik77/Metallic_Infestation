package fr.iut.montreuil.metallic_infestation.modele.obstacles;

import fr.iut.montreuil.metallic_infestation.modele.utilitaire.ElementNonDeplacable;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;

public abstract class Obstacle extends ElementNonDeplacable {
    private int cout;

    public Obstacle(Case c, int cout) {
        super(c);
        this.cout = cout;
    }
    public int getCout() {
        return cout;
    }

    public void poserObstacle() {
        if (this.environnement.getTerrain().cheminSurCase(this.getPosition())){
            // On dit que la case est occupée par une tour
            this.environnement.getTerrain().setCase(this.getPosition(),4);
        }
    }
    public boolean ennemisSurObstacle() {
        for (Ennemi e : environnement.getListeEnnemis()){
            if (e.getCase().equals(this.getPosition())){
                return true;
            }
        }
        return false;
    }

    public abstract void actionObstacle();

    //Pour la BD
    public abstract String getTypeObstacle();


}
