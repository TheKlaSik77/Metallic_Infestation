package fr.iut.montreuil.metallic_infestation.modele.vagues;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.EnnemiFacile;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.EnnemiMoyen;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.ParcoursBFS;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

import java.util.ArrayList;
import java.util.Random;

public class StrategieMilieuFin implements StrategieVague{

    private static StrategieMilieuFin instance;
    int nombreEnnemis = 16;
    int ennemisSupplementaires = 5;

    public static StrategieMilieuFin getInstance() {
        if (instance == null) {
            instance = new StrategieMilieuFin();
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
