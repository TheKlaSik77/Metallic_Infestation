
package fr.iut.montreuil.metallic_infestation.modele;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
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


    public ArrayList<Ennemi> lancerProchaineVague(Terrain terrainExperimental) {
        ArrayList<Ennemi> ennemisASpawn = new ArrayList<>();
        Environnement.vagueActuelle++;
        if (Environnement.vagueActuelle < NB_VAGUES_JEU) {
            if (!estDerniereVague()) {
                System.out.println("Une vague ennemie se prépare...");
                System.out.println("Vague actuelle : " + (Environnement.vagueActuelle));
                ennemisASpawn = lancerVague(terrainExperimental);

            }
        }
        return ennemisASpawn;
    }

    private ArrayList<Ennemi> lancerVague(Terrain terrain) {
        ArrayList<Ennemi> listeEnnemisASpawn = new ArrayList<>();
        Random random = new Random();
        int nombreEnnemis = 3;
        int ennemisSupplementaires = (Environnement.vagueActuelle / 2) * 2; // Calcule le nombre d'ennemis supplémentaires à ajouter

        // À partir de la 10e vague, tous les ennemis sont des ennemis difficiles
        if (Environnement.vagueActuelle >= NB_VAGUES_JEU_TRANSITION_ENNEMIS_DIFF) {
            nombreEnnemis += ennemisSupplementaires;
            for (int i = 0; i < nombreEnnemis; i++) {
                EnnemiDifficile ennemiDifficile = new EnnemiDifficile(environnement.getParcoursBFS(), terrain);
                listeEnnemisASpawn.add(ennemiDifficile);
            }
        } else {
            int typeEnnemi = 0;
            nombreEnnemis += ennemisSupplementaires;
            for (int i = 0; i < nombreEnnemis; i++) {
                if (Environnement.vagueActuelle >= 3 && Environnement.vagueActuelle <= 5) {
                    typeEnnemi = random.nextInt(2);
                } else if (Environnement.vagueActuelle > 6) {
                    typeEnnemi = random.nextInt(3);
                }
                switch (typeEnnemi) {
                    case 0:
                        EnnemiFacile ennemiFacile = new EnnemiFacile(environnement.getParcoursBFS(), terrain);
                        listeEnnemisASpawn.add(ennemiFacile);
                        break;
                    case 1:
                        EnnemiMoyen ennemiMoyen = new EnnemiMoyen(environnement.getParcoursBFS(), terrain);
                        listeEnnemisASpawn.add(ennemiMoyen);
                        break;
                    case 2:
                        EnnemiDifficile ennemiDifficile = new EnnemiDifficile(environnement.getParcoursBFS(), terrain);
                        listeEnnemisASpawn.add(ennemiDifficile);
                        break;
                }
            }
        }
        return listeEnnemisASpawn;
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
