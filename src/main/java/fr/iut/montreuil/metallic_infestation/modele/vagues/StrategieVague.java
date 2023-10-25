package fr.iut.montreuil.metallic_infestation.modele.vagues;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

import java.util.ArrayList;

public interface StrategieVague {

    ArrayList<Ennemi> genererEnnemisVague(Terrain terrain);

}
