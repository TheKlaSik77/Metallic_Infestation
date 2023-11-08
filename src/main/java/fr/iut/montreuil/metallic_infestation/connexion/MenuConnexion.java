package fr.iut.montreuil.metallic_infestation.connexion;

import fr.iut.montreuil.metallic_infestation.connexion.exception.UtilisateurDejaConnecté;
import fr.iut.montreuil.metallic_infestation.connexion.exception.UtilisateurIntrouvable;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuConnexion {
    private Connexion connexion;
    public MenuConnexion() {
        this.connexion = Connexion.getInstance();
    }

    //En attendant une interface graphique
    public void menu() throws UtilisateurDejaConnecté, SQLException, UtilisateurIntrouvable {

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        int choix;
        System.out.println("Se connecter : 1");
        System.out.println("Nouvel utilisateur : 2");
        System.out.println("Se déconnecter : 3");
        System.out.println("Revenir au jeu : 4");
        choix = scanner.nextInt();

        switch (choix){
            case 1:
                this.connexion.chargerUtilisateur();
                break;
            case 2:
                this.connexion.creerUtilisateur();
                break;
            case 3:
                this.connexion.deconnexionUtilisateur();
                break;
            default:
        }

    }


}
