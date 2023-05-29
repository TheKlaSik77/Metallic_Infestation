package fr.iut.montreuil.metallic_infastation.modele;

public class Essais {
    public static void main(String[] args) {

        Ennemi ennemi = new EnnemiFacile(new Terrain());
        Joueur joueur = new Joueur(10, 5);
        System.out.println(joueur.pvJoueurProprerty());
        joueur.crediterPvJoueurProperty(2);
        System.out.println(joueur.pvJoueurProprerty());
        System.out.println(joueur.argentProperty());
        joueur.crediterArgentProperty(2);
        System.out.println(joueur.argentProperty());
    }
}
