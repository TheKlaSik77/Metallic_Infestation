package fr.iut.montreuil.metallic_infastation.modele;

import fr.iut.montreuil.metallic_infastation.vue.LaserVue;

public class Laser {
    Tourelle tourelle;
    Ennemi ennemivisée;
    public Laser(Tourelle tourelle, Ennemi ennemivisée) {
        this.tourelle = tourelle;
        this.ennemivisée = ennemivisée;
    }

    public Point CoordonnéeTourelleDepart(){
        int x = tourelle.getPosition().getJ() *32;
        int y = tourelle.getPosition().getI() *32;
        return new Point(x, y);
    }

    public Point CordonnéeEnnemiArrive(){
        return ennemivisée.getCoordonnees();
    }

    public Ennemi getEnnemivisée(){
        return ennemivisée;
    }

    public Tourelle getTourelle (){
        return tourelle;
    }
}
