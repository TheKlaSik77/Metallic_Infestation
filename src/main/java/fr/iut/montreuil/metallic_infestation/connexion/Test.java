package fr.iut.montreuil.metallic_infestation.connexion;

import fr.iut.montreuil.metallic_infestation.connexion.Parametres.ParametresAdministrateur;
import fr.iut.montreuil.metallic_infestation.connexion.exception.UtilisateurDejaConnecté;
import fr.iut.montreuil.metallic_infestation.connexion.exception.UtilisateurIntrouvable;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.LiaisonEntreLeMenuEtLeJeu;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException, UtilisateurIntrouvable, UtilisateurDejaConnecté {
        Connexion connexion = Connexion.getInstance();
        connexion.creerUtilisateur();

        //Pour charger un nouvel Environnement
        //MAIDE ON VA DEVOIR FAIRE DES RESET DES SINGLETONS SI ON CHARGE :O !!!!!
        ///AAAAAAAAAAAAAH !!!!


        LiaisonEntreLeMenuEtLeJeu.nbTerrain = 2;

/*        MenuConnexion menu = new MenuConnexion();
        menu.menu();*/
    }
}
