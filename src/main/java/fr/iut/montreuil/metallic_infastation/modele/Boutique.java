package fr.iut.montreuil.metallic_infastation.modele;

public class Boutique {
    Joueur joueur;
    Environnement environnement;

    //Arraylist<Tourelle> listeTourelleDisponible

    public Boutique (Joueur joueur, Environnement environnement){
        this.environnement = environnement;
        this.joueur = joueur;
    }

    public void AchatPv (int montant, int pv) {
        if (joueur.achatPossible(montant) == true){
            joueur.crediterPvJoueurProperty(pv);
            joueur.debiterArgentProperty(montant);
        }
    }

    public void AchatTour(int idTour){
        if (idTour == 1){

        }
        else if (idTour == 2) {

        }
        else {

        }
    }
}
