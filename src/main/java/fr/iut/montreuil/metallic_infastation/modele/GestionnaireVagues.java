package fr.iut.montreuil.metallic_infastation.modele;

import fr.iut.montreuil.metallic_infastation.modele.Environnement;
import fr.iut.montreuil.metallic_infastation.modele.Terrain;

public class GestionnaireVagues {
    private final int NB_VAGUES_JEU = 15;
    //final static int NOMBRE_VAGUES_POUR_ENNEMI_DIFFICILE = 3;
    //final static int NOMBRE_ENNEMIS_DIFFICILES_SUPPLEMENTAIRES = 2;
    private int vagueActuelle;
    private Environnement environnement;

    public GestionnaireVagues(Environnement environnement) {
        this.environnement = environnement;
        this.vagueActuelle = 0;
    }

    public void lancerProchaineVague(Terrain terrainExperimental) {
        if (vagueActuelle < NB_VAGUES_JEU) {
                System.out.println("Une vague ennemie se prépare...");
                System.out.println("Vague actuelle : " + (vagueActuelle + 1));
                environnement.lancerVague(terrainExperimental);
                vagueActuelle++;
        }
    }

    public boolean estDerniereVague() {
        return vagueActuelle >= NB_VAGUES_JEU;
    }



}