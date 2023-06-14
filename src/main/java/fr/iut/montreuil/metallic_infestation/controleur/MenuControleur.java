package fr.iut.montreuil.metallic_infestation.controleur;

import fr.iut.montreuil.metallic_infestation.JeuApplication;
import fr.iut.montreuil.metallic_infestation.modele.LiaisonEntreLeMenuEtLeJeu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MenuControleur implements Initializable {

    private JeuApplication jeuApplication;
    private Stage jeuMap, stageActuel;



    @FXML
    void demarreMAP1(ActionEvent event) {
        LiaisonEntreLeMenuEtLeJeu.nbTerrain = 1;
        try {
            jeuApplication.start(jeuMap);
            stageActuel = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageActuel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void demarreMAP2(ActionEvent event) {
        LiaisonEntreLeMenuEtLeJeu.nbTerrain = 2;
        try {
            jeuApplication.start(jeuMap);
            stageActuel = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageActuel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void demarreMAP3(ActionEvent event) {
        LiaisonEntreLeMenuEtLeJeu.nbTerrain = 3;
        try {
            jeuApplication.start(jeuMap);
            stageActuel = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageActuel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.jeuApplication = new JeuApplication();
        this.jeuMap = new Stage();

    }
}
