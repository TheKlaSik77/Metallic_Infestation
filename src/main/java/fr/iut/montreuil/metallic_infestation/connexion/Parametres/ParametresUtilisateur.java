package fr.iut.montreuil.metallic_infestation.connexion.Parametres;

import fr.iut.montreuil.metallic_infestation.connexion.Connexion;
import fr.iut.montreuil.metallic_infestation.connexion.exception.UtilisateurIntrouvable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParametresUtilisateur {

    private static Connexion connexion = Connexion.getInstance();
    private int idUser;
    private String login;
    private String password;

    public ParametresUtilisateur(String login, String password) throws UtilisateurIntrouvable {
        this.login = login;
        this.password = password;
        idUser(login,password);
        if(this.idUser == -1){throw new UtilisateurIntrouvable();}
    }

    public void idUser(String login, String password) {
        this.idUser = -1;
        ResultSet resultat = null;

        String query = "SELECT idUser FROM metallic.Utilisateur WHERE login = ? AND mdp = ?";

        try (PreparedStatement preparedStatement = connexion.getConnexion().prepareStatement(query)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultat = preparedStatement.executeQuery();

            while (resultat.next()) {
                this.idUser = resultat.getInt("idUser");
            }
        } catch (SQLException e) {System.out.println("Utilisateur non trouvé");}
    }

    public int getIdUser() {
        return idUser;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
