package fr.iut.montreuil.metallic_infastation.modele;

public class Projectile extends ElementDeplacable{

    private Tourelle tourelle;
    private Ennemi ennemiVise;
    public Projectile(Point coordonnees, Tourelle tourelle, Ennemi ennemiVise, int vitesse){
        super(coordonnees,vitesse);
        this.tourelle = tourelle;
        this.ennemiVise = ennemiVise;
    }

    public void seDeplacer(){

        if (this.ennemiVise.coordonnees.getX() > this.coordonnees.getX()){
            this.coordonnees.setX(this.coordonnees.getX() + getVitesse());
        } else if (this.ennemiVise.coordonnees.getX() < this.coordonnees.getX()){
            this.coordonnees.setX(this.coordonnees.getX() - getVitesse());
        }
        if (this.ennemiVise.coordonnees.getY() > this.coordonnees.getY()){
            this.coordonnees.setY(this.coordonnees.getY() + getVitesse());
        } else if (this.ennemiVise.coordonnees.getY() < this.coordonnees.getY()){
            this.coordonnees.setY(this.coordonnees.getY() - getVitesse());
        }

    }
    public boolean arriveSurEnnemi(){
        return this.coordonnees.getCase().caseEgale(this.ennemiVise.getCase());
    }

    public Tourelle getTourelle() {
        return this.tourelle;
    }

    public Ennemi getEnnemiVise() {
        return ennemiVise;
    }
}
