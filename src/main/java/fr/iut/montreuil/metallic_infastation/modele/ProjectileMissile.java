package fr.iut.montreuil.metallic_infastation.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class ProjectileMissile extends Projectile{
    private DoubleProperty angleProperty;
    public ProjectileMissile (Tourelle tourelleMissile, Ennemi ennemiVisee){
        super(new Point(tourelleMissile.getPosition().getCentreCase().getX()-16, tourelleMissile.getPosition().getCentreCase().getY()-7),tourelleMissile,ennemiVisee,10);
        this.angleProperty = new SimpleDoubleProperty(Math.toDegrees(Math.atan2(this.getEnnemiVise().getCoordonnees().getY() - this.getCoordonnees().getY(),this.getEnnemiVise().getCoordonnees().getX() - this.getCoordonnees().getX())));
    }
    @Override
    public void seDeplacer() {

        int deltaX = this.getEnnemiVise().getCoordonnees().getX() - this.getCoordonnees().getX();
        int deltaY = this.getEnnemiVise().getCoordonnees().getY() - this.getCoordonnees().getY();
        angleProperty.set(Math.toDegrees(Math.atan2(deltaY, deltaX)));
        int ro = (int) ((Math.pow(deltaX, 2) + Math.pow(deltaY, 2)) / (Math.pow(this.getVitesse(), 2)));
        int deltaXModifie = (int) (deltaX / Math.sqrt(ro));
        int deltaYModifie = (int) (deltaY / Math.sqrt(ro));
        this.coordonnees.setX(this.coordonnees.getX() + deltaXModifie);
        this.coordonnees.setY(this.coordonnees.getY() + deltaYModifie);
    }
    public DoubleProperty AngleProperty(){return angleProperty;}
}
