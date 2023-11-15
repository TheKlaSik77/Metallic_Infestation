package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.effets;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;

public abstract class Degat implements Effet{

    private int degat;
    public Degat(int degat){
        this.degat = degat;
    }
    @Override
    public abstract void appliquerEffet(Ennemi ennemi);
    public int getDegat(){
        return this.degat;
    }
}
