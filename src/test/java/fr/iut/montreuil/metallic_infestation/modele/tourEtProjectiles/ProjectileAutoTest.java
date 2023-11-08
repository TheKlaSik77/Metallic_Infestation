package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import static org.junit.jupiter.api.Assertions.*;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.EnnemiFacile;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.*;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.ProjectileAuto;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.Tourelle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class ProjectileAutoTest {

    private ProjectileAuto projectileAuto;
    private Tourelle tourelle;
    private Ennemi ennemiVise;
    private Environnement environnement;
    private Terrain terrain;

    @Before
    public void setUp() {
        Case position = new Case(0, 0);
        terrain = Terrain.getInstance();
        environnement = Environnement.getInstance();
        tourelle = new TourelleSemi(position);
        Point coordonneesEnnemi = new Point(10, 10);
        ennemiVise = new EnnemiFacile();
        ennemiVise.setCoordonnees(coordonneesEnnemi.getX(), coordonneesEnnemi.getY());
        projectileAuto = new ProjectileAuto(tourelle.getPosition().getCentreCase(), ennemiVise);

    }

    @Test
    public void testGetEnnemiVise() {
        Ennemi result = projectileAuto.getEnnemiVise();
        assertEquals(ennemiVise, result);
    }



    @Test
    public void testEquals() {
        ProjectileAuto otherProjectileAuto = new ProjectileAuto(tourelle.getPosition().getCentreCase(), ennemiVise);
        assertNotEquals(projectileAuto, otherProjectileAuto);
    }

}
