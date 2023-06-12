package fr.iut.montreuil.metallic_infestation.modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParcoursBFSTest {

    @Test
    void caseLaPlusProcheDArrivee() {
        Terrain terrainExperimental = new Terrain();
        ParcoursBFS parcours = new ParcoursBFS(terrainExperimental);
        parcours.remplirGrilleBFS();
        parcours.afficherParcours();
        assertTrue(parcours.caseLaPlusProcheDArrivee(new Case(0,0)).equals(new Case(1,1)));
        assertTrue(parcours.caseLaPlusProcheDArrivee(new Case(5,0)).equals(new Case(6,1)));
        assertTrue(parcours.caseLaPlusProcheDArrivee(new Case(21,22)).equals(new Case(20,23)));
    }
}