package fr.iut.montreuil.metallic_infastation.controleur;

import fr.iut.montreuil.metallic_infastation.modele.*;
import fr.iut.montreuil.metallic_infastation.modele.*;
import fr.iut.montreuil.metallic_infastation.vue.EnnemisVue;
import fr.iut.montreuil.metallic_infastation.vue.TerrainVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    private Environnement env;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initAnimation();
        Terrain terrainExperimental = new Terrain();
        TerrainVue terrainVue = new TerrainVue(terrainExperimental,tilePane);

        ArrayList<Ennemi> listeEnnemis = new ArrayList<>();
        listeEnnemis.add(new EnnemiFacile(terrainExperimental));
        listeEnnemis.add(new EnnemiFacile(terrainExperimental));
        this.env = new Environnement(10,1,0, listeEnnemis);
        EnnemisVue ennemisVue = new EnnemisVue(env,zoneAffichageEnnemis);
        ennemisVue.ajouterEnnemi(listeEnnemis.get(0));
        ennemisVue.ajouterEnnemi(listeEnnemis.get(1));





        terrainVue.afficherTerrain();
        ParcoursBFS parcoursBFS = new ParcoursBFS(terrainExperimental);
        parcoursBFS.remplirGrilleBFS();
        parcoursBFS.afficherParcours();
        gameLoop.play();

    }


    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    // TODO: Fini quand plus de points de vie ou vagues 15.
                    if(temps==1000000){
                        System.out.println("fini");
                        gameLoop.stop();
                    }
                    else if (temps%2==0){
                        for (Ennemi e : env.getListeEnnemis()){
                            e.seDeplacer();
                        }
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

}
