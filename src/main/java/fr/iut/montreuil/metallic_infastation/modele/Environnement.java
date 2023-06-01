package fr.iut.montreuil.metallic_infastation.modele;

import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class Environnement {

    public static int vagueActuelle;
    private Terrain terrain;
    private ObservableList<Ennemi> listeEnnemis;
    private ObservableList<Tourelle> listeTourelles;
    private ParcoursBFS parcoursBFS;
    private final int NB_VAGUES_JEU = 3;
    private final Duration DELAI_LANCEMENT_VAGUE = Duration.ofSeconds(10);
    private Instant dernierLancementVague;

    boolean vagueEnCours = true;
    private int delaiEntreIterations = 5000; // Délai de 2 secondes (2000 millisecondes) entre chaque itération

    public void setDelaiEntreIterations(int delai) {
        this.delaiEntreIterations = delai;
    }


    public Environnement(Terrain terrain) {
        this.terrain = terrain;
        this.listeEnnemis = FXCollections.observableArrayList();
        this.listeTourelles = FXCollections.observableArrayList();
        this.parcoursBFS = new ParcoursBFS(terrain);
        vagueActuelle = 0;
        this.dernierLancementVague = Instant.now();
    }

    public ObservableList<Ennemi> getListeEnnemis() {
        return listeEnnemis;
    }

    public ObservableList<Tourelle> getListeTourelles() {
        return listeTourelles;
    }

    public Ennemi ennemiSurCase(Case c) {
        for (Ennemi e : listeEnnemis) {
            if (e.getCase().caseEgale(c)) {
                return e;
            }
        }
        return null;
    }


    public void ajouterDansListeTours(Tourelle t) {
        listeTourelles.add(t);

    }

    public void spawnEnnemisVague(Terrain terrain) {
        Random random = new Random();
        int nombreEnnemis = 10;
        for (int i = 0; i < nombreEnnemis; i++) {
            int typeEnnemi = random.nextInt(3);
            System.out.println("Type d'ennemi : " + typeEnnemi);

            switch (typeEnnemi) {
                case 0:
                    EnnemiFacile ennemiFacile = new EnnemiFacile(parcoursBFS, terrain);
                    listeEnnemis.add(ennemiFacile);
                    break;
                case 1:
                    EnnemiMoyen ennemiMoyen = new EnnemiMoyen(parcoursBFS, terrain);
                    listeEnnemis.add(ennemiMoyen);
                    break;
                case 2:
                    EnnemiDifficile ennemiDifficile = new EnnemiDifficile(parcoursBFS, terrain);
                    listeEnnemis.add(ennemiDifficile);
                    break;
            }
        }
    }

    public Tourelle retirerTour(Case c) {
        Tourelle supprimee = null;
        for (int i = this.getListeTourelles().size() - 1; i >= 0; i--) {
            if (this.getListeTourelles().get(i).getPosition().caseEgale(c)) {
                supprimee = this.getListeTourelles().get(i);
                this.getListeTourelles().remove(i);
            }
        }
        return supprimee;
    }

    /**
     * public void lancerProchaineVague(Terrain terrain) {
     * if (vagueActuelle < NB_VAGUES_JEU) {
     * vagueActuelle++;
     * if (!this.estDerniereVague()) {
     * System.out.println("Une vague ennemie se prépare...");
     * System.out.println("Vague actuelle : " + (vagueActuelle + 1));
     * spawnEnnemisVague(terrain);
     * }
     * }
     * <p>
     * }
     */

    public void lancerVague() {
        System.out.println("Une vague ennemie se prépare...");
        System.out.println("Vague actuelle : " + (vagueActuelle + 1));
        spawnEnnemisVague(terrain);
        for (int idEnnemi = this.getListeEnnemis().size() - 1; idEnnemi >= 0; idEnnemi--) {
            Ennemi e = this.getListeEnnemis().get(idEnnemi);
            e.seDeplacer();
            if (e.aAtteintLaCible() || e.estMort()) {
                this.getListeEnnemis().remove(e);
            }
        }
        if(listeEnnemis.isEmpty()){
            vagueEnCours = false;
        }
    }

    public boolean estDerniereVague() {
        return vagueActuelle >= NB_VAGUES_JEU;
    }

}


