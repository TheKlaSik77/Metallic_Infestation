package fr.iut.montreuil.metallic_infestation.vue;

import fr.iut.montreuil.metallic_infestation.JeuApplication;
import fr.iut.montreuil.metallic_infestation.modele.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.Projectile;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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
        String imageUrl = "img/missile/projectile_rocket.png";


        URL urlImProjectile = JeuApplication.class.getResource(imageUrl);
        Image image = new Image(String.valueOf(urlImProjectile));
        ImageView projectile = new ImageView(image);


    }
}
