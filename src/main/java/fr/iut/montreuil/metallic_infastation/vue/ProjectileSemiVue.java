package fr.iut.montreuil.metallic_infastation.vue;

import fr.iut.montreuil.metallic_infastation.JeuApplication;
import fr.iut.montreuil.metallic_infastation.modele.EnnemiFacile;
import fr.iut.montreuil.metallic_infastation.modele.Environnement;
import fr.iut.montreuil.metallic_infastation.modele.Projectile;
import fr.iut.montreuil.metallic_infastation.modele.ProjectileSemi;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;

public class ProjectileSemiVue {

    private Environnement env;
    @FXML
    private Pane zoneAffichageProjectiles;

    public ProjectileSemiVue(Environnement env, Pane zoneAffichageProjectiles){
        this.env = env;
        this.zoneAffichageProjectiles = zoneAffichageProjectiles;
    }

    public void ajouterProjectile(Projectile p){
        String imageUrl = "img/projectiles/projectileSemiAuto.png";
        URL urlImProjectile = JeuApplication.class.getResource(imageUrl);
        Image image = new Image(String.valueOf(urlImProjectile));
        ImageView projectile = new ImageView(image);
        projectile.translateXProperty().bind(p.getCoordonnees().pXProperty());
        projectile.translateYProperty().bind(p.getCoordonnees().pYProperty());
        zoneAffichageProjectiles.getChildren().add(projectile);


    }

    public void retirerProjectile(Projectile p) {
        ObservableList<Node> enfants = zoneAffichageProjectiles.getChildren();
        for (Node enfant : enfants) {
            if (enfant instanceof ImageView) {
                ImageView projectile = (ImageView) enfant;
                if (projectile.getTranslateX() == p.getCoordonnees().getX()
                        && projectile.getTranslateY() == p.getCoordonnees().getY()) {
                    enfants.remove(projectile);
                    break;
                }
            }
        }
    }
}
