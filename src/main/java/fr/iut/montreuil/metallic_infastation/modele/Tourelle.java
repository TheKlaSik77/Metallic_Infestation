package fr.iut.montreuil.metallic_infastation.modele;

public abstract class Tourelle {

    private int id;
    private int degats;
    private Case position;
    private int cout;
    private int portee;
    private Environnement env;
    private Ennemi ennemiVise;
    private Terrain terrain;
    private int compteur = 0;


    public Tourelle(int degats, Case position, int cout, int portee, Environnement env, Terrain terrain){
        this.compteur++;
        this.id = compteur;
        this.degats = degats;
        this.position = position;
        this.cout = cout;
        this.portee = portee;
        this.env = env;
        this.ennemiVise = null;
        this.terrain = terrain;
    }

    public Ennemi ennemiLePlusProche() {
        // Calcul si ennemi autour de toutes les cases par rapport à sa portée
        for (int zoneTest = 1; zoneTest <= portee; zoneTest++) {
            for (int i = zoneTest * -1; i <= zoneTest; i++) {
                for (int j = zoneTest * -1; j <= zoneTest; j++) {
                    if ((i == zoneTest || i == zoneTest * -1) || (j == zoneTest || j == zoneTest * -1)) {

                        Ennemi ennemiCase = env.ennemiSurCase(new Case(this.position.getI() + i, this.position.getJ() + j));
                        if (ennemiCase != null) {

                            return ennemiCase;
                        }
                    }
                }
            }
        }
        return null;
    }

    public void raffraichirEnnemiVise(){
        this.ennemiVise = this.ennemiLePlusProche();
    }
    public Ennemi getEnnemiVise(){
        return this.ennemiVise;
    }
    public void infligerDegats(){
        if(ennemiVise != null) {
            ennemiVise.decrementerPv(degats);
            System.out.println(ennemiVise.getPv());

        }

    }
}