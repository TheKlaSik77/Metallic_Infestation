package fr.iut.montreuil.metallic_infastation.controleur;

import fr.iut.montreuil.metallic_infastation.modele.*;
import fr.iut.montreuil.metallic_infastation.modele.*;
import fr.iut.montreuil.metallic_infastation.vue.BoutiqueVue;
import fr.iut.montreuil.metallic_infastation.vue.EnnemisVue;
import fr.iut.montreuil.metallic_infastation.vue.TerrainVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class JeuControleur implements Initializable {

    @FXML
    private TilePane tilePane;
    @FXML
    private Pane zoneAffichageEnnemis;
    private Timeline gameLoop;
    private int temps;


    @FXML
    private Label ArgentProperty;

    @FXML
    private Label PvProperty;

    @FXML
    private Label prixTour;

    private Environnement env;
    private Joueur joueur;
    private BoutiqueVue boutiqueVue;

    @FXML
    private ToggleGroup groupeRadio;
    @FXML
    private RadioButton tour1;

    @FXML
    private RadioButton tour2;

    @FXML
    private RadioButton tour3;
    private EnnemisVue ennemisVue;

    private Terrain terrainExperimental = new Terrain();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initAnimation();

        TerrainVue terrainVue = new TerrainVue(terrainExperimental, tilePane);
        this.env = new Environnement(terrainExperimental);
        this.ennemisVue = new EnnemisVue(env, zoneAffichageEnnemis);
        this.joueur = new Joueur(150,3500);
        Boutique boutique = new Boutique(joueur, env, terrainExperimental);
        this.boutiqueVue = new BoutiqueVue(boutique, groupeRadio, tour1,tour2,tour3, prixTour, tilePane, terrainExperimental);
        joueur.argentProperty().addListener((obs, old, nouv) -> this.ArgentProperty.setText(nouv.toString()));
        joueur.pvJoueurProprerty().addListener((obs, old, nouv) -> this.PvProperty.setText(nouv.toString()));
        env.getListeEnnemis().addListener((ListChangeListener<Ennemi>) change -> {
            while (change.next()) {
                System.out.println("ajouts : " + change.getAddedSubList());
                System.out.println("supressions : " + change.getRemoved());
                System.out.println("liste des ennemis: " + change.getList());
                System.out.println("nombre d'ennemis : " + change.getList().size());
                if (change.wasRemoved()) {
                    for (Ennemi removedEnnemi : change.getRemoved()) {
                        ennemisVue.supprimerEnnemi(removedEnnemi);
                    }
                }
                if (change.wasAdded()) {
                    for (Ennemi addedEnnemi : change.getAddedSubList()) {
                        ennemisVue.ajouterEnnemi(addedEnnemi);
                    }
                }
            }
        });
        env.lancerVague(terrainExperimental);


        terrainVue.afficherTerrain();
        ParcoursBFS parcoursBFS = new ParcoursBFS(terrainExperimental);
        parcoursBFS.remplirGrilleBFS();
        parcoursBFS.afficherParcours();
        gameLoop.play();

        groupeRadio.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (tour1.isSelected()){
                prixTour.setText(String.valueOf(1));
            } else if (tour2.isSelected()) {
                prixTour.setText(String.valueOf(2));
            }
            else {
                prixTour.setText(String.valueOf(3));
            }

        });

        tilePane.setOnMouseClicked(event -> {
            Case c = new Case((int) event.getX() / terrainExperimental.getTailleCase(), (int) event.getY() / terrainExperimental.getTailleCase());
            if (this.terrainExperimental.videSurCase(c)){
                boutiqueVue.achatTour(c);
            } else if (this.terrainExperimental.tourSurCase(c)) {
                boutiqueVue.venteTour(c);
            }
        });
    }


    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev -> {
                    // TODO: Fini quand plus de points de vie ou vagues 15.

                    if (temps == 1000000) {
                        System.out.println("fini");
                        gameLoop.stop();
                    } else if (temps % 2 == 0) {
                        
           
                        for (int idEnnemi = env.getListeEnnemis().size() - 1; idEnnemi >= 0; idEnnemi--) {
                            Ennemi e = env.getListeEnnemis().get(idEnnemi);
                            e.seDeplacer();
                            if (e.aAtteintLaCible() || e.estMort()) {
                                env.getListeEnnemis().remove(e);
                            }
                        }

                        for (Tourelle t : env.getListeTourelles()){
                            t.raffraichirEnnemiVise();

                        }
                    }
                    if (temps % 50 == 0){
                        for (Tourelle t : env.getListeTourelles()){
                            if (t instanceof TourelleSemi){
                                t.infligerDegats();
                                System.out.println(t.getEnnemiVise());
                                System.out.println("tire");
                            }
                        }

                    }
                    temps++;
                }

                )
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public void achatUnPv(ActionEvent actionEvent) {
        boutiqueVue.achatUnPv();
    }

    public void achatTroisPv(ActionEvent actionEvent) {
        boutiqueVue.achatTroisPv();
    }

    public void achatCinqPv(ActionEvent actionEvent) {
        boutiqueVue.achatCinqPv();
    }
}
