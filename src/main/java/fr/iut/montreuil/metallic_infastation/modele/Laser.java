package fr.iut.montreuil.metallic_infastation.modele;

import fr.iut.montreuil.metallic_infastation.vue.LaserVue;

public class Laser {
    private Environnement environnement;
    private LaserVue laserVue;

    public Laser(Environnement environnement, LaserVue laserVue) {
        this.environnement = environnement;
        this.laserVue = laserVue;
    }

    public void  gestionLaser(){
        for (Tourelle t: environnement.getListeTourelles()) {
            if (t.estEntrainDeTirer()){
                for (Ennemi e: t.getListeDesEnnemisVisées()) {
                    laserVue.creerLaser(t.getCoordonnées(), e.getCoordonnees());
                }
            }
        }
    }
}
