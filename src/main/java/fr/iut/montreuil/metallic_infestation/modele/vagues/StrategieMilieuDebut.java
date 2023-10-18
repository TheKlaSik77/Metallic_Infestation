package fr.iut.montreuil.metallic_infestation.modele.vagues;


import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.EnnemiFacile;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.EnnemiMoyen;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.ParcoursBFS;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class StrategieMilieuDebut implements StrategieVague{

    int nombreEnnemis = 8;
    int ennemisSupplementaires = 2;

    private static StrategieMilieuDebut instance;

    public static StrategieMilieuDebut getInstance() {
        if (instance == null) {
            instance = new StrategieMilieuDebut();
        }
        return instance;
    }

    @Override
    public ArrayList<Ennemi> genererEnnemisVague(Terrain terrain) {
        ArrayList<Ennemi> listeEnnemisASpawn = new ArrayList<>();
        Random random = new Random();
        nombreEnnemis += ennemisSupplementaires;
        int typeEnnemi = 0;
        for (int i = 0; i < nombreEnnemis; i++) {
                typeEnnemi = random.nextInt(2);
            switch (typeEnnemi) {
                case 0:
                    EnnemiFacile ennemiFacile = new EnnemiFacile(new ParcoursBFS(terrain), terrain);
                    listeEnnemisASpawn.add(ennemiFacile);
                    break;
                case 1:
                    EnnemiMoyen ennemiMoyen = new EnnemiMoyen(new ParcoursBFS(terrain), terrain);
                    listeEnnemisASpawn.add(ennemiMoyen);
                    break;
            }
        }
        System.out.println(listeEnnemisASpawn.size());
        return listeEnnemisASpawn;
    }
}
