package fr.iut.montreuil.metallic_infestation.modele.effets;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;

public class DegatIndividuel extends Degat {

    public DegatIndividuel(int degat){
        super(degat);
    }

    @Override
    public void appliquerEffet(Ennemi ennemi) {
        ennemi.decrementerPv(this.getDegat());
    }
}
