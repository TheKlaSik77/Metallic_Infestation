package fr.iut.montreuil.metallic_infestation.modele;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ParcoursBFSTest {

    @Test
    void caseLaPlusProcheDArrivee() {
        Terrain terrainExperimental = new Terrain();
        ParcoursBFS parcours = new ParcoursBFS(terrainExperimental);
        parcours.remplirGrilleBFS();
        parcours.afficherParcours();
        assertTrue(parcours.caseLaPlusProcheDArrivee(new Case(0, 0)).caseEgale(new Case(1, 1)));
        //assertTrue(parcours.caseLaPlusProcheDArrivee(new Case(5, 0)).caseEgale(new Case(6, 1)));
        //assertTrue(parcours.caseLaPlusProcheDArrivee(new Case(21, 22)).caseEgale(new Case(20, 23)));
    }

}