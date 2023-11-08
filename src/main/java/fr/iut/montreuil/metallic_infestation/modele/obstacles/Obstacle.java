package fr.iut.montreuil.metallic_infestation.modele.obstacles;

import fr.iut.montreuil.metallic_infestation.modele.effets.Effet;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.ElementNonDeplacable;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

public abstract class Obstacle extends ElementNonDeplacable implements Effet {
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

    public void poserObstacle() {
        if (Terrain.getInstance().cheminSurCase(this.getPosition())){
            // On dit que la case est occupée par une tour
            Terrain.getInstance().setCase(this.getPosition(),4);
        }
    }
    public void appliquerEffet(Ennemi ennemi){
        effet.appliquerEffet(ennemi);
    }
    public boolean ennemisSurObstacle() {
        for (Ennemi e : environnement.getListeEnnemis()){
            if (e.getCase().equals(this.getPosition())){
                return true;
            }
        }
        return false;
    }

    //Pour la BD
    public abstract String getTypeObstacle();


}
