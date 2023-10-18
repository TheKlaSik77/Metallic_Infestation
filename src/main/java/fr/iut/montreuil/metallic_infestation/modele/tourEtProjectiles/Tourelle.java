package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.ProjectileMissile;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.ProjectileSemi;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

import java.util.ArrayList;



public abstract class Tourelle extends ElementNonDeplacable {

    private int id;
    private int degats;
    private int cout;
    private int porteeTourelle;

    private Ennemi ennemiVise;

    private int compteur = 0;

    private int porteeMissile;


    public Tourelle(int degats, Case position, int cout, int porteeTourelle, int porteeMissile){
        super(position);
        this.compteur++;
        this.id = compteur;
        this.degats = degats;
        this.cout = cout;
        this.porteeTourelle = porteeTourelle;
        this.ennemiVise = null;
        this.porteeMissile = porteeMissile;
    }


    public Ennemi ennemiLePlusProche() {
        ArrayList<Ennemi> ennemisLesPlusProches = ennemisLesPlusProches(position, porteeTourelle);

        if (!ennemisLesPlusProches.isEmpty()) {
            return ennemisLesPlusProches.get(0);
        }

        return null;
    }

    public ArrayList<Ennemi> ennemisLesPlusProches(Case emplacement, int portee) {
        ArrayList<Ennemi> ennemisLesPlusProches = new ArrayList<>();

        for (int zoneTest = 1; zoneTest <= portee; zoneTest++) {
            for (int i = zoneTest * -1; i <= zoneTest; i++) {
                for (int j = zoneTest * -1; j <= zoneTest; j++) {
                    if ((i == zoneTest || i == zoneTest * -1) || (j == zoneTest || j == zoneTest * -1)) {

                        Ennemi ennemiCase = environnement.ennemiSurCase(new Case(emplacement.getI() + i, emplacement.getJ() + j));
                        if (ennemiCase != null) {
                            ennemisLesPlusProches.add(ennemiCase);
                        }
                    }
                }
            }
        }
        return ennemisLesPlusProches;
    }



    public void raffraichirEnnemiVise(){
        this.ennemiVise = this.ennemiLePlusProche();
    }
    public Ennemi getEnnemiVise(){
        return this.ennemiVise;
    }


    public abstract void infligerDegats();
    public int getCout (){return this.cout;}

    public int getDegats(){return this.degats;}

    public int getPorteeMissile(){return this.porteeMissile;}

    public void poserTourelle(){
        if (this.environnement.getTerrain().emplacementVideSurCase(this.getPosition())){
            // On dit que la case est occupée par une tour
            this.environnement.getTerrain().setCase(this.getPosition(),3);
        }
    }
    public ProjectileSemi creerProjectile(){
        return new ProjectileSemi(this,this.ennemiVise);
    }

    public ProjectileMissile creerProjectileMissile() {return new ProjectileMissile(this, this.ennemiVise);}

}
