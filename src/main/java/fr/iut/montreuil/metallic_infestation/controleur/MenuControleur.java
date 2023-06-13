package fr.iut.montreuil.metallic_infestation.controleur;

import fr.iut.montreuil.metallic_infestation.JeuApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MenuControleur implements Initializable {



    @FXML
    private ImageView imMap1;
    @FXML
    private ImageView imMap2;

    @FXML
    private ImageView imMap3;

    @FXML
    void demarreMAP1(ActionEvent event) {
        JeuApplication jeuApp = new JeuApplication();
        Stage nouvelleStage = new Stage();
        try {
            jeuApp.start(nouvelleStage);
            Stage stageActuel = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageActuel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void demarreMAP2(ActionEvent event) {

    }

    @FXML
    void demarreMAP3(ActionEvent event) {

    }

    @FXML
    void boutonNonPresseMap1(MouseEvent event) {
        imMap1.setImage(new Image(String.valueOf(JeuApplication.class.getResource("img/gui/pv_bouton_non_pressé.png"))));
    }

    @FXML
    void boutonPresseMap1(MouseEvent event) {
        imMap1.setImage(new Image(String.valueOf(JeuApplication.class.getResource("img/gui/pv_bouton_pressé.png"))));
    }
    @FXML
    void boutonNonPresseMap2(MouseEvent event) {
        imMap2.setImage(new Image(String.valueOf(JeuApplication.class.getResource("img/gui/pv_bouton_non_pressé.png"))));
    }

    @FXML
    void boutonPresseMap2(MouseEvent event) {
        imMap2.setImage(new Image(String.valueOf(JeuApplication.class.getResource("img/gui/pv_bouton_pressé.png"))));
    }

    @FXML
    void boutonNonPresseMap3(MouseEvent event) {
        imMap3.setImage(new Image(String.valueOf(JeuApplication.class.getResource("img/gui/pv_bouton_non_pressé.png"))));
    }

    @FXML
    void boutonPresseMap3(MouseEvent event) {
        imMap3.setImage(new Image(String.valueOf(JeuApplication.class.getResource("img/gui/pv_bouton_pressé.png"))));
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
