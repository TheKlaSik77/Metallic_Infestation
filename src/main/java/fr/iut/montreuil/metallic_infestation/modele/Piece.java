package fr.iut.montreuil.metallic_infestation.modele;

import javafx.beans.property.IntegerProperty;
import javafx.scene.shape.Circle;

public class Piece {

    private IntegerProperty x;
    private IntegerProperty y;

    public Piece(IntegerProperty x, IntegerProperty y) {
        this.x = x;
        this.y = y;
    }

    public final int getX() {
        return x.getValue();
    }
    public final void setX(int n){
        x.setValue(n);
    }
    public final IntegerProperty xProperty() {
        return x;
    }

    public final int getY() {
        return y.getValue();
    }
    public final void setY(int n){
        y.setValue(n);
    }
    public final IntegerProperty yProperty() {
        return y;
    }
}
