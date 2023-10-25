package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.ElementDeplacable;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Explosion;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Point;

public abstract class Projectile extends ElementDeplacable {


    private Point coordonneesDepart;
    private Ennemi ennemiVise;

    private Tourelle tourelle;

    protected Projectile(Point coordonneesDepart, Tourelle tourelle, int vitesse, Ennemi ennemiVise) {
        super(coordonneesDepart, vitesse);
        this.coordonneesDepart = coordonneesDepart;
        this.ennemiVise = ennemiVise;
        this.tourelle = tourelle;
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

    public Tourelle getTourelle(){return this.tourelle;}

}
