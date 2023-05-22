package fr.iut.montreuil.metallic_infastation.modele;

public class EnnemiFacile extends Ennemi{

    public EnnemiFacile(Terrain terrain){
        super(3,1,new Point(0,(int)(Math.random() * 80)),terrain);

    }
}
