package fr.iut.montreuil.metallic_infestation.modele.obstacles;

import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MineTest {
    @Test
    public void testGetDegats() {
        Case c = new Case(1, 2);
        Terrain terrain = new Terrain();
        Environnement env = new Environnement(terrain);
        Mine mine = new Mine(c, env, terrain);

        int expectedDegats = 300;
        int actualDegats = mine.getDegats();

        assertEquals(expectedDegats, actualDegats, "Les dégâts correspondent");
    }

    @Test
    public void testGetPorteeExplosion() {
        Case c = new Case(1, 2);
        Terrain terrain = new Terrain();
        Environnement env = new Environnement(terrain);
        Mine mine = new Mine(c, env, terrain);

        int expectedPorteeExplosion = 2;
        int actualPorteeExplosion = mine.getPorteeExplosion();

        assertEquals(expectedPorteeExplosion, actualPorteeExplosion, "La portée d'explosion correspond");
    }

}