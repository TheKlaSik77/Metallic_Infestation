package fr.iut.montreuil.metallic_infastation.modele;

public abstract class ElementDeplacable {

    private Point coordonnees;
    private int vitesse;

    protected ElementDeplacable(Point coordonnees, int vitesse){
        this.coordonnees = coordonnees;
        this.vitesse = vitesse;
    }

    public Point getCoordonnees(){
        return this.coordonnees;
    }

    public int getVitesse(){
        return this.vitesse;
    }

    public void setVitesse(int vitesse){
        this.vitesse = vitesse;
    }
}
