package fr.iut.montreuil.metallic_infastation.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Laser {
    private Tourelle tourelle;
    private Ennemi ennemiVise;
    private String id;
    static int compteur = 0;

    public Laser(Tourelle tourelle, Ennemi ennemivisée) {
        this.tourelle = tourelle;
        this.ennemiVise = ennemivisée;
        this.compteur++;
        this.id = "L" +compteur;
    }

    public Point CoordonnéeTourelleDepart(){
        int x = tourelle.getPosition().getJ() *32;
        int y = tourelle.getPosition().getI() *32;
        return new Point(x, y);
    }

    public Point CordonnéeEnnemiArrive(){
        return ennemiVise.getCoordonnees();
    }

    public Ennemi getEnnemiVise(){
        return ennemiVise;
    }

    public Tourelle getTourelle (){
        return tourelle;
    }
    public boolean equals(Laser l){
        return this.getId().equals(l.getId());
    }
    public String getId(){
        System.out.println(id);return id;}

}
