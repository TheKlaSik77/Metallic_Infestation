package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.ElementDeplacable;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Point;

public abstract class Projectile extends ElementDeplacable {


    private Point coordonneesDepart;
    private Ennemi ennemiVise;

    protected Projectile(Point coordonneesDepart, int vitesse, Ennemi ennemiVise) {
        super(coordonneesDepart, vitesse);
        this.coordonneesDepart = coordonneesDepart;
        this.ennemiVise = ennemiVise;
    }


    public boolean equals(Projectile p){
        return this.getId() == p.getId();
    }


    public abstract void seDeplacer();

    public boolean arriveSurEnnemi(){
        return this.coordonnees.getCase().equals(this.ennemiVise.getCase());
    }

    public Ennemi getEnnemiVise() {
        return ennemiVise;
    }

}
