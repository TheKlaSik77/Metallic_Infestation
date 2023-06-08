package fr.iut.montreuil.metallic_infestation.modele;

public abstract class Projectile extends ElementDeplacable{

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

    public abstract void seDeplacer();
    public boolean arriveSurEnnemi(){
        return this.coordonnees.getCase().caseEgale(this.ennemiVise.getCase());
    }

    public Tourelle getTourelle() {
        return this.tourelle;
    }

    public Ennemi getEnnemiVise() {
        return ennemiVise;
    }

    public Explosion creerExplosion () {
        return new Explosion(this.ennemiVise.getCoordonnees(), tourelle.getPorteeMissile());
    }
}
