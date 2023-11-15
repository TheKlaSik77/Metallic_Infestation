package fr.iut.montreuil.metallic_infestation.connexion;

import fr.iut.montreuil.metallic_infestation.connexion.Parametres.ParametresAdministrateur;
import fr.iut.montreuil.metallic_infestation.connexion.Parametres.ParametresUtilisateur;
import fr.iut.montreuil.metallic_infestation.connexion.exception.UtilisateurDejaConnecté;
import fr.iut.montreuil.metallic_infestation.connexion.exception.UtilisateurIntrouvable;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;

import java.sql.*;
import java.util.Scanner;

public class Connexion {

    //etape 1 : connexion à la bd via administrateur
    //etape 2 : pour que l'utilisateur puisse sauvegarder ou charger : enregistrement dans la table Utilisateurs
    //présence du login de l'utilisateur dans toutes les tables (appel clé étrangère)

    private static Connexion uniqueInstance = null;
    private ParametresAdministrateur prmtr_admin;
    private Connection connexion;

    private Utilisateur user_connecte;

    private Connexion() {

        this.prmtr_admin = new ParametresAdministrateur();
        this.connexion = null;
        this.user_connecte = null; //pour le moment on ne sauvegarde pas l'utilisateur en local

        try {
            connexion = DriverManager.getConnection(prmtr_admin.getUrl(),prmtr_admin.getLogin(), prmtr_admin.getPassword());
            System.out.println("Connexion au serveur");
        }
        catch (Exception e){
            System.out.println("Impossible de se connecter au serveur");
        }

    }

    public static Connexion getInstance(){
        if (uniqueInstance==null){
            uniqueInstance = new Connexion();
        }
        return uniqueInstance;
    }

    public int creationCompte(String login, String mdp) throws SQLException {

        int resultat = 0;

        String query = "INSERT INTO metallic.Utilisateur (login,mdp) VALUES (?, ?);";
        try (PreparedStatement preparedStatement = connexion.prepareStatement(query)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, mdp);
            resultat = preparedStatement.executeUpdate();
            preparedStatement.close();

            if (resultat > 0) {
                System.out.println("Utilisateur créé");
            } else {
                System.out.println("Échec de la création du compte");
            }
        }

        return resultat;
    }

    public void creerUtilisateur() throws SQLException, UtilisateurIntrouvable {

        //pour l'instant avec le terminal, mettre une fenêtre plus tard
       Scanner scanner = new Scanner(System.in).useDelimiter("\n");
       String login;
       String mdp;

       System.out.println("Veuillez saisir votre login : ");
       login = scanner.nextLine();
       System.out.println("Veuillez saisir votre mot de passe : ");
       mdp = scanner.nextLine();

        if(creationCompte(login,mdp) == 1){
            this.user_connecte = new Utilisateur(new ParametresUtilisateur(login,mdp));
        }
    }

    public void chargerUtilisateur() throws UtilisateurDejaConnecté {

        if(this.user_connecte != null){
            throw new UtilisateurDejaConnecté();
        }

        //pour l'instant avec le terminal, mettre une fenêtre plus tard
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        String login;
        String mdp;

        System.out.println("Veuillez saisir votre login : ");
        login = scanner.nextLine();
        System.out.println("Veuillez saisir votre mot de passe : ");
        mdp = scanner.nextLine();

        ResultSet resultat;
        String query = "SELECT * FROM metallic.Utilisateur WHERE login = ? AND mdp = ?";

        try (PreparedStatement preparedStatement = this.connexion.prepareStatement(query)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, mdp);
            resultat = preparedStatement.executeQuery();

            while (resultat.next()) {
               String loginTrouvé = resultat.getString("login");
               String mdpTrouvé = resultat.getString("mdp");
                this.user_connecte = new Utilisateur(new ParametresUtilisateur(loginTrouvé,mdpTrouvé));
            }

            preparedStatement.close();
        } catch (SQLException e) {System.out.println("Utilisateur inconnu");} catch (UtilisateurIntrouvable e) {
            throw new RuntimeException(e);
        }

    }

    public void deconnexionUtilisateur(){this.user_connecte = null;}

    public Connection getConnexion() {
        return connexion;
    }
    public Utilisateur getUser_connecte() {
        return user_connecte;
    }

    public boolean utilisateurConnecte(){return this.user_connecte!=null;}

}
