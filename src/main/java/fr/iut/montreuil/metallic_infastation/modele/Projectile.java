package fr.iut.montreuil.metallic_infastation.modele;

public class Projectile extends ElementDeplacable{

    private Tourelle tourelle;
    private Ennemi ennemiVise;
    public Projectile(Point coordonnees, int vitesse, Tourelle tourelle, Ennemi ennemiVise){
        super(coordonnees,vitesse);
        this.tourelle = tourelle;
        this.ennemiVise = ennemiVise;
    }

    public void seDeplacer(){

        if (this.ennemiVise.coordonnees.getX() > this.coordonnees.getX()){
            this.coordonnees.setX(this.coordonnees.getX() + 1);
        } else if (this.ennemiVise.coordonnees.getX() < this.coordonnees.getX()){
            this.coordonnees.setX(this.coordonnees.getX() - 1);
        }
        if (this.ennemiVise.coordonnees.getY() > this.coordonnees.getY()){
            this.coordonnees.setY(this.coordonnees.getY() + 1);
        } else if (this.ennemiVise.coordonnees.getY() < this.coordonnees.getY()){
            this.coordonnees.setY(this.coordonnees.getY() - 1);
        }

    }
}
