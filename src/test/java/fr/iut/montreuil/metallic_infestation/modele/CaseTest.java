package fr.iut.montreuil.metallic_infestation.modele;

import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaseTest {

    @Test
    void caseEgale() {
        Case c1 = new Case(0,0);
        Case c2 = new Case(1,1);
        Case c3 = new Case(1,1);

        assertTrue(c2.equals(c3));
        assertFalse(c1.equals(c2));
    }
}