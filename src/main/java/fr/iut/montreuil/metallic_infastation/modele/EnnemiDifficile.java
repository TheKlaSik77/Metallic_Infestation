package fr.iut.montreuil.metallic_infastation.modele;

public class EnnemiDifficile extends Ennemi{
    public EnnemiDifficile(ParcoursBFS parcoursBFS, Terrain terrain) {
        super(10, 1,5, parcoursBFS, terrain);
    }

    @Override
    public String toString() {
        return "EnnemiDifficile" + super.toString();
    }
}
