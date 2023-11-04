package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Explosion;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Point;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class ProjectileMissile extends Projectile {
    private Environnement env;
    private DoubleProperty angleProperty;
    public ProjectileMissile (Environnement env,Point coordonneesDepart, Ennemi ennemiVise){
        super(coordonneesDepart,2,ennemiVise);
        this.env = env;
        this.angleProperty = new SimpleDoubleProperty(Math.toDegrees(Math.atan2(this.getEnnemiVise().getCoordonnees().getY() - this.getCoordonnees().getY(),this.getEnnemiVise().getCoordonnees().getX() - this.getCoordonnees().getX())));
    }
    public Explosion creerExplosion () {
        return new Explosion(this.env, this.getEnnemiVise().getCoordonnees(), this.getDegats(), this.getPorteeExplosition());
    }
    public DoubleProperty AngleProperty(){return angleProperty;}
}
