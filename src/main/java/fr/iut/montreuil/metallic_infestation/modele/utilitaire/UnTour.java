package fr.iut.montreuil.metallic_infestation.modele.utilitaire;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Mine;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Obstacle;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Pics;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.*;

import java.util.ArrayList;

public class UnTour {

    private Ennemi ennemi;
    private ArrayList<Ennemi> ennemisASupp;
    private ArrayList<Projectile> listeProjectilesASupp;
    private ArrayList<Obstacle> listeObstaclesASupp;

    public int nbTours;

    public UnTour() {
        this.ennemisASupp = new ArrayList<>();
        this.listeObstaclesASupp = new ArrayList<>();
        this.listeProjectilesASupp = new ArrayList<>();
        this.ennemi = null;
        this.nbTours = 1;
    }

    public void unTour() {

        Environnement env = Environnement.getInstance();

        if (this.getNbTours() % 700 == 0 || this.getNbTours() == 100) {
            env.setEnnemisASpawn(env.getGestionnaireVagues().lancerProchaineVague(env.getTerrain()));
        }

        if (this.getNbTours() % 20 == 0 && !env.getEnnemisASpawn().isEmpty()) {
            env.getListeEnnemis().add(env.getEnnemisASpawn().remove(env.getEnnemisASpawn().size() - 1));
        }

        if (this.getNbTours() % 2 == 0) {
            actionEnnemis(env);
        }

        actionProjectiles(env);

        for (Tourelle t: env.getListeTourelles()){
            t.raffraichirEnnemi();
            if (t.getEnnemisCibles() != null) {
                Projectile p = t.creerProjectile();
                env.ajouterProjectile(p);
            }

        }


        actionObstacles(env);
        nettoyageFinDeTour(env);
        this.nbTours++;
    }

    public void actionEnnemis(Environnement env){

        for (int idEnnemi = env.getListeEnnemis().size() - 1; idEnnemi >= 0; idEnnemi--) {
            Ennemi e = env.getListeEnnemis().get(idEnnemi);
            e.seDeplacer();
            if (e.aAtteintLaCible()) {
                ennemisASupp.add(e);
                env.getJoueur().debiterPvJoueurProperty(e.getDrop());
            } else if (e.estMort()) {
                ennemisASupp.add(e);
                env.getJoueur().crediterArgentProperty(e.getDrop());
            }
        }
    }

    public void actionProjectiles(Environnement env){
    //TODO: Modifier
        /*
        if (this.getNbTours() % 2 == 0) {
            for (Projectile p : env.getListeProjectiles()) {
                p.seDeplacer();
                if (p.arriveSurEnnemi()) {
                    if (p instanceof ProjectileMissile){
                        env.getListExplosions().add(((ProjectileMissile) p).creerExplosion());
                    }
                    p.getTourelle().infligerDegats();
                    listeProjectilesASupp.add(p);
                }
            }
        }

         */
    }

    public void actionObstacles(Environnement env){
        if (!env.getListeObstacles().isEmpty()) {

            for (int i = env.getListeObstacles().size() - 1; i >= 0; i--) {

                for (Ennemi e : env.getListeEnnemis()) {
                    this.ennemi = e;
                    if (env.getListeObstacles().get(i).ennemisSurObstacle()) {
                        env.getListeObstacles().get(i).actionObstacle();
                    }
                }
            }
        }
    }

    public void nettoyageFinDeTour(Environnement env){
        //TODO : Modifier
        /*
        for (Laser l : env.getListeLasers()){
            if (l.getEnnemiVise() == null){
                env.getListeLasers().clear();
            }
        }
        for (Projectile p : listeProjectilesASupp) {
            env.retirerProjectile(p);
        }

        for (Obstacle o : listeObstaclesASupp) {
            env.getListeObstacles().remove(o);
        }

        for (Ennemi e : ennemisASupp){
            env.retirerEnnemi(e);
        }
        if(this.getNbTours() % 2 == 0) {
            env.getListeLasers().clear();
        }
        for (Ennemi e : env.getListeEnnemis()){
            if (e.estSurChemin()){
                e.retablirVitesse();
            }
        }

         */
        
    }

    public Ennemi getEnnemi(){return this.ennemi;}

    public ArrayList<Obstacle> getObstaclesASupp(){return this.listeObstaclesASupp;}

    public int getNbTours(){return this.nbTours;}
    
}
