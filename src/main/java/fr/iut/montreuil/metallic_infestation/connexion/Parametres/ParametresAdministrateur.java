package fr.iut.montreuil.metallic_infestation.connexion.Parametres;

public class ParametresAdministrateur {

    private String url;
    private String login;

    private String password;

    public ParametresAdministrateur() {
        this.url = "jdbc:postgresql://localhost:5432/metallic";
        this.login = "metal";
        this.password = "admin";
    }

    public String getUrl() {
        return url;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
