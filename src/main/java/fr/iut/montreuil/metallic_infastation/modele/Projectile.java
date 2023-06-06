package fr.iut.montreuil.metallic_infastation.modele;

public class Projectile extends ElementDeplacable{

    private Tourelle tourelle;
    private Ennemi ennemiVise;

    public Projectile(Point coordonnees, Tourelle tourelle, Ennemi ennemiVise, int vitesse){
        super(coordonnees,vitesse);
        this.tourelle = tourelle;
        this.ennemiVise = ennemiVise;
    }

    public boolean equals(Projectile p){
        return this.getId() == p.getId();
    }

    public void seDeplacer(){

        int deltaX = this.ennemiVise.getCoordonnees().getX() - this.getCoordonnees().getX();
        int deltaY = this.ennemiVise.getCoordonnees().getY() - this.getCoordonnees().getY();
        int ro = (int)((Math.pow(deltaX,2) + Math.pow(deltaY,2)) / (Math.pow(this.getVitesse(),2)));

        int deltaXModifie = (int)(deltaX / Math.sqrt(ro));
        int deltaYModifie = (int)(deltaY / Math.sqrt(ro));
        this.coordonnees.setX(this.coordonnees.getX() + deltaXModifie);
        this.coordonnees.setY(this.coordonnees.getY() + deltaYModifie);
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
