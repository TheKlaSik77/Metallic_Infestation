package fr.iut.montreuil.metallic_infestation.modele.obstacles;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.ElementDeplacable;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.ElementNonDeplacable;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;

public abstract class Obstacle extends ElementNonDeplacable {
    private int cout;

    public Obstacle(Case c, Environnement environnement, int cout) {
        super(c, environnement);
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


}
