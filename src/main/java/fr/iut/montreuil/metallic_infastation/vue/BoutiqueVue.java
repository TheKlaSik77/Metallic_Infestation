package fr.iut.montreuil.metallic_infastation.vue;

import fr.iut.montreuil.metallic_infastation.modele.Boutique;
import fr.iut.montreuil.metallic_infastation.modele.Joueur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;


public class BoutiqueVue {
    @FXML
    private ToggleGroup groupeRadio;
    @FXML
    private RadioButton tour1;

    @FXML
    private RadioButton tour2;

    @FXML
    private RadioButton tour3;

    private Boutique boutique;

    public BoutiqueVue (Boutique boutique, ToggleGroup groupeRadio, RadioButton tour1, RadioButton tour2, RadioButton tour3){
        this.boutique = boutique;
        this.groupeRadio = groupeRadio;
        this.tour1 = tour1;
        this.tour2 = tour2;
        this.tour3 = tour3;
    }
    public void achatUnPv() {
        boutique.AchatPv(10,1);
    }

    public void achatTroisPv() {
        boutique.AchatPv(30,3);
    }

    public void achatCinqPv() {
        boutique.AchatPv(50,5);
    }

    public void achatTour(){
        if (tour1.isSelected()){
            System.out.println("Tour1");
            //TODO : Permettre au label 'prixTour' D'afficher le prix de la tour a payer
            // TODO : Appeler la méthode acheterTour dans la boutique
        }
        else if (tour2.isSelected()) {
            System.out.println("Tour2");
            //TODO : Permettre au label 'prixTour' D'afficher le prix de la tour a payer
            // TODO : Appeler la méthode acheterTour dans la boutique
        }
        else{
            System.out.println("Tour3");
            //TODO : Permettre au label 'prixTour' D'afficher le prix de la tour a payer
            // TODO : Appeler la méthode acheterTour dans la boutique
        }
    }

}
