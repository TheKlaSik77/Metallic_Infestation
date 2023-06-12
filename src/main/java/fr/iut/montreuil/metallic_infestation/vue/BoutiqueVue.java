package fr.iut.montreuil.metallic_infestation.vue;


import fr.iut.montreuil.metallic_infestation.JeuApplication;
import fr.iut.montreuil.metallic_infestation.modele.*;
import javafx.fxml.FXML;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Label;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.net.URL;

public class BoutiqueVue {

    @FXML
    private ImageView imageTour1;

    @FXML
    private ImageView imageTour2;

    @FXML
    private ImageView imageTour3;
    @FXML
    private ToggleGroup groupeRadio;
    @FXML
    private ToggleButton tour1;

    @FXML
    private ToggleButton tour2;

    @FXML
    private ToggleButton tour3;
    @FXML
    private ToggleButton obs1;
    @FXML
    private ToggleButton obs2;
    private Boutique boutique;
    private Label prixTour;
    private TilePane tilePane;

    private Terrain terrain;


    public BoutiqueVue (Boutique boutique, ToggleGroup groupeRadio, ToggleButton tour1, ToggleButton tour2, ToggleButton tour3, ToggleButton obs1, ToggleButton obs2, Label prixTour, TilePane tilePane, Terrain terrain){
        this.boutique = boutique;
        this.groupeRadio = groupeRadio;
        this.tour1 = tour1;
        this.tour2 = tour2;
        this.tour3 = tour3;
        this.obs1 = obs1;
        this.obs2 = obs2;
        this.prixTour = prixTour;
        this.tilePane = tilePane;
        this.terrain = terrain;
    }

    public void achatUnPv() {
        boutique.AchatPv(100,1);
    }

    public void achatTroisPv() {
        boutique.AchatPv(200,3);
    }

    public void achatCinqPv() {
        boutique.AchatPv(300,5);
    }

    public void achatTour(Case c){
        if (tour1.isSelected()){
            boutique.achatTour(1, c);
        }
        else if (tour2.isSelected()) {
            boutique.achatTour(2,c);
        }
        else {
            boutique.achatTour(3,c);
        }
    }

    public void achatObstacle(Case c) {
        if (obs1.isSelected()){
            boutique.achatObstacle(1,c);
        } else if (obs2.isSelected()){
            boutique.achatObstacle(2,c);
        }
    }

    public void chargerImageTours() {
        String imageUrlt1 = "img/tourelle/tourelleSemiEteinte.png";
        URL urlImTourelleSemi = JeuApplication.class.getResource(imageUrlt1);
        Image imageTour1 = new Image(String.valueOf(urlImTourelleSemi));
        ImageView tour1 = new ImageView(imageTour1);
        this.imageTour1.setImage(tour1.getImage());

        String imageUrlt2 = "img/tourelle/tourelleAutoEteinte.png";
        URL urlImTourelleAuto = JeuApplication.class.getResource(imageUrlt2);
        Image imageTour2 = new Image(String.valueOf(urlImTourelleAuto));
        ImageView tour2 = new ImageView(imageTour2);
        this.imageTour2.setImage(tour2.getImage());

        String imageUrlt3 = "img/tourelle/tourelleMissiles.png";
        URL urlImTourelleMissile = JeuApplication.class.getResource(imageUrlt3);
        Image imageTour3 = new Image(String.valueOf(urlImTourelleMissile));
        ImageView tour3 = new ImageView(imageTour3);
        this.imageTour3.setImage(tour3.getImage());

    }


}
