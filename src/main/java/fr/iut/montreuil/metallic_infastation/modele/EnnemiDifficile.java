package fr.iut.montreuil.metallic_infastation.modele;

public class EnnemiDifficile extends Ennemi{
    public EnnemiDifficile(Terrain terrain) {
        super(10, 1, terrain);
    }

    @Override
    public String toString() {
        return "EnnemiDifficile" + super.toString();
    }
}
