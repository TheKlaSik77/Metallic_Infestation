package fr.iut.montreuil.metallic_infestation.vue;

import fr.iut.montreuil.metallic_infestation.modele.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;


public class BoutiqueVue {
    @FXML
    private ToggleGroup groupeRadio;
    @FXML
    private ToggleButton tour1;

    @FXML
    private ToggleButton tour2;

    @FXML
    private ToggleButton tour3;

    private Boutique boutique;
    private Label prixTour;
    private TilePane tilePane;

    private Terrain terrain;


    public BoutiqueVue (Boutique boutique, ToggleGroup groupeRadio, ToggleButton tour1, ToggleButton tour2, ToggleButton tour3, Label prixTour, TilePane tilePane, Terrain terrain){
        this.boutique = boutique;
        this.groupeRadio = groupeRadio;
        this.tour1 = tour1;
        this.tour2 = tour2;
        this.tour3 = tour3;
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
        else{
            boutique.achatTour(3,c);
        }
    }




}
