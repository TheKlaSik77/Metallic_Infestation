package fr.iut.montreuil.metallic_infestation.modele.ennemis;

import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.ParcoursBFS;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

public class EnnemiFacile extends Ennemi {

    public EnnemiFacile(){
        super(50,4,1);
    }

    @Override
    public String toString() {
        return "EnnemiFacile" + super.toString();
    }
    public void retablirVitesse(){
        this.setVitesse(4);
    }
}
