package fr.iut.montreuil.metallic_infastation.modele;

public class ProjectileMissile extends Projectile{
    public ProjectileMissile (Tourelle tourelleMissile, Ennemi ennemiVisee){
        super(new Point(tourelleMissile.getPosition().getCentreCase().getX()-16, tourelleMissile.getPosition().getCentreCase().getY()-7),tourelleMissile,ennemiVisee,10);
    }
}
