package fr.iut.montreuil.metallic_infestation.modele.utilitaire;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Point {

    private IntegerProperty pX;
    private IntegerProperty pY;

    public Point(int x, int y) {
        this.pX = new SimpleIntegerProperty(x);
        this.pY = new SimpleIntegerProperty(y);
    }


    public IntegerProperty pXProperty() {
        return pX;
    }
    public int getX(){
        return this.pX.getValue();
    }

    public void setX(double x) {
        this.pX.setValue(x);
    }

    public IntegerProperty pYProperty() {
        return pY;
    }

    public int getY(){
        return this.pY.getValue();
    }

    public void setY(double y) {
        this.pY.setValue(y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + pX.getValue() +
                ", y=" + pY.getValue() +
                '}';
    }
    public boolean equals(Point p){
        return this.getX() == p.getX() && this.getY() == p.getY();
    }
    public Case getCase() {
        return new Case(this.getY() / 32, this.getX() / 32);
    }
}


