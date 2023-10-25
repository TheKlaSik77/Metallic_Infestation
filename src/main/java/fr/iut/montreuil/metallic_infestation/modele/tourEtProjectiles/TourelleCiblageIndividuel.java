package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

import java.util.ArrayList;
import java.util.Map;

public abstract class TourelleCiblageIndividuel extends Tourelle{

    //Par défaut juste en attendant le merge de Kylian qui va tout résoudre !
    public TourelleCiblageIndividuel(int degats, Case position, int cout, int porteeTourelle, int porteeMissile) {
        super(degats, position, cout, porteeTourelle, porteeMissile);
    }
//    public TourelleCiblageIndividuel(Case position, int cout, int porteeTourelle, Environnement env, Terrain terrain) {
//        super(position, cout, porteeTourelle, env, terrain);
//    }
//
//    public Ennemi ennemiLePlusProche() {
//        ArrayList<Ennemi> tabDistanceEnnemiTrie = Environnement.getInstance().getEnnemiLesPlusProchesDePosition(this.getCoordonnes(),this.getPorteeTourelle());
//        return tabDistanceEnnemiTrie.get(0);
//    }
//    public void raffraichirEnnemi(){
//        ennemiLePlusProche();
//    }
}
