package fr.iut.montreuil.metallic_infestation.vue;

import fr.iut.montreuil.metallic_infestation.JeuApplication;
import fr.iut.montreuil.metallic_infestation.modele.Environnement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PieceVue {

    private Environnement environnement;
    private Pane zoneAffichagePieces;

    public PieceVue (Environnement environnement, Pane zoneAffichagePieces){
        this.environnement = environnement;
        this.zoneAffichagePieces = zoneAffichagePieces;
    }

    public void creerPiece (){
        String URL = String.valueOf(JeuApplication.class.getResource("img/piece/coin.png"));
        Image imagePiece = new Image(URL);
        ImageView imageViewPiece = new ImageView(imagePiece);
        zoneAffichagePieces.getChildren().add(imageViewPiece);

    }


}
