package fr.iut.montreuil.metallic_infestation.modele.utilitaire;

public abstract class ElementNonDeplacable {

    protected Case position;
    protected Environnement environnement;

    public ElementNonDeplacable(Case c){
         this.position = c;
         this.environnement = Environnement.getInstance();
    }

    public Case getPosition() {
        return position;
    }
    public Environnement getE() {
        return environnement;
    }


}
