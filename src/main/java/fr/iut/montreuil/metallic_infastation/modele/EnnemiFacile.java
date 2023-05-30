package fr.iut.montreuil.metallic_infastation.modele;

public class EnnemiFacile extends Ennemi{

    public EnnemiFacile(Terrain terrain){
        super(3,3,terrain);
    }

    @Override
    public String toString() {
        return "EnnemiFacile" + super.toString();
    }
}
