package fr.iut.montreuil.metallic_infestation.modele.vagues;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.EnnemiDifficile;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.ParcoursBFS;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

import java.util.ArrayList;

public class StrategieDernieresVagues implements StrategieVague{

    public static StrategieDernieresVagues instance;
    int nombreEnnemis = 24;
    int ennemisSupplementaires = 6;

    public static StrategieDernieresVagues getInstance() {
        if (instance == null) {
            instance = new StrategieDernieresVagues();
        }
        return instance;
    }

    @Override
    public ArrayList<Ennemi> genererEnnemisVague() {
        ArrayList<Ennemi> listeEnnemisASpawn = new ArrayList<>();
        nombreEnnemis += ennemisSupplementaires;
        for (int i = 0; i < nombreEnnemis; i++) {
            EnnemiDifficile ennemiDifficile = new EnnemiDifficile();
            listeEnnemisASpawn.add(ennemiDifficile);
        }
        System.out.println(listeEnnemisASpawn.size());
        return listeEnnemisASpawn;
    }
}
