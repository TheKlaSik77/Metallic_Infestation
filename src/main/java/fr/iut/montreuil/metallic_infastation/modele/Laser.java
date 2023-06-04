package fr.iut.montreuil.metallic_infastation.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Laser {
    private Tourelle tourelle;
    private Ennemi ennemiVise;
    private String id;
    private DoubleProperty distanceProperty;

    private DoubleProperty angleProperty;
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

    public void seDeplace(){
        this.distanceProperty.set(Math.sqrt(Math.pow(CordonnéeEnnemiArrive().getX() - CoordonnéeTourelleDepart().getX(), 2) + Math.pow(CordonnéeEnnemiArrive().getY() - CoordonnéeTourelleDepart().getX(), 2)) +32);
        this.angleProperty.set(Math.toDegrees(Math.atan2(CordonnéeEnnemiArrive().getY() - CoordonnéeTourelleDepart().getY(), CordonnéeEnnemiArrive().getX() - CoordonnéeTourelleDepart().getX())));

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

    public DoubleProperty angleProperty (){
        return this.angleProperty;
    }

    public String getId(){
        return id;}

}
