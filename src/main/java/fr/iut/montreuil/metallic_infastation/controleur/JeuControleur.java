package fr.iut.montreuil.metallic_infastation.controleur;

import fr.iut.montreuil.metallic_infastation.modele.*;
import fr.iut.montreuil.metallic_infastation.vue.BoutiqueVue;
import fr.iut.montreuil.metallic_infastation.vue.EnnemisVue;
import fr.iut.montreuil.metallic_infastation.vue.TerrainVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.net.URL;
import java.util.ResourceBundle;

public class JeuControleur implements Initializable {

    final static int NB_VAGUES_JEU = 3;

    @FXML
    private TilePane tilePane;
    @FXML
    private Pane zoneAffichageEnnemis;
    private int temps;
    @FXML
    private Label ArgentProperty;
    @FXML
    private Label PvProperty;

    @FXML
    private ToggleGroup groupeRadio;
    @FXML
    private RadioButton tour1;

    @FXML
    private RadioButton tour2;

    @FXML
    private RadioButton tour3;

    private Environnement env;
    private Joueur joueur;
    private BoutiqueVue boutiqueVue;
    private Timeline gameLoop;
    private EnnemisVue ennemisVue;
    private int vagueActuelle;
    private Terrain terrainExperimental;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initAnimation();
        terrainExperimental = new Terrain();
        TerrainVue terrainVue = new TerrainVue(terrainExperimental, tilePane);
        this.env = new Environnement(terrainExperimental);
        TourelleSemi tourelleSemi = new TourelleSemi(new Case(8, 10), env, terrainExperimental);
        tourelleSemi.poserTourelle();
        this.ennemisVue = new EnnemisVue(env, zoneAffichageEnnemis);
        this.joueur = new Joueur(150, 3500);
        Boutique boutique = new Boutique(joueur, env);
        this.boutiqueVue = new BoutiqueVue(boutique, groupeRadio, tour1, tour2, tour3);
        joueur.argentProperty().addListener((obs, old, nouv) -> this.ArgentProperty.setText(nouv.toString()));
        joueur.pvJoueurProprerty().addListener((obs, old, nouv) -> this.PvProperty.setText(nouv.toString()));


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


        terrainVue.afficherTerrain();
        ParcoursBFS parcoursBFS = new ParcoursBFS(terrainExperimental);
        parcoursBFS.remplirGrilleBFS();
        //parcoursBFS.afficherParcours();
        gameLoop.play();
    }


    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                ev -> {
                    if (vagueActuelle == NB_VAGUES_JEU ) {
                        System.out.println("fini");
                        gameLoop.stop();
                    } else {
                        if (env.getListeEnnemis().isEmpty()) {
                            vagueActuelle++;
                            if(vagueActuelle < NB_VAGUES_JEU){
                                System.out.println("Une vague ennemi se prépare...");
                                System.out.println("Vague actuelle : " + vagueActuelle);
                                env.lancerVague(terrainExperimental);
                            }
                        }
                        for (int idEnnemi = env.getListeEnnemis().size() - 1; idEnnemi >= 0; idEnnemi--) {
                            Ennemi e = env.getListeEnnemis().get(idEnnemi);
                            e.seDeplacer();
                            if (e.aAtteintLaCible() || e.estMort()) {
                                env.getListeEnnemis().remove(e);
                            }
                        }
                        for (Tourelle t : env.getListeTourelles()) {
                            t.raffraichirEnnemiVise();

                        }
                        if (temps % 20 == 0) {
                            for (Tourelle t : env.getListeTourelles()) {
                                if (t instanceof TourelleSemi) {
                                    t.infligerDegats();
                                    System.out.println(t.getEnnemiVise());
                                    System.out.println("tire");
                                }
                            }
                        }

                    }
                    temps++;
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
    void achatTour(ActionEvent event) {
        System.out.println("ajour");
        boutiqueVue.achatTour();
    }

}
