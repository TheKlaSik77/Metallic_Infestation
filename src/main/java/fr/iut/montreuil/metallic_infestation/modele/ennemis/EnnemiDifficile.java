package fr.iut.montreuil.metallic_infestation.modele.ennemis;

import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.ParcoursBFS;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

public class EnnemiDifficile extends Ennemi {
    public EnnemiDifficile() {
        super(300, 2,5);
    }

    @Override
    public String toString() {
        return "EnnemiDifficile" + super.toString();
    }
    public void retablirVitesse(){
        this.setVitesse(2);
    }
}
