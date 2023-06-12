package fr.iut.montreuil.metallic_infestation.controleur;

import fr.iut.montreuil.metallic_infestation.JeuApplication;
import fr.iut.montreuil.metallic_infestation.modele.*;
import fr.iut.montreuil.metallic_infestation.vue.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private Pane zoneAffichageObjets;
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
    private ObstacleVue obstacleVue;

    @FXML
    private ToggleGroup toursGroupe;

    @FXML
    private ImageView imObs1;

    @FXML
    private ImageView imObs2;
    @FXML
    private ToggleButton obs1;

    @FXML
    private ToggleButton obs2;

    @FXML
    private ImageView imTour1;

    @FXML
    private ImageView imTour2;

    @FXML
    private ImageView imTour3;

    @FXML
    private ToggleButton tour1;

    @FXML
    private ToggleButton tour2;

    @FXML
    private ToggleButton tour3;

    @FXML
    private ImageView im1Pv;

    @FXML
    private ImageView im3Pv;

    @FXML
    private ImageView im5Pv;
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
        TourelleVue tourelleVue = new TourelleVue(env,zoneAffichageObjets);
        this.obstacleVue = new ObstacleVue(env,zoneAffichageObjets);

        ProjectileSemiVue projectileSemiVue = new ProjectileSemiVue(env,zoneAffichageEnnemis);
        ProjectileMissileVue projectileMissileVue = new ProjectileMissileVue(env, zoneAffichageEnnemis);
        ExplosionVue  explostionVue = new ExplosionVue(env, zoneAffichageEnnemis);



        this.ennemisVue = new EnnemisVue(env, zoneAffichageEnnemis);

        this.joueur = env.getJoueur();
        Boutique boutique = new Boutique(joueur, env, terrainExperimental);
        this.boutiqueVue = new BoutiqueVue(boutique, toursGroupe, tour1,tour2,tour3, obs1, obs2, prixTour, tilePane, terrainExperimental);
        this.laserVue = new LaserVue(env, zoneAffichageEnnemis);
        joueur.argentProperty().addListener((obs, old, nouv) -> this.ArgentProperty.setText(nouv.toString()));
        joueur.pvJoueurProprerty().addListener((obs, old, nouv) -> this.PvProperty.setText(nouv.toString()));
        gestionnaireVagues = new GestionnaireVagues(env);
        env.getListeEnnemis().addListener((ListChangeListener<Ennemi>) change -> {
            while (change.next()) {
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
        env.getListeObstacles().addListener((ListChangeListener<Obstacle>) change -> {
            while (change.next()) {
                if (change.wasRemoved()) {
                    for (Obstacle removedObstacle : change.getRemoved()) {
                        obstacleVue.retirerObstacle(removedObstacle);
                    }
                }
                if (change.wasAdded()){
                    for (Obstacle addedObstacle : change.getAddedSubList()) {
                        obstacleVue.poserObstacle(addedObstacle);
                    }
                }
            }
        });
        env.getListeProjectiles().addListener((ListChangeListener<Projectile>) change -> {
            while (change.next()) {
                if (change.wasRemoved()) {
                    for (int i = change.getRemoved().size() - 1; i >= 0; i--) {
                        if (change.getRemoved() instanceof ProjectileSemi){
                            projectileSemiVue.retirerProjectile(change.getRemoved().get(i));
                        }
                        else {
                            projectileMissileVue.retirerProjectile(change.getRemoved().get(i));
                        }
                    }
                }
                if (change.wasAdded()) {
                    for (Projectile addedProjectile : change.getAddedSubList()) {
                        if (addedProjectile instanceof ProjectileSemi){
                            projectileSemiVue.ajouterProjectile(addedProjectile);
                        }
                        else {
                            projectileMissileVue.ajouterMissile(addedProjectile);
                        }
                    }
                }
            }
        });
        env.getListeLasers().addListener((ListChangeListener<Laser>) change -> {
            while (change.next()) {
                if (change.wasRemoved()) {
                    for (Laser removedLaser : change.getRemoved()) {
                        laserVue.retirerLaser(removedLaser);
                    }
                }
                if (change.wasAdded()) {
                    for (Laser addedLaser : change.getAddedSubList()) {
                        laserVue.creerLaser(addedLaser);
                    }
                }
            }
        });

        env.getListExplosions().addListener((ListChangeListener<Explosion>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Explosion addedExplosion : change.getAddedSubList()) {
                        explostionVue.explosion(addedExplosion);
                    }
                }
            }
        });

        terrainVue.afficherTerrain();
        ParcoursBFS parcoursBFS = new ParcoursBFS(terrainExperimental);

        parcoursBFS.remplirGrilleBFS();
        gameLoop.play();

        toursGroupe.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            String imgUrl1 = "img/gui/bouton_non_pressé.png";
            String imgUrl2 = "img/gui/bouton_pressé.png";
            URL urlImTour1 = JeuApplication.class.getResource(imgUrl1);
            Image nonPresse = new Image(String.valueOf(urlImTour1));
            URL urlImTour2 = JeuApplication.class.getResource(imgUrl2);
            Image presse = new Image(String.valueOf(urlImTour2));
            if (tour1.isSelected()){
                imTour1.setImage(presse);
                imTour2.setImage(nonPresse);
                imTour3.setImage(nonPresse);
                imObs1.setImage(nonPresse);
                imObs2.setImage(nonPresse);
                prixTour.setText(String.valueOf(10));
            } else if (tour2.isSelected()) {
                imTour1.setImage(nonPresse);
                imTour2.setImage(presse);
                imTour3.setImage(nonPresse);
                imObs1.setImage(nonPresse);
                imObs2.setImage(nonPresse);
                prixTour.setText(String.valueOf(30));
            }
            else if (tour3.isSelected()){
                imTour1.setImage(nonPresse);
                imTour2.setImage(nonPresse);
                imTour3.setImage(presse);
                imObs1.setImage(nonPresse);
                imObs2.setImage(nonPresse);
                prixTour.setText(String.valueOf(50));
            } else if (obs1.isSelected()){
                imTour1.setImage(nonPresse);
                imTour2.setImage(nonPresse);
                imTour3.setImage(nonPresse);
                imObs1.setImage(presse);
                imObs2.setImage(nonPresse);
                prixTour.setText(String.valueOf(10));
            } else {
                imTour1.setImage(nonPresse);
                imTour2.setImage(nonPresse);
                imTour3.setImage(nonPresse);
                imObs1.setImage(nonPresse);
                imObs2.setImage(presse);
                prixTour.setText(String.valueOf(20));
            }
        });

        tilePane.setOnMouseClicked(event -> {

            Case c = new Case((int) event.getY() / terrainExperimental.getTailleCase(), (int) event.getX() / terrainExperimental.getTailleCase());

            if (event.getButton() == MouseButton.PRIMARY){
                if (this.terrainExperimental.emplacementVideSurCase(c)) {
                    boutiqueVue.achatTour(c);
                } else if (this.terrainExperimental.cheminSurCase(c)){
                    boutiqueVue.achatObstacle(c);
                }
            }
        });
        zoneAffichageEnnemis.setOnMouseClicked(event -> {
            Case c = new Case((int) event.getY() / terrainExperimental.getTailleCase(), (int) event.getX() / terrainExperimental.getTailleCase());
            if (event.getButton() == MouseButton.SECONDARY) {
                if (this.terrainExperimental.tourSurCase(c)) {
                    boutique.venteTour(c);
                } else if (this.terrainExperimental.obstacleSurCase(c)){
                    boutique.venteObstacle(c);
                }
            }

        });


    }


    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.030),
                ev -> {
                    if (gestionnaireVagues.estDerniereVague()) {
                        System.out.println("Fini");
                        gameLoop.stop();
                    } else {

                        env.unTour(gestionnaireVagues);
                        for (Obstacle o : this.env.getListeObstacles()){
                            if (o.ennemisSurPics()){
                                obstacleVue.actionnerPics(o);
                            } else {
                                obstacleVue.desactiverPics(o);
                            }
                        }

                    }

                }
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

    public Timeline getGameLoop() {
        return gameLoop;
    }
    @FXML
    void boutonNonPresse1(MouseEvent event) {
        im1Pv.setImage(new Image(String.valueOf(JeuApplication.class.getResource("img/gui/pv_bouton_non_pressé.png"))));
    }

    @FXML
    void boutonNonPresse2(MouseEvent event) {
        im3Pv.setImage(new Image(String.valueOf(JeuApplication.class.getResource("img/gui/pv_bouton_non_pressé.png"))));
    }

    @FXML
    void boutonNonPresse3(MouseEvent event) {
        im5Pv.setImage(new Image(String.valueOf(JeuApplication.class.getResource("img/gui/pv_bouton_non_pressé.png"))));
    }

    @FXML
    void boutonPresse1(MouseEvent event) {
        im1Pv.setImage(new Image(String.valueOf(JeuApplication.class.getResource("img/gui/pv_bouton_pressé.png"))));
    }

    @FXML
    void boutonPresse2(MouseEvent event) {
        im3Pv.setImage(new Image(String.valueOf(JeuApplication.class.getResource("img/gui/pv_bouton_pressé.png"))));
    }

    @FXML
    void boutonPresse3(MouseEvent event) {
        im5Pv.setImage(new Image(String.valueOf(JeuApplication.class.getResource("img/gui/pv_bouton_pressé.png"))));
    }

}