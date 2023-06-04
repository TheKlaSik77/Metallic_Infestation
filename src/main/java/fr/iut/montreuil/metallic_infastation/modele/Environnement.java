package fr.iut.montreuil.metallic_infastation.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Random;

public class Environnement {

    public static int vagueActuelle;
    private Terrain terrain;
    private ObservableList<Ennemi> listeEnnemis;
    private ObservableList<Tourelle> listeTourelles;

    private ObservableList<Laser> listeLasers;
    private ParcoursBFS parcoursBFS;

    private Joueur joueur;

    public Environnement(Terrain terrain) {
        this.terrain = terrain;
        this.listeEnnemis = FXCollections.observableArrayList();
        this.listeTourelles = FXCollections.observableArrayList();
        this.listeLasers = FXCollections.observableArrayList();
        this.parcoursBFS = new ParcoursBFS(terrain);
        this.joueur = new Joueur(100,1000);
        vagueActuelle = 0;
    }

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

    public void ajouterDansListeTours(Tourelle t){
            listeTourelles.add(t);

    }

    public void lancerVague(Terrain terrain) {
        Random random = new Random();
        int nombreEnnemis = 10;

        for (int i = 0; i < nombreEnnemis; i++) {
            int typeEnnemi = random.nextInt(3);
            System.out.println("Type d'ennemi : " + typeEnnemi);

            switch (typeEnnemi) {
                case 0:
                    EnnemiFacile ennemiFacile = new EnnemiFacile(parcoursBFS,terrain);
                    listeEnnemis.add(ennemiFacile);
                    break;
                case 1:
                    EnnemiMoyen ennemiMoyen = new EnnemiMoyen(parcoursBFS,terrain);
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
        for (int i = this.getListeTourelles().size() - 1 ; i >= 0 ; i--){
            if (this.getListeTourelles().get(i).getPosition().caseEgale(c)){
                supprimee = this.getListeTourelles().get(i);
                this.getListeTourelles().remove(i);
            }
        }
        return supprimee;
    }

    public Joueur getJoueur() {
        return this.joueur;
    }

    public void ajouterLaser(Laser p){
        if (p != null) {
            if (p.getEnnemiVise() != null && p.getTourelle() != null) {
                listeLasers.add(p);
            }
        }
    }

    public boolean destEstPresent (Ennemi e){
        for (Laser l: listeLasers) {
            if (e == l.getEnnemiVise()){
                return true;
            }
        }
        return false;
    }

    public void retirerLaser (Ennemi e){
        for (int i = listeLasers.size()-1 ; i > 0 ; i--){
            if (listeLasers.get(i).getEnnemiVise() == e){
                listeLasers.remove(listeLasers.get(i));
                break;
            }
        }
    }
}


