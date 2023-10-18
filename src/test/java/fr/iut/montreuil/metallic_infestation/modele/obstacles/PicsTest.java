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
        Terrain terrain = Terrain.getInstance();
        Environnement env =Environnement.getInstance();
        Pics pics = new Pics(c);
        ParcoursBFS parcoursBFS = new ParcoursBFS();
        Ennemi ennemi = new EnnemiFacile();
        ennemi.setVitesse(5);
        pics.actionnerPics(ennemi);
        int expectedVitesse = 1;
        int actualVitesse = ennemi.getVitesse();

        assertEquals(expectedVitesse, actualVitesse, "La vitesse de l'ennemi a été modifiée correctement par les pics.");
    }


}
