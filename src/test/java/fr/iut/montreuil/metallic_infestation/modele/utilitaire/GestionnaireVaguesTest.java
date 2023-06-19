package fr.iut.montreuil.metallic_infestation.modele.utilitaire;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.EnnemiDifficile;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.EnnemiFacile;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.EnnemiMoyen;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.Projectile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestionnaireVaguesTest {

    private Environnement environnement;
    private GestionnaireVagues gestionnaireVagues;
    private Terrain terrainExperimental;

    @BeforeEach
    public void setUp() {
        terrainExperimental = new Terrain();
        environnement = new Environnement(terrainExperimental);
        gestionnaireVagues = new GestionnaireVagues(environnement);

    }

    @Test
    public void testLancerProchaineVague_listeNonNull_PasDerniereVague() {
        ArrayList<Ennemi> ennemisASpawn = gestionnaireVagues.lancerProchaineVague(terrainExperimental);
        assertNotNull(ennemisASpawn, "La liste des ennemis à spawner ne doit pas être nulle.");
        assertFalse(gestionnaireVagues.estDerniereVague(), "La dernière vague ne doit pas être atteinte.");

    }

    @Test
    public void testLancerVague() {
        ArrayList<Ennemi> listeEnnemisASpawn = gestionnaireVagues.lancerVague(terrainExperimental);
        assertNotNull(listeEnnemisASpawn, "La liste des ennemis à spawner ne devrait pas être nulle.");
        assertFalse(listeEnnemisASpawn.isEmpty(), "La liste des ennemis à spawner ne devrait pas être vide.");

    }

    @Test
    public void testLancerVague_DifferentsNombreEnnemis(){
        Environnement.vagueActuelleProperty.set(1);
        ArrayList<Ennemi> listeEnnemis = gestionnaireVagues.lancerVague(terrainExperimental);
        int expectedNombreEnnemis = 3;
        assertEquals(expectedNombreEnnemis, listeEnnemis.size(), "Le nombre d'ennemis pour la 1ère vague est correct.");

        // Test pour la 5ème vague
        Environnement.vagueActuelleProperty.set(5);
        listeEnnemis = gestionnaireVagues.lancerVague(terrainExperimental);
        expectedNombreEnnemis = 7;
        assertEquals(expectedNombreEnnemis, listeEnnemis.size(), "Le nombre d'ennemis pour la 5ème vague est correct.");

        // Test pour la 12ème vague
        Environnement.vagueActuelleProperty.set(12);
        listeEnnemis = gestionnaireVagues.lancerVague(terrainExperimental);
        expectedNombreEnnemis = 15;
        assertEquals(expectedNombreEnnemis, listeEnnemis.size(), "Le nombre d'ennemis pour la 12ème vague est correct.");

    }

    @Test
    public void testLancerVague_DifferentesTypeEnnemis(){
        // test première vague
        Environnement.vagueActuelleProperty.set(1);
        ArrayList<Ennemi> listeEnnemisASpawnVague1 = gestionnaireVagues.lancerVague(terrainExperimental);
        assertTrue(listeEnnemisASpawnVague1.stream().allMatch(e -> e instanceof EnnemiFacile),
                "Tous les ennemis dans la liste doivent être des ennemis faciles pour la vague 1.");


        // test vague 3
        Environnement.vagueActuelleProperty.set(3);

        ArrayList<Ennemi> listeEnnemisASpawnVague3 = gestionnaireVagues.lancerVague(terrainExperimental);
        boolean estFacileVague3 = false;
        boolean estMoyenVague3= false;

        for (Ennemi ennemi :  listeEnnemisASpawnVague3) {
            if (ennemi instanceof EnnemiFacile) {
                estFacileVague3 = true;
            } else if (ennemi instanceof EnnemiMoyen) {
                estMoyenVague3 = true;
            }
        }
        assertTrue(estFacileVague3, "La liste doit contenir au moins un ennemi de type EnnemiFacile pour la vague 4.");
        assertTrue(estMoyenVague3, "La liste doit contenir au moins un ennemi de type EnnemiMoyen pour la vague 4.");


        // test vague 7
        Environnement.vagueActuelleProperty.set(7);

        ArrayList<Ennemi> listeEnnemisASpawnVague7 = gestionnaireVagues.lancerVague(terrainExperimental);
        boolean estFacileVague7 = false;
        boolean estMoyenVague7 = false;
        boolean estDifficileVague7 = false;

        for (Ennemi ennemi : listeEnnemisASpawnVague7) {
            if (ennemi instanceof EnnemiFacile) {
                estFacileVague7 = true;
            } else if (ennemi instanceof EnnemiMoyen) {
                estMoyenVague7 = true;
            } else if (ennemi instanceof EnnemiDifficile) {
                estDifficileVague7 = true;

            }
        }
        assertTrue(estFacileVague7, "La liste doit contenir au moins un ennemi de type EnnemiFacile pour la vague 7.");
        assertTrue(estMoyenVague7, "La liste doit contenir au moins un ennemi de type EnnemiMoyen pour la vague 7.");
        assertTrue(estDifficileVague7, "La liste doit contenir au moins un ennemi de type EnnemiDiffcile pour la vague 7.");



        // test vague 10
        Environnement.vagueActuelleProperty.set(10);
        ArrayList<Ennemi> listeEnnemisASpawnVague11 = gestionnaireVagues.lancerVague(terrainExperimental);
        assertTrue(listeEnnemisASpawnVague11.stream().allMatch(e -> e instanceof EnnemiDifficile),
                "Tous les ennemis dans la liste doivent être des ennemis difficiles pour la vague 11.");
    }

    @Test
    public void testEstDerniereVague(){
        environnement.setVagueActuelleProperty(14);
        assertFalse(gestionnaireVagues.estDerniereVague(), "Dernière Vague non atteinte");


        environnement.setVagueActuelleProperty(15);
        assertTrue(gestionnaireVagues.estDerniereVague(), "Dernière Vague atteinte");
    }
}