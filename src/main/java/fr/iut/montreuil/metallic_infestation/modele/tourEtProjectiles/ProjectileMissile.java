package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.effets.Explosion;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Point;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;

public class ProjectileMissile extends Projectile {
    private DoubleProperty angleProperty;
    public ProjectileMissile (Point positionDepart, Ennemi ennemiVise){
        super(new Explosion(5,50,env),postitionDepart,2,ennemiVise);
        this.angleProperty = new SimpleDoubleProperty(Math.toDegrees(Math.atan2(this.getEnnemiVise().getCoordonnees().getY() - this.getCoordonnees().getY(),this.getEnnemiVise().getCoordonnees().getX() - this.getCoordonnees().getX())));
    }


    public DoubleProperty AngleProperty(){return angleProperty;}
}
