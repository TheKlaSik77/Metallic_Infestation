package fr.iut.montreuil.metallic_infastation.vue;

import fr.iut.montreuil.metallic_infastation.JeuApplication;
import fr.iut.montreuil.metallic_infastation.modele.*;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import java.net.URL;


public class LaserVue {
    private Environnement environnement;

    private static Pane zoneAffichageLaser;

    public LaserVue (Environnement environnement, Pane zoneAffichageLaser) {
        this.environnement = environnement;
        this.zoneAffichageLaser = zoneAffichageLaser;
    }

    /*public static void creerLaser(Laser laser) {
        if (laser.CordonnéeEnnemiArrive() != null) {
            Line line = new Line(laser.CoordonnéeTourelleDepart().getX(), laser.CoordonnéeTourelleDepart().getY()+16, laser.CordonnéeEnnemiArrive().getX()+16, laser.CordonnéeEnnemiArrive().getY()+16);
            line.setStroke(Color.BLUE);
            line.setStrokeWidth(2);
            zoneAffichageLaser.getChildren().add(line);
        }
    }*/

    public static void creerLaser(Laser laser) {
        if (laser.CordonnéeEnnemiArrive() != null) {
            double x1 = laser.CoordonnéeTourelleDepart().getX();
            double y1 = laser.CoordonnéeTourelleDepart().getY();
            double x2 = laser.CordonnéeEnnemiArrive().getX();
            double y2 = laser.CordonnéeEnnemiArrive().getY();

            //double angle = Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));

            URL urlImageLaser = JeuApplication.class.getResource("img/projectiles/lasers/blue_laser.png");
            Image imageLaser = new Image(urlImageLaser.toString());
            ImageView imageViewLaser = new ImageView(imageLaser);
            imageViewLaser.setId(laser.getId());
            imageViewLaser.setX(x1-32);
            imageViewLaser.setY(y1-32);
            //double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
            //imageViewLaser.setFitWidth(distance + 32);
            imageViewLaser.fitWidthProperty().bind(laser.angleProperty());
            imageViewLaser.setFitHeight(48);
            System.out.println("ID du laser vue : " + imageViewLaser.getId());

            //Rotate rotate = new Rotate(angle, x1, y1);
            Rotate rotate = new Rotate();
            rotate.angleProperty().bind(laser.angleProperty());
            imageViewLaser.getTransforms().add(rotate);
            zoneAffichageLaser.getChildren().add(imageViewLaser);
        }
    }


    /*public static void retirerLaser(Laser laser) {
        ObservableList<Node> enfants = zoneAffichageLaser.getChildren();
        enfants.removeIf(node -> node instanceof Line && ((Line) node).getStroke().equals(Color.BLUE));
    }*/

    public static void  retirerLaser(Laser laser) {
        ObservableList<Node> enfants = zoneAffichageLaser.getChildren();

        for (int i = enfants.size()-1; i >= 0; i--) {
            Node enfant = enfants.get(i);

            if (enfant instanceof ImageView) {
                ImageView imageLaser = (ImageView) enfant;
                System.out.println("Image Laser ID: " + imageLaser.getId() + ", Laser ID: " + laser.getId());
                if (imageLaser.lookup("#" + laser.getId()) != null) {
                    enfants.remove(i);
                    System.out.println("Laser removed: " + laser.getId());
                    break;
                }
            }
        }
    }


}
