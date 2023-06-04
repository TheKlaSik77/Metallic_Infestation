package fr.iut.montreuil.metallic_infastation.controleur;

import fr.iut.montreuil.metallic_infastation.modele.*;
import fr.iut.montreuil.metallic_infastation.vue.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


public class JeuControleur implements Initializable {

    final static int NB_VAGUES_JEU = 3;

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
    private int vagueActuelle;
    private Terrain terrainExperimental;

    private GestionnaireVagues gestionnaireVagues;

    private boolean vagueTerminee = true;

    private LaserVue laserVue;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initAnimation();
        this.terrainExperimental = new Terrain();
        TerrainVue terrainVue = new TerrainVue(terrainExperimental, tilePane);
        this.env = new Environnement(terrainExperimental);
        TourelleVue tourelleVue = new TourelleVue(env,zoneAffichageEnnemis);

        this.ennemisVue = new EnnemisVue(env, zoneAffichageEnnemis);
        this.joueur = env.getJoueur();
        Boutique boutique = new Boutique(joueur, env, terrainExperimental);
        this.boutiqueVue = new BoutiqueVue(boutique, groupeRadio, tour1,tour2,tour3, prixTour, tilePane, terrainExperimental);

        this.laserVue = new LaserVue(env, zoneAffichageEnnemis);

        joueur.argentProperty().addListener((obs, old, nouv) -> this.ArgentProperty.setText(nouv.toString()));
        joueur.pvJoueurProprerty().addListener((obs, old, nouv) -> this.PvProperty.setText(nouv.toString()));
        gestionnaireVagues = new GestionnaireVagues(env);
        env.getListeEnnemis().addListener((ListChangeListener<Ennemi>) change -> {
            while (change.next()) {
                /*
                System.out.println("ajouts : " + change.getAddedSubList());
                System.out.println("supressions : " + change.getRemoved());
                 */
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
        env.getListeTourelles().addListener((ListChangeListener<Tourelle>) change -> {
            while (change.next()) {
                System.out.println("ajouts : " + change.getAddedSubList());
                System.out.println("supressions : " + change.getRemoved());
                System.out.println("liste des tours: " + change.getList());
                System.out.println("nombre de tours : " + change.getList().size());
                if (change.wasRemoved()) {
                    for (Tourelle removedTourelle : change.getRemoved()) {
                        tourelleVue.retirerTour(removedTourelle);
                    }
                }
                if (change.wasAdded()) {
                    for (Tourelle addedTourelle : change.getAddedSubList()) {
                        tourelleVue.poserTour(addedTourelle);
                    }
                }
            }
        });

        env.getListeLasers().addListener((ListChangeListener<Laser>) change -> {
            while (change.next()) {
                if (change.wasRemoved()) {
                    for (Laser removedLaser : change.getRemoved()) {
                        LaserVue.retirerLaser(removedLaser);
                    }
                }
                if (change.wasAdded()) {
                    for (Laser addedLaser : change.getAddedSubList()) {
                        LaserVue.creerLaser(addedLaser);
                    }
                }
            }
        });

        env.lancerVague(terrainExperimental);


        terrainVue.afficherTerrain();
        ParcoursBFS parcoursBFS = new ParcoursBFS(terrainExperimental);

        parcoursBFS.remplirGrilleBFS();
        //parcoursBFS.afficherParcours();
        gameLoop.play();

        groupeRadio.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (tour1.isSelected()){
                prixTour.setText(String.valueOf(10));
            } else if (tour2.isSelected()) {
                prixTour.setText(String.valueOf(30));
            }
            else {
                prixTour.setText(String.valueOf(50));
            }

        });

        tilePane.setOnMouseClicked(event -> {

            Case c = new Case((int) event.getY() / terrainExperimental.getTailleCase(), (int) event.getX() / terrainExperimental.getTailleCase());

            if (event.getButton() == MouseButton.PRIMARY && this.terrainExperimental.emplacementVideSurCase(c)) {
                boutiqueVue.achatTour(c);
                System.out.println("tour posée");
                terrainExperimental.afficherTab();
            }
        });
        zoneAffichageEnnemis.setOnMouseClicked(event -> {
            Case c = new Case((int) event.getY() / terrainExperimental.getTailleCase(), (int) event.getX() / terrainExperimental.getTailleCase());
            if (event.getButton() == MouseButton.SECONDARY && this.terrainExperimental.tourSurCase(c)) {
                System.out.println("clic droit");
                boutique.venteTour(c);
                System.out.println("tour vendue");
            }

        });
    }


    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                ev -> {
                    if (vagueActuelle == NB_VAGUES_JEU  || joueur.pvNull()) {
                        System.out.println("fini");
                        gameLoop.stop();
                    } else {
                        if (env.getListeEnnemis().isEmpty()) {
                            gestionnaireVagues.lancerProchaineVague(terrainExperimental);
                        }

                        for (int idEnnemi = env.getListeEnnemis().size() - 1; idEnnemi >= 0; idEnnemi--) {
                            Ennemi e = env.getListeEnnemis().get(idEnnemi);
                            e.seDeplacer();
                            env.retirerLaser(e);
                            if (e.aAtteintLaCible() || e.estMort()) {
                                env.getListeEnnemis().remove(e);
                                joueur.debiterPvJoueurProperty(e.getDrop());
                            }
                        }
                        for (Tourelle t : env.getListeTourelles()) {
                            t.raffraichirEnnemiVise();
                            if (t instanceof TourelleAuto){
                                Laser l = ((TourelleAuto) t).creerLaser();
                                    env.ajouterLaser(((TourelleAuto) t).creerLaser());
                                }


                        }
                    }

                    for (Tourelle t : env.getListeTourelles()){
                        if (t instanceof TourelleSemi){
                            if (temps % 20 == 0){
                                t.infligerDegats();
                            }
                        }
                        else if (t instanceof TourelleAuto) {
                            t.infligerDegats();
                        }
                        else {
                            if (temps % 50 == 0)
                            t.infligerDegats();
                        }
                    }
                    temps++;
                }
        );
        gameLoop.getKeyFrames().

                add(kf);

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

    public Timeline getGameLoop() {
        return gameLoop;
    }

}
