package fr.iut.montreuil.metallic_infestation.connexion;

import fr.iut.montreuil.metallic_infestation.modele.utilitaire.*;

public class SauvegardePartie {

    private Environnement environnement;
    private int idSave;

    public SauvegardePartie() {
        this.environnement = Environnement.getInstance();
    }

    //Appel sauvegardeTourelles et sauvegardeObstacles
    public void sauvegarde(){

    }

    public void sauvegardeTourelles(){}

    public void sauvegardeObstacles(){}

    //Renvoit le
    public int calculeDurée(){return 0;}

    public int getIdDurée(){
        //select Max(idDuree) from metallic.Duree;
        return 0;
    }

    private int getIdSave(){

        //select Max(idSave) from metallic.Sauvegarde;
        return 0;
    }





}
