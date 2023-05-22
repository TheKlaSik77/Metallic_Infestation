package fr.iut.montreuil.metallic_infastation.modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaseTest {

    @Test
    void caseEgale() {
        Case c1 = new Case(0,0);
        Case c2 = new Case(1,1);
        Case c3 = new Case(1,1);

        assertTrue(c2.caseEgale(c3));
        assertFalse(c1.caseEgale(c2));
    }
}