package fr.iut.montreuil.metallic_infastation.vue;

import fr.iut.montreuil.metallic_infastation.JeuApplication;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import fr.iut.montreuil.metallic_infastation.modele.Terrain;

import java.net.URL;

public class TerrainVue {

    private Terrain terrain;
    private TilePane tilePane;

    public TerrainVue(Terrain terrain, TilePane tilePane){
        this.terrain = terrain;
        this.tilePane = tilePane;
    }

    public void afficherTerrain(){
        URL urlImHerbe = JeuApplication.class.getResource("img/herbe16x16.png");
        Image imHerbe= new Image(String.valueOf(urlImHerbe));

        URL urlImChemin = JeuApplication.class.getResource("img/terre16x16.png");
        Image imChemin = new Image(String.valueOf(urlImChemin));

        URL urlImArrivee = JeuApplication.class.getResource("img/arrivee16x16.png");
        Image imArrivee = new Image(String.valueOf(urlImArrivee));

        URL urlImSoldat = JeuApplication.class.getResource("img/soldat16x16.png");
        Image imSoldat = new Image(String.valueOf(urlImSoldat));

        for (int i = 0 ; i < terrain.getTerrain().length ; i++){
            for (int j = 0 ; j < terrain.getTerrain()[i].length ; j++) {
                switch (terrain.getTerrain()[i][j]) {
                    case 0:
                        ImageView imageView = new ImageView(imHerbe);
                        tilePane.getChildren().add(imageView);
                        break;
                    case 1:
                        ImageView imageView2 = new ImageView(imChemin);
                        tilePane.getChildren().add(imageView2);
                        break;
                    case 2:
                        ImageView imageView3 = new ImageView(imArrivee);
                        tilePane.getChildren().add(imageView3);
                        break;
                    case 3:
                        ImageView imageView4 = new ImageView(imSoldat);
                        tilePane.getChildren().add(imageView4);
                        break;

                }
            }

        }

    }
}
