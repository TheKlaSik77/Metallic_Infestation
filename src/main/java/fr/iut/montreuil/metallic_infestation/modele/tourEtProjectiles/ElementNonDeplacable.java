package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.ElementDeplacable;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import javafx.collections.ObservableList;

public abstract class ElementNonDeplacable {

    protected Case position;
    protected Environnement environnement;

    public ElementNonDeplacable(Case c, Environnement e){
         this.position = c;
         this.environnement = e;
    }

    public Case getPosition() {
        return position;
    }
    public Environnement getE() {
        return environnement;
    }


}
