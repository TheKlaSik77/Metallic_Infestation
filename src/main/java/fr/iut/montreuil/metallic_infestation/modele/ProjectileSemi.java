package fr.iut.montreuil.metallic_infestation.modele;

public class ProjectileSemi extends Projectile{

    public ProjectileSemi(Tourelle tourelleSemi, Ennemi ennemiVise){
        super(new Point(tourelleSemi.getPosition().getCentreCase().getX() - 16,tourelleSemi.getPosition().getCentreCase().getY() - 7),tourelleSemi,ennemiVise,10);
    }

}
