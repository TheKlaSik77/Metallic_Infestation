package fr.iut.montreuil.metallic_infestation.modele.effets;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;

public class Ralentissement implements Effet{

    private int vitesse;
    public Ralentissement(int vitesse){
        this.vitesse = vitesse;
    }
    @Override
    public void appliquerEffet(Ennemi ennemi) {
        ennemi.setVitesse(1);
    }
}
