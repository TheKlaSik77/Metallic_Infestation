package fr.iut.montreuil.metallic_infestation.modele.vagues;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.EnnemiFacile;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.ParcoursBFS;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

import java.util.ArrayList;

public class StrategiePremieresVagues implements StrategieVague {
    int nombreEnnemis = 1;
    int ennemisSupplementaires = 2;

    private static StrategiePremieresVagues instance;

    public static StrategiePremieresVagues getInstance() {
        if (instance == null) {
            instance = new StrategiePremieresVagues();
        }
        return instance;
    }



    @Override
    public ArrayList<Ennemi> genererEnnemisVague(Terrain terrain) {
        ArrayList<Ennemi> listeEnnemisASpawn = new ArrayList<>();
        nombreEnnemis += ennemisSupplementaires;
        for (int i = 0; i < nombreEnnemis; i++) {
            EnnemiFacile ennemiFacile = new EnnemiFacile(new ParcoursBFS(terrain), terrain);
            listeEnnemisASpawn.add(ennemiFacile);
        }
        System.out.println(listeEnnemisASpawn.size());
        return listeEnnemisASpawn;

    }


}
