package fr.iut.montreuil.metallic_infestation.modele;

public class EnnemiFacile extends Ennemi{

    public EnnemiFacile(ParcoursBFS parcoursBFS, Terrain terrain){
        super(50,4,1,parcoursBFS,terrain);
    }

    @Override
    public String toString() {
        return "EnnemiFacile" + super.toString();
    }
    public void retablirVitesse(){
        this.setVitesse(4);
    }
}
