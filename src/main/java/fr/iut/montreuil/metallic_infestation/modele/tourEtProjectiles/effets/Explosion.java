package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.effets;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;

import java.util.ArrayList;

public class Explosion extends Degat{

    private int porteeExplosion;
    private Environnement environnement;
    public Explosion(int porteeExplosion, int degat, Environnement env){
        super(degat);
        this.porteeExplosion = porteeExplosion;
        this.environnement = env;
    }
    public ArrayList<Ennemi> ennemisAPortee(Case emplacement, int portee) {
        ArrayList<Ennemi> ennemisLesPlusProches = new ArrayList<>();
        for (int i = portee * -1; i <= portee; i++) {
            for (int j = portee * -1; j <= portee; j++) {
                Ennemi ennemiCase = environnement.ennemiSurCase(new Case(emplacement.getI() + i, emplacement.getJ() + j));
                if (ennemiCase != null) {
                    ennemisLesPlusProches.add(ennemiCase);
                }
            }
        }
        return ennemisLesPlusProches;
    }
    @Override
    public void appliquerEffet(Ennemi ennemi) {
        ArrayList<Ennemi> ennemisTouches = ennemisAPortee(ennemi.getCase(),this.porteeExplosion);
        for (Ennemi e : ennemisTouches){
            e.decrementerPv(this.getDegat());
        }
    }


}
