package fr.iut.montreuil.metallic_infastation.vue;

import fr.iut.montreuil.metallic_infastation.JeuApplication;
import fr.iut.montreuil.metallic_infastation.modele.Environnement;
import fr.iut.montreuil.metallic_infastation.modele.Projectile;
import fr.iut.montreuil.metallic_infastation.modele.ProjectileMissile;
import fr.iut.montreuil.metallic_infastation.modele.TourelleAuto;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

import java.net.URL;

public class ProjectileMissileVue {
    private Environnement environnement;

    @FXML
    private Pane zoneAffichageProjectiles;

    public ProjectileMissileVue (Environnement environnement, Pane zoneAffichageProjectiles){
        this.environnement = environnement;
        this.zoneAffichageProjectiles = zoneAffichageProjectiles;
    }

    public void ajouterMissile(Projectile p){
        String imageUrl = "img/projectiles/missile/projectile_rocket.png";

        URL urlImProjectile = JeuApplication.class.getResource(imageUrl);
        Image image = new Image(String.valueOf(urlImProjectile));
        ImageView projectile = new ImageView(image);


        projectile.translateXProperty().bind(p.getCoordonnees().pXProperty());
        projectile.translateYProperty().bind(p.getCoordonnees().pYProperty());
        projectile.setId(String.valueOf(p.getId()));


        projectile.rotateProperty().bind(((ProjectileMissile) p).AngleProperty());
        zoneAffichageProjectiles.getChildren().add(projectile);
    }

    public void retirerProjectile(Projectile p) {
        System.out.println("supprimé");
        System.out.println(p.getId());
        System.out.println(zoneAffichageProjectiles.lookup("#" + p.getId()));
        zoneAffichageProjectiles.getChildren().remove(zoneAffichageProjectiles.lookup("#" + p.getId()));
    }
}
