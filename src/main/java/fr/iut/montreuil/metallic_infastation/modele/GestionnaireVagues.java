
package fr.iut.montreuil.metallic_infastation.modele;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class GestionnaireVagues {
    private final int NB_VAGUES_JEU = 15;
    private final int NB_VAGUES_JEU_TRANSITION_ENNEMIS_DIFF = 10;

    private final Environnement environnement;
    private Instant debutPartie;


    public GestionnaireVagues(Environnement environnement) {
        this.environnement = environnement;
        debutPartie = Instant.now();
    }


    public void lancerProchaineVague(Terrain terrainExperimental) {
        Environnement.vagueActuelle++;
        if (Environnement.vagueActuelle < NB_VAGUES_JEU) {
            if (!estDerniereVague()) {
                System.out.println("Une vague ennemie se prépare...");
                System.out.println("Vague actuelle : " + (Environnement.vagueActuelle));
                lancerVague(terrainExperimental);

            }
        }
    }

    private void lancerVague(Terrain terrain) {
        Random random = new Random();
        int nombreEnnemis = 10;
        int ennemisSupplementaires = (Environnement.vagueActuelle / 2) * 2; // Calcule le nombre d'ennemis supplémentaires à ajouter

        // À partir de la 10e vague, tous les ennemis sont des ennemis difficiles
        if (Environnement.vagueActuelle >= NB_VAGUES_JEU_TRANSITION_ENNEMIS_DIFF) {
            nombreEnnemis += ennemisSupplementaires;
            for (int i = 0; i < nombreEnnemis; i++) {
                EnnemiDifficile ennemiDifficile = new EnnemiDifficile(environnement.parcoursBFS, terrain);
                environnement.getListeEnnemis().add(ennemiDifficile);
            }
        } else {
            nombreEnnemis += ennemisSupplementaires;
            for (int i = 0; i < nombreEnnemis; i++) {
                int typeEnnemi = random.nextInt(3);

                switch (typeEnnemi) {
                    case 0:
                        EnnemiFacile ennemiFacile = new EnnemiFacile(environnement.parcoursBFS, terrain);
                        environnement.getListeEnnemis().add(ennemiFacile);
                        break;
                    case 1:
                        EnnemiMoyen ennemiMoyen = new EnnemiMoyen(environnement.parcoursBFS, terrain);
                        environnement.getListeEnnemis().add(ennemiMoyen);
                        break;
                    case 2:
                        EnnemiDifficile ennemiDifficile = new EnnemiDifficile(environnement.parcoursBFS, terrain);
                        environnement.getListeEnnemis().add(ennemiDifficile);
                        break;
                }
            }
        }
        for (int i = 0; i<environnement.getListeEnnemis().size(); i)
    }

    public boolean estDerniereVague() {
        return Environnement.vagueActuelle >= NB_VAGUES_JEU;
    }

    public Instant getDebutPartie() {
        return debutPartie;
    }

    public void setDebutPartie(Instant debutPartie) {
        this.debutPartie = debutPartie;
    }



}
 


