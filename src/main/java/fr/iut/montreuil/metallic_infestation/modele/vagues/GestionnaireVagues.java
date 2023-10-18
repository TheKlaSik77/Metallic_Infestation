
package fr.iut.montreuil.metallic_infestation.modele.vagues;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.EnnemiDifficile;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.EnnemiFacile;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.EnnemiMoyen;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.ParcoursBFS;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

public class GestionnaireVagues {
    private final int NB_VAGUES_JEU = 15;
    private Environnement environnement;
    private StrategieVague strategieVague;


    public GestionnaireVagues(Environnement environnement) {
        this.environnement = environnement;
        strategieVague = StrategiePremieresVagues.getInstance();
    }

    public ArrayList<Ennemi> lancerProchaineVague(Terrain terrainExperimental) {
        Environnement.incrementerVagueActuelleProperty();
        if(!estDerniereVague()) {
            determinerStrategie(terrainExperimental);
            return lancerVague(terrainExperimental);
        }
        return null;
    }


    public void determinerStrategie(Terrain terrainExperimental) {
        if (Environnement.vagueActuelleProperty.get() > 3 && Environnement.vagueActuelleProperty.get() <= 6) {
            setVagueStrategy(StrategieMilieuDebut.getInstance());

        } else if (Environnement.vagueActuelleProperty.get() > 6 && Environnement.vagueActuelleProperty.get() <= 10) {
            setVagueStrategy(StrategieMilieuFin.getInstance());

        } else if (Environnement.vagueActuelleProperty.get() > 10 & Environnement.vagueActuelleProperty.get() <= NB_VAGUES_JEU){
            setVagueStrategy(StrategieDernieresVagues.getInstance());
        }
    }

    public void setVagueStrategy(StrategieVague strategy) {
        this.strategieVague = strategy;
    }

    public ArrayList<Ennemi> lancerVague(Terrain terrain) {
        return strategieVague.genererEnnemisVague(terrain);
    }

    public boolean estDerniereVague() {
        return Environnement.vagueActuelleProperty.get() >= NB_VAGUES_JEU;
    }


}
