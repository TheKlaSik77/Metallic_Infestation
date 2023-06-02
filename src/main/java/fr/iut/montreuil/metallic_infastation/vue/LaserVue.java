package fr.iut.montreuil.metallic_infastation.vue;

import fr.iut.montreuil.metallic_infastation.modele.*;
import javafx.animation.PathTransition;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.io.IOException;
import java.net.URI;


public class LaserVue {
    private Environnement environnement;

    private static Pane zoneAffichageLaser;

    public LaserVue (Environnement environnement, Pane zoneAffichageLaser) {
        this.environnement = environnement;
        this.zoneAffichageLaser = zoneAffichageLaser;
    }

    public static void creerLaser(Laser laser) {
        if (laser.CordonnéeEnnemiArrive() != null) {
            Line line = new Line(laser.CoordonnéeTourelleDepart().getX(), laser.CoordonnéeTourelleDepart().getY()+16, laser.CordonnéeEnnemiArrive().getX(), laser.CordonnéeEnnemiArrive().getY());
            line.setStroke(Color.BLUE);
            line.setStrokeWidth(2);
            zoneAffichageLaser.getChildren().add(line);
        }
    }



    public static void retirerLaser (Laser l) {

    }

}
