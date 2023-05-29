package fr.iut.montreuil.metallic_infastation.vue;

import fr.iut.montreuil.metallic_infastation.JeuApplication;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
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
        URL urlImHerbe = JeuApplication.class.getResource("img/terrain/horsZone(1).png");
        Image imHerbe = new Image(String.valueOf(urlImHerbe));
        URL urlImHerbe2 = JeuApplication.class.getResource("img/terrain/horsZone(2).png");
        Image imHerbe2 = new Image(String.valueOf(urlImHerbe2));
        URL urlImHerbe3 = JeuApplication.class.getResource("img/terrain/horsZone(3).png");
        Image imHerbe3 = new Image(String.valueOf(urlImHerbe3));
        URL urlImHerbe4 = JeuApplication.class.getResource("img/terrain/horsZone(4).png");
        Image imHerbe4 = new Image(String.valueOf(urlImHerbe4));
        URL urlImHerbe5 = JeuApplication.class.getResource("img/terrain/horsZone(5).png");
        Image imHerbe5 = new Image(String.valueOf(urlImHerbe5));
        URL urlImHerbe6 = JeuApplication.class.getResource("img/terrain/horsZone(6).png");
        Image imHerbe6 = new Image(String.valueOf(urlImHerbe6));
        URL urlImHerbe7 = JeuApplication.class.getResource("img/terrain/horsZone(7).png");
        Image imHerbe7 = new Image(String.valueOf(urlImHerbe7));
        URL urlmBaril = JeuApplication.class.getResource("img/terrain/baril.png");
        Image imBaril = new Image(String.valueOf(urlmBaril));


        URL urlImChemin = JeuApplication.class.getResource("img/terrain/chemin.png");
        Image imChemin = new Image(String.valueOf(urlImChemin));

        URL urlImContourChemin = JeuApplication.class.getResource("img/terrain/contourChemin.png");
        Image imContourChemin = new Image(String.valueOf(urlImContourChemin));

        URL urlImArriveeNO = JeuApplication.class.getResource("img/bunker/NO.png");
        Image imArriveeNO = new Image(String.valueOf(urlImArriveeNO));
        URL urlImArriveeNE = JeuApplication.class.getResource("img/bunker/NE.png");
        Image imArriveeNE = new Image(String.valueOf(urlImArriveeNE));
        URL urlImArriveeSE = JeuApplication.class.getResource("img/bunker/SE.png");
        Image imArriveeSE = new Image(String.valueOf(urlImArriveeSE));
        URL urlImArriveeSO = JeuApplication.class.getResource("img/bunker/SO.png");
        Image imArriveeSO = new Image(String.valueOf(urlImArriveeSO));

        URL urlImTourelleSemi = JeuApplication.class.getResource("img/tourelle/tourelleSemiEteinte.png");
        Image imTourelleSemi = new Image(String.valueOf(urlImTourelleSemi));
        URL urlImTourelleAuto = JeuApplication.class.getResource("img/tourelle/tourelleAutoEteinte.png");
        Image imTourelleAuto = new Image(String.valueOf(urlImTourelleAuto));
        URL urlImTourelleMissiles = JeuApplication.class.getResource("img/tourelle/tourelleMissiles.png");
        Image imTourelleMissiles = new Image(String.valueOf(urlImTourelleMissiles));


        for (int i = 0 ; i < terrain.getTerrain().length ; i++){
            for (int j = 0 ; j < terrain.getTerrain()[i].length ; j++) {
                switch (terrain.getTerrain()[i][j]) {
                    case 0 -> {
                        double rand = Math.random();
                        if (rand < 0.01) {
                            ImageView imageView = new ImageView(imHerbe);
                            ImageView imageViewBaril = new ImageView(imBaril);
                            StackPane stackPane = new StackPane();
                            stackPane.getChildren().addAll(imageView, imageViewBaril);
                            tilePane.getChildren().add(stackPane);
                        } else if (rand < 0.04) {
                            ImageView imageView = new ImageView(imHerbe7);
                            tilePane.getChildren().add(imageView);
                        } else if (rand < 0.08) {
                            ImageView imageView = new ImageView(imHerbe6);
                            tilePane.getChildren().add(imageView);
                        } else if (rand < 0.1) {
                            ImageView imageView = new ImageView(imHerbe5);
                            tilePane.getChildren().add(imageView);
                        } else if (rand < 0.2) {
                            ImageView imageView = new ImageView(imHerbe4);
                            tilePane.getChildren().add(imageView);
                        } else if (rand < 0.4) {
                            ImageView imageView = new ImageView(imHerbe3);
                            tilePane.getChildren().add(imageView);
                        } else if (rand < 0.6) {
                            ImageView imageView = new ImageView(imHerbe2);
                            tilePane.getChildren().add(imageView);
                        } else {
                            ImageView imageView = new ImageView(imHerbe);
                            tilePane.getChildren().add(imageView);
                        }
                    }
                    case 1 -> {
                        ImageView imageView2 = new ImageView(imChemin);
                        tilePane.getChildren().add(imageView2);
                    }
                    case 2 -> {
                        ImageView imageView3 = new ImageView(imContourChemin);
                        tilePane.getChildren().add(imageView3);
                    }
                    case 3 -> {
                        ImageView imageView2_1 = new ImageView(imContourChemin);
                        ImageView imageView4 = new ImageView(imTourelleSemi);
                        StackPane stackPane = new StackPane();
                        stackPane.getChildren().addAll(imageView2_1, imageView4);
                        tilePane.getChildren().add(stackPane);
                    }
                    case 4 -> {
                        ImageView imageView2_2 = new ImageView(imContourChemin);
                        ImageView imageView5 = new ImageView(imTourelleAuto);
                        StackPane stackPane2 = new StackPane();
                        stackPane2.getChildren().addAll(imageView2_2, imageView5);
                        tilePane.getChildren().add(stackPane2);
                    }
                    case 5 -> {
                        ImageView imageView2_3 = new ImageView(imContourChemin);
                        ImageView imageView6 = new ImageView(imTourelleMissiles);
                        StackPane stackPane3 = new StackPane();
                        stackPane3.getChildren().addAll(imageView2_3, imageView6);
                        tilePane.getChildren().add(stackPane3);
                    }
                    case 11 -> {
                        ImageView imageView7 = new ImageView(imArriveeNO);
                        tilePane.getChildren().add(imageView7);
                        ImageView imageView7_1 = new ImageView(imChemin);
                        StackPane stackPane4 = new StackPane();
                        stackPane4.getChildren().addAll(imageView7_1, imageView7);
                        tilePane.getChildren().add(stackPane4);
                    }
                    case 12 -> {
                        ImageView imageView8 = new ImageView(imArriveeNE);
                        ImageView imageView7_2 = new ImageView(imChemin);
                        StackPane stackPane5 = new StackPane();
                        stackPane5.getChildren().addAll(imageView7_2, imageView8);
                        tilePane.getChildren().add(stackPane5);
                    }
                    case 13 -> {
                        ImageView imageView9 = new ImageView(imArriveeSE);
                        ImageView imageView7_3 = new ImageView(imChemin);
                        StackPane stackPane6 = new StackPane();
                        stackPane6.getChildren().addAll(imageView7_3, imageView9);
                        tilePane.getChildren().add(stackPane6);
                    }
                    case 14 -> {
                        ImageView imageView10 = new ImageView(imArriveeSO);
                        tilePane.getChildren().add(imageView10);
                        ImageView imageView7_4 = new ImageView(imChemin);
                        StackPane stackPane7 = new StackPane();
                        stackPane7.getChildren().addAll(imageView7_4, imageView10);
                        tilePane.getChildren().add(stackPane7);
                    }
                }
            }

        }

    }
}
