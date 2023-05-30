package fr.iut.montreuil.metallic_infastation.modele;

public class EnnemiFacile extends Ennemi{

    public EnnemiFacile(Terrain terrain){
        super(3,1,1,terrain);
    }

    @Override
    public String toString() {
        return "EnnemiFacile" + super.toString();
    }
}
