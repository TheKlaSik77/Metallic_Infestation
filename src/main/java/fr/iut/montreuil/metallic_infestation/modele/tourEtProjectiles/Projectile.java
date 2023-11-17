package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import fr.iut.montreuil.metallic_infestation.modele.utilitaire.ElementDeplacable;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.effets.Effet;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Point;
import javafx.scene.effect.Effect;

public abstract class Projectile extends ElementDeplacable {

    private int id;
    private Point coordonneesDepart;
    private Ennemi ennemiVise;
    private Effet effet;
    private static int compteur = 0;

    protected Projectile(Effet effet, Point coordonneesDepart, int vitesse, Ennemi ennemiVise) {
        super(coordonneesDepart, vitesse);
        this.effet = effet;
        this.coordonneesDepart = coordonneesDepart;
        this.ennemiVise = ennemiVise;
        this.id = compteur;
        compteur++;
    }


    public boolean equals(Projectile p){
        return this.getId() == p.getId();
    }

    public void appliquerEffet(){
        this.effet.appliquerEffet(ennemiVise);
    }
    public void seDeplacer(){
        int deltaX = this.getEnnemiVise().getCoordonnees().getX() - this.getCoordonnees().getX();
        int deltaY = this.getEnnemiVise().getCoordonnees().getY() - this.getCoordonnees().getY();
        int ro = (int)((Math.pow(deltaX,2) + Math.pow(deltaY,2)) / (Math.pow(this.getVitesse(),2)));

        int deltaXModifie = (int)(deltaX / Math.sqrt(ro));
        int deltaYModifie = (int)(deltaY / Math.sqrt(ro));
        this.coordonnees.setX(this.coordonnees.getX() + deltaXModifie);
        this.coordonnees.setY(this.coordonnees.getY() + deltaYModifie);
    }

    public boolean arriveSurEnnemi(){
        return this.coordonnees.getCase().equals(this.ennemiVise.getCase());
    }

    public Ennemi getEnnemiVise() {
        return ennemiVise;
    }

}
