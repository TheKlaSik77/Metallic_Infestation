package fr.iut.montreuil.metallic_infestation.modele;

import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.ParcoursBFS;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;
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
<<<<<<< HEAD
        assertTrue(parcours.caseLaPlusProcheDArrivee(new Case(0,0)).equals(new Case(1,1)));
        assertTrue(parcours.caseLaPlusProcheDArrivee(new Case(5,0)).equals(new Case(6,1)));
        assertTrue(parcours.caseLaPlusProcheDArrivee(new Case(21,22)).equals(new Case(20,23)));
=======
        assertTrue(parcours.caseLaPlusProcheDArrivee(new Case(0, 0)).caseEgale(new Case(1, 1)));
        //assertTrue(parcours.caseLaPlusProcheDArrivee(new Case(5, 0)).caseEgale(new Case(6, 1)));
        //assertTrue(parcours.caseLaPlusProcheDArrivee(new Case(21, 22)).caseEgale(new Case(20, 23)));
>>>>>>> a5e4e48e7414f84eba56eb30cd4ff47a431fb2a1
    }

}