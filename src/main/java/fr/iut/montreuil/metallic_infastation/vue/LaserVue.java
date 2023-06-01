package fr.iut.montreuil.metallic_infastation.vue;

import fr.iut.montreuil.metallic_infastation.modele.Case;
import fr.iut.montreuil.metallic_infastation.modele.Environnement;
import fr.iut.montreuil.metallic_infastation.modele.Point;
import javafx.animation.PathTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.io.IOException;
import java.net.URI;


public class LaserVue {
    private Environnement environnement;

    private Pane zoneAffichageLaser;

    public LaserVue (Environnement environnement, Pane zoneAffichageLaser) {
        this.environnement = environnement;
        this.zoneAffichageLaser = zoneAffichageLaser;
    }

    public void creerLaser (Point departTourelle, Point arrivéeEnnemi){
        PathTransition laserTransition = new PathTransition();
        laserTransition.setNode(createCircle(0, 0, 5, Color.GREEN));
        //Image laserImage = new Image("laser.png");
        //ImageView imageView = new ImageView(laserImage);
        Path path = new Path();
        path.getElements().addAll(new MoveTo(departTourelle.getX(),departTourelle.getY()), new LineTo(arrivéeEnnemi.getX(), arrivéeEnnemi.getY()));
        laserTransition.setPath(path);

        laserTransition.play();
    }

    private Circle createCircle(double centerX, double centerY, double radius, Color color) {
        Circle circle = new Circle(centerX, centerY, radius);
        circle.setFill(color);
        return circle;
    }
}
