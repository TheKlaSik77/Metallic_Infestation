package fr.iut.montreuil.metallic_infestation.modele;

public class Vecteur {
    int a;
    int b;

    public Vecteur(int a, int b){
        this.a = a;
        this.b = b;
    }

    public static Vecteur calculVecteurDirecteur(Point a, Point b){
        return new Vecteur(b.getX() - a.getX(),b.getY() - a.getY());
    }
}
