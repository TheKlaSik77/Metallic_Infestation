package fr.iut.montreuil.metallic_infestation.modele.obstacles;

import static org.junit.jupiter.api.Assertions.*;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.EnnemiFacile;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Pics;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.ParcoursBFS;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PicsTest {

    @Test
    public void testActionnerPics() {
        Case c = new Case(1, 2);
        Terrain terrain = new Terrain();
        Environnement env = new Environnement(terrain);
        Pics pics = new Pics(c, env, terrain);
        ParcoursBFS parcoursBFS = new ParcoursBFS(terrain);
        Ennemi ennemi = new EnnemiFacile(parcoursBFS, terrain);
        ennemi.setVitesse(5);
        pics.actionnerPics(ennemi);
        int expectedVitesse = 1;
        int actualVitesse = ennemi.getVitesse();

        assertEquals(expectedVitesse, actualVitesse, "La vitesse de l'ennemi a été modifiée correctement par les pics.");
    }


}
