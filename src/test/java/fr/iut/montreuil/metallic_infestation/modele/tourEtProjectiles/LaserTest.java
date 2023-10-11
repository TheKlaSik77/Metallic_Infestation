package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import static org.junit.jupiter.api.Assertions.*;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.EnnemiFacile;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.*;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.Laser;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.Tourelle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class LaserTest {

    private Laser laser;
    private Tourelle tourelle;
    private Ennemi ennemiVise;
    private Environnement environnement;
    private Terrain terrain;

    @Before
    public void setUp() {
        Case position = new Case(0, 0);
        terrain = Terrain.getInstance();
        environnement = Environnement.getInstance();
        tourelle = new TourelleSemi(position, environnement, terrain);
        Point coordonneesEnnemi = new Point(10, 10);
        ennemiVise = new EnnemiFacile(Environnement.getInstance());
        ennemiVise.setCoordonnees(coordonneesEnnemi.getX(), coordonneesEnnemi.getY());
        laser = new Laser(tourelle, ennemiVise);

    }

    @Test
    public void testCoordonneeTourelleDepart() {
        Point expected = new Point(0, 0);
        Point result = laser.CoordonnéeTourelleDepart();
        assertEquals(expected, result);
    }

    @Test
    public void testCoordonneeEnnemiArrive() {
        Point expected = new Point(10, 10);
        Point result = laser.CordonnéeEnnemiArrive();
        assertEquals(expected, result);
    }

    @Test
    public void testGetEnnemiVise() {
        Ennemi result = laser.getEnnemiVise();
        assertEquals(ennemiVise, result);
    }

    @Test
    public void testGetTourelle() {
        Tourelle result = laser.getTourelle();
        assertEquals(tourelle, result);
    }


    @Test
    public void testEquals() {
        Laser otherLaser = new Laser(tourelle, ennemiVise);
        assertNotEquals(laser, otherLaser);
    }

}
