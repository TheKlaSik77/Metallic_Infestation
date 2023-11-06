package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.utilitaire;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;

public class DistanceEnnemiCible implements Comparable<DistanceEnnemiCible>{
    Ennemi ennemi;
    double distance;

    public DistanceEnnemiCible(Ennemi ennemi, double distance){
        this.ennemi = ennemi;
        this.distance = distance;
    }

    public Ennemi getEnnemi() {
        return ennemi;
    }

    public double getDistance(){
        return this.distance;
    }

    @Override
    public int compareTo(DistanceEnnemiCible distanceEnnemiCible) {
        return Double.compare(this.distance, distanceEnnemiCible.getDistance());
    }
}
