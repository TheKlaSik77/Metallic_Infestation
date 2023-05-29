package fr.iut.montreuil.metallic_infastation.modele;

import fr.iut.montreuil.metallic_infastation.vue.TerrainVue;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

import java.util.Iterator;

public class Boutique {
   private Joueur joueur;
   private Environnement environnement;

   private Terrain terrain;

   private ObservableList<Tourelle> tourelles;




    public Boutique (Joueur joueur, Environnement environnement, Terrain terrain){
        this.environnement = environnement;
        this.joueur = joueur;
        this.terrain = terrain;
        this.tourelles = environnement.getListeTourelles();

    }

    public void AchatPv (int montant, int pv) {
        if (joueur.achatPossible(montant) == true){
            joueur.crediterPvJoueurProperty(pv);
            joueur.debiterArgentProperty(montant);
        }
    }
    /**
     * idTour == 1 -> TourelleSemi
     * idTour == 2
     *
     */
    public void AchatTour(int idTour, Case c){
        if (idTour == 1){
            Tourelle tourelle1 = new TourelleSemi(c, environnement, terrain);
            if(joueur.achatPossible(tourelle1.getCout())) {
                tourelles.add(tourelle1);
                terrain.setCase(c, 3);
                joueur.debiterArgentProperty(tourelle1.getCout());
            }
        }
        else if (idTour == 2) {
            //TODO
        }
        else {
            //TODO
        }
    }

    public void VenteTour(Case c) {
        ObservableList<Tourelle> tourelles = environnement.getListeTourelles();
        Iterator<Tourelle> iterator = tourelles.iterator();
        while (iterator.hasNext()) {
            Tourelle p = iterator.next();
            Case position = p.getPosition();
            if (c.getI() == position.getI() && c.getJ() == position.getJ()) {
                joueur.crediterArgentProperty(p.getCout());
                iterator.remove();
            }
        }
        terrain.setCase(c, 0);
    }

}
