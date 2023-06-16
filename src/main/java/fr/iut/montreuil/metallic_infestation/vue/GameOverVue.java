package fr.iut.montreuil.metallic_infestation.vue;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


public class GameOverVue {
    private Label gameOverLabel;


    private String texteGameOver;



    public GameOverVue(Label texteGameOver) {
        this.texteGameOver = null;
        this.gameOverLabel = texteGameOver;

    }


    public void affichageGameOver() {
        this.texteGameOver = "Game Over";

        gameOverLabel.setText(texteGameOver);

    }
}