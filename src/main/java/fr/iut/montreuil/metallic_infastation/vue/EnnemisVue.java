package fr.iut.montreuil.metallic_infastation.vue;

import fr.iut.montreuil.metallic_infastation.JeuApplication;
import fr.iut.montreuil.metallic_infastation.modele.Ennemi;
import fr.iut.montreuil.metallic_infastation.modele.Environnement;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;

//TODO: EnnemiVue = 1 ennemi
public class EnnemisVue {

    private Environnement env;
    @FXML
    Pane zoneAffichageEnnemis;

    public EnnemisVue(Environnement env, Pane zoneAffichageEnnemis){
        this.env = env;
        this.zoneAffichageEnnemis = zoneAffichageEnnemis;
    }

    public void ajouterEnnemi(Ennemi e){
        URL urlImSoldat = JeuApplication.class.getResource("img/soldat16x16.png");
        Image imSoldat = new Image(String.valueOf(urlImSoldat));
        ImageView ennemi = new ImageView(imSoldat);
        ennemi.translateXProperty().bind(e.getCoordonnees().pXProperty());
        ennemi.translateYProperty().bind(e.getCoordonnees().pYProperty());
        zoneAffichageEnnemis.getChildren().add(ennemi);

    }



}
