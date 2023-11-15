package fr.iut.montreuil.metallic_infestation.modele.obstacles;

import fr.iut.montreuil.metallic_infestation.modele.effets.Effet;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.ElementNonDeplacable;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

public abstract class Obstacle extends ElementNonDeplacable {
    private int cout;
    private Effet effet;
    public Obstacle(Case c, int cout, Effet effet) {
        super(c);
        this.cout = cout;
        this.effet = effet;
    }
    public int getCout() {
        return cout;
    }

    public Effet getEffet() {
        return effet;
    }

    public void poserObstacle() {
        if (Terrain.getInstance().cheminSurCase(this.getPosition())){
            // On dit que la case est occupée par une tour
            Terrain.getInstance().setCase(this.getPosition(),4);
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
    public void actionObstacle() {
        for (Ennemi e : Environnement.getInstance().getListeEnnemis()){
            this.getEffet().appliquerEffet(e);
        }
    }
    //Pour la BD

    public String getTypeObstacle() {
        return TypeObstacle.Pics.name();
    }

}
