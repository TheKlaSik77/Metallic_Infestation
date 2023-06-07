package fr.iut.montreuil.metallic_infastation.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Random;

public class Environnement {

    final static int NOMBRE_VAGUES_POUR_ENNEMI_DIFFICILE = 3;
    final static int NOMBRE_ENNEMIS_DIFFICILES_SUPPLEMENTAIRES = 5;
    public static int vagueActuelle;
<<<<<<< HEAD

=======
    private final Joueur joueur;
>>>>>>> f92e48873891146e24b1351484d8cca0a1dfd3a3
    private Terrain terrain;
    private ObservableList<Ennemi> listeEnnemis;
    private ObservableList<Tourelle> listeTourelles;
    private ObservableList<Projectile> listeProjectiles;
    private ParcoursBFS parcoursBFS;
<<<<<<< HEAD
    public static int nbTours;

=======
    public int nbTours;
    private ObservableList<Laser> listeLasers;
>>>>>>> f92e48873891146e24b1351484d8cca0a1dfd3a3

    public Environnement(Terrain terrain) {
        this.terrain = terrain;
        this.listeEnnemis = FXCollections.observableArrayList();
        this.listeTourelles = FXCollections.observableArrayList();
        this.listeProjectiles = FXCollections.observableArrayList();
        this.listeLasers = FXCollections.observableArrayList();
        this.parcoursBFS = new ParcoursBFS(terrain);
        this.joueur = new Joueur(100,1000);
        vagueActuelle = 0;
        nbTours = 1;
    }

    /**
     * public unTour(){
     * if(nbTours%vitesse==0)
     * }
     */


    public ObservableList<Ennemi> getListeEnnemis() {
        return listeEnnemis;
    }

    public ObservableList<Tourelle> getListeTourelles() {
        return listeTourelles;
    }

    public ObservableList<Laser> getListeLasers(){
        return listeLasers;
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

    public ObservableList<Projectile> getListeProjectiles() {
        return listeProjectiles;
    }

    public void ajouterProjectile(Projectile p) {
        listeProjectiles.add(p);
    }

    public Projectile retirerProjectile(Projectile p) {
        Projectile supprime = null;
        for (int i = this.getListeProjectiles().size() - 1; i >= 0; i--) {
            if (this.getListeProjectiles().get(i).getCoordonnees().equals(p.getCoordonnees())) {
                supprime = this.getListeProjectiles().get(i);
                this.getListeProjectiles().remove(i);
            }
        }
        return supprime;
    }

    public void lancerVague(Terrain terrain) {
        Random random = new Random();
        int nombreEnnemis = 10;

        if (vagueActuelle % NOMBRE_VAGUES_POUR_ENNEMI_DIFFICILE == 0) {
            nombreEnnemis += NOMBRE_ENNEMIS_DIFFICILES_SUPPLEMENTAIRES;
        }
        for (int i = 0; i < nombreEnnemis; i++) {
            int typeEnnemi = random.nextInt(3);

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

    /**
     * Regarde si ennemi est déjà visé par un laser
     * @param e
     * @return
     */
    public boolean destEstPresent (Ennemi e){
        for (Laser l: listeLasers) {
            if (e == l.getEnnemiVise()){
                return true;
            }
        }
        return false;
    }

    public void retirerLaser(Laser l){
        for (int i = listeLasers.size()-1 ; i >= 0 ; i--){
            if (listeLasers.get(i).equals(l)){
                this.listeLasers.remove(listeLasers.get(i));
            }
        }
    }
}


>>>>>>> f92e48873891146e24b1351484d8cca0a1dfd3a3
