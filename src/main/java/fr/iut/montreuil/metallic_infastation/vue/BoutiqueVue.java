package fr.iut.montreuil.metallic_infastation.vue;

import fr.iut.montreuil.metallic_infastation.controleur.JeuControleur;
import fr.iut.montreuil.metallic_infastation.modele.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.TilePane;

import java.util.concurrent.atomic.AtomicBoolean;


public class BoutiqueVue {
    @FXML
    private ToggleGroup groupeRadio;
    @FXML
    private RadioButton tour1;

    @FXML
    private RadioButton tour2;

    @FXML
    private RadioButton tour3;

    private Boutique boutique;
    private Label prixTour;
    private TilePane tilePane;

    private Terrain terrain;


    public BoutiqueVue (Boutique boutique, ToggleGroup groupeRadio, RadioButton tour1, RadioButton tour2, RadioButton tour3, Label prixTour, TilePane tilePane, Terrain terrain){
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
        boutique.AchatPv(10,1);
    }

    public void achatTroisPv() {
        boutique.AchatPv(30,3);
    }

    public void achatCinqPv() {
        boutique.AchatPv(50,5);
    }

    public void achatTour(Case c){
        if (tour1.isSelected()){

            boutique.achatTour(1, c);
        }
        else if (tour2.isSelected()) {
            System.out.println("Tour2");
            boutique.achatTour(2,c);
        }
        else{
            System.out.println("Tour3");
            boutique.achatTour(3,c);
        }
    }


}
