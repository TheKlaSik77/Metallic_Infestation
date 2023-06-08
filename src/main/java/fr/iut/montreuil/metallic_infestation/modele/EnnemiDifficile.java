package fr.iut.montreuil.metallic_infestation.modele;

public class EnnemiDifficile extends Ennemi{
    public EnnemiDifficile(ParcoursBFS parcoursBFS, Terrain terrain) {
        super(300, 2,5, parcoursBFS, terrain);
    }

    @Override
    public String toString() {
        return "EnnemiDifficile" + super.toString();
    }
}
