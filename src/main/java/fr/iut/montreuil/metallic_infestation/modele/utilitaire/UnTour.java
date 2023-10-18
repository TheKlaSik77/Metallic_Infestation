package fr.iut.montreuil.metallic_infestation.modele.utilitaire;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Mine;
import fr.iut.montreuil.metallic_infestation.modele.obstacles.Pics;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.*;

import java.util.ArrayList;

public class UnTour {

    private ArrayList<Ennemi> ennemisASupp;
    private ArrayList<Projectile> listeProjectilesASupp;
    public UnTour() {
        this.ennemisASupp = new ArrayList<>();
        this.listeProjectilesASupp = new ArrayList<>();
    }

    public void unTour() {

        Environnement env = Environnement.getInstance();

        if (env.getNbTours() % 700 == 0 || env.getNbTours() == 100) {
            env.setEnnemisASpawn(env.getGestionnaireVagues().lancerProchaineVague(env.getTerrain()));
        }

        if (env.getNbTours() % 20 == 0 && !env.getEnnemisASpawn().isEmpty()) {
            env.getListeEnnemis().add(env.getEnnemisASpawn().remove(env.getEnnemisASpawn().size() - 1));
        }

        if (env.getNbTours() % 2 == 0) {
            actionEnnemis(env);
        }

        actionProjectiles(env);

        if (env.getNbTours() % 20 == 0) {
            for (Tourelle t : env.getListeTourelles()) {
                if (t instanceof TourelleSemi) {
                    t.raffraichirEnnemiVise();
                    if (t.getEnnemiVise() != null) {
                        Projectile p = t.creerProjectile();
                        env.ajouterProjectile(p);
                    }
                }
            }
        }

        if (env.getNbTours() % 100 == 0){
            for (Tourelle t: env.getListeTourelles()){
                if (t instanceof TourelleMissiles) {
                    t.raffraichirEnnemiVise();
                    if (t.getEnnemiVise() != null) {
                        Projectile p = t.creerProjectileMissile();
                        env.ajouterProjectile(p);
                    }
                }
            }
        }
        
        for (Tourelle t : env.getListeTourelles()) {
            if (t instanceof TourelleAuto) {
                t.raffraichirEnnemiVise();
                if (t.getEnnemiVise() != null) {
                    Laser l = ((TourelleAuto) t).creerLaser();
                    env.ajouterLaser(l);
                    t.infligerDegats();
                }
            }
        }

        actionObstacles(env);
        nettoyageFinDeTour(env);
        env.incrementeNbTours();
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

        if (env.getNbTours() % 2 == 0) {
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
    }

    public void actionObstacles(Environnement env){
        if (!env.getListeObstacles().isEmpty()) {
            for (int i = env.getListeObstacles().size() - 1; i >= 0; i--) {
                for (Ennemi e : env.getListeEnnemis()) {
                    if (env.getListeObstacles().get(i).ennemisSurObstacle()) {
                        if (env.getListeObstacles().get(i) instanceof Pics) {
                            if (env.getListeObstacles().get(i).ennemisSurObstacle()) {
                                ((Pics) env.getListeObstacles().get(i)).actionnerPics(e);
                            }
                        } else if (env.getListeObstacles().get(i) instanceof Mine) {
                            env.getTerrain().setCase(env.getListeObstacles().get(i).getPosition(), 1);
                            Explosion explosion = new Explosion(env.getListeObstacles().get(i).getPosition().getCentreCase(), ((Mine) env.getListeObstacles().get(i)).getDegats(), ((Mine) env.getListeObstacles().get(i)).getPorteeExplosion());
                            env.getListExplosions().add(explosion);
                            explosion.infligerDegats();
                            env.getListeObstacles().remove(env.getListeObstacles().get(i));
                            break;
                        }
                    }

                }
            }
        }
    }

    public void nettoyageFinDeTour(Environnement env){

        for (Laser l : env.getListeLasers()){
            if (l.getEnnemiVise() == null){
                env.getListeLasers().clear();
            }
        }
        for (Projectile p : listeProjectilesASupp) {
            env.retirerProjectile(p);
        }
        for (Ennemi e : ennemisASupp){
            env.retirerEnnemi(e);
        }
        if(env.getNbTours() % 2 == 0) {
            env.getListeLasers().clear();
        }
        for (Ennemi e : env.getListeEnnemis()){
            if (e.estSurChemin()){
                e.retablirVitesse();
            }
        }
        
    }
    
}
