package fr.iut.montreuil.metallic_infastation.modele;

import fr.iut.montreuil.metallic_infastation.modele.Environnement;
import fr.iut.montreuil.metallic_infastation.modele.Terrain;

public class GestionnaireVagues {
    private final int NB_VAGUES_JEU = 15;

    private final Environnement environnement;

    public GestionnaireVagues(Environnement environnement) {
        this.environnement = environnement;
    }

    public void lancerProchaineVague(Terrain terrainExperimental) {
        Environnement.vagueActuelle++;
        if (Environnement.vagueActuelle < NB_VAGUES_JEU) {
            if(!estDerniereVague()) {
                System.out.println("Une vague ennemie se prépare...");
                System.out.println("Vague actuelle : " + (Environnement.vagueActuelle));
                environnement.lancerVague(terrainExperimental);

            }
        }
    }

    public boolean estDerniereVague() {
        return Environnement.vagueActuelle >= NB_VAGUES_JEU;
    }



}