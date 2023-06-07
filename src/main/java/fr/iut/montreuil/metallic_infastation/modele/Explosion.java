package fr.iut.montreuil.metallic_infastation.modele;

public class Explosion {
    private Point coordonneeExplosion;
    private int porteeExplosion;
    private String id;
    private static int compteur = 0;


    public Explosion (Point coordonneeExplosion, int porteeExplosion){
        this.coordonneeExplosion = coordonneeExplosion;
        this.porteeExplosion = porteeExplosion;
        this.id = "M"+compteur;
        this.compteur++;
    }

    public Point getCoordonneeExplosion (){return this.coordonneeExplosion;}
    public int getPorteeExplosion() {return this.porteeExplosion;}

    public String getId(){return this.id;}

}
