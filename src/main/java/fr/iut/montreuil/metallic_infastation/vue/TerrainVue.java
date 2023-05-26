package fr.iut.montreuil.metallic_infastation.vue;

import fr.iut.montreuil.metallic_infastation.JeuApplication;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import fr.iut.montreuil.metallic_infastation.modele.Terrain;

import java.net.URL;
import java.util.Random;

public class TerrainVue {

    private Terrain terrain;
    private TilePane tilePane;

    public TerrainVue(Terrain terrain, TilePane tilePane){
        this.terrain = terrain;
        this.tilePane = tilePane;
    }

    public void afficherTerrain(){
        URL urlImHerbe = JeuApplication.class.getResource("img/horsTerrain16x16.png");
        Image imHerbe= new Image(String.valueOf(urlImHerbe));
        URL urlImHerbe2 = JeuApplication.class.getResource("img/horsTerrain(2)16x16.png");
        Image imHerbe2= new Image(String.valueOf(urlImHerbe2));

        URL urlImChemin = JeuApplication.class.getResource("img/chemin16x16.png");
        Image imChemin = new Image(String.valueOf(urlImChemin));

        URL urlImArrivee = JeuApplication.class.getResource("img/arrivee16x16.png");
        Image imArrivee = new Image(String.valueOf(urlImArrivee));

        URL urlImTour = JeuApplication.class.getResource("img/tour16x16.png");
        Image imTour = new Image(String.valueOf(urlImTour));

        for (int i = 0 ; i < terrain.getTerrain().length ; i++){
            for (int j = 0 ; j < terrain.getTerrain()[i].length ; j++) {
                switch (terrain.getTerrain()[i][j]) {
                    case 0:
                        double rand = Math.random();
                        if (rand < 0.02){
                            ImageView imageView = new ImageView(imHerbe2);
                            tilePane.getChildren().add(imageView);
                            break;
                        } else {
                            ImageView imageView = new ImageView(imHerbe);
                            tilePane.getChildren().add(imageView);
                            break;
                        }

                    case 1:
                        ImageView imageView2 = new ImageView(imChemin);
                        tilePane.getChildren().add(imageView2);
                        break;
                    case 2:
                        ImageView imageView3 = new ImageView(imArrivee);
                        tilePane.getChildren().add(imageView3);
                        break;
                    case 3:
                        ImageView imageView4 = new ImageView(imTour);
                        tilePane.getChildren().add(imageView4);

                }
            }

        }

    }
}
