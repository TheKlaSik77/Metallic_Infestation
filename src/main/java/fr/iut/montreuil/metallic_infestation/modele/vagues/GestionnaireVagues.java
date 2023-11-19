
package fr.iut.montreuil.metallic_infestation.modele.vagues;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

import java.util.ArrayList;

public class GestionnaireVagues {
    private final int NB_VAGUES_JEU = 15;
    private StrategieVague strategieVague;


    public GestionnaireVagues() {

        strategieVague = StrategiePremieresVagues.getInstance();
    }

    public ArrayList<Ennemi> lancerProchaineVague() {
        Environnement.incrementerVagueActuelleProperty();
        if(!estDerniereVague()) {
            determinerStrategie();
            return lancerVague();
        }
        return null;
    }


    public void determinerStrategie() {
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

    public ArrayList<Ennemi> lancerVague() {
        return strategieVague.genererEnnemisVague();
    }

    public boolean estDerniereVague() {
        return Environnement.vagueActuelleProperty.get() >= NB_VAGUES_JEU;
    }


}
