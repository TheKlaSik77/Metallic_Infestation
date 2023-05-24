package fr.iut.montreuil.metallic_infastation.modele;

public class EnnemiMoyen extends Ennemi{
    public EnnemiMoyen(Terrain terrain) {
        super(5, 1, new Point(0,(int)(Math.random() * 80)), terrain);
    }

    @Override
    public String toString() {
        return "EnnemiMoyen" + super.toString();
    }
}
