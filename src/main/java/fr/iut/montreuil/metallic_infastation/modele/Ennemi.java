package fr.iut.montreuil.metallic_infastation.modele;

public abstract class Ennemi extends ElementDeplacable{
    private static int compteur = 0;
    private int id;
    private int pv;
    private int drop;

    private int vitesse;
    private Terrain terrain;
    private ParcoursBFS parcoursBFS;
    private Case caseDestination;

    private Point coordonnees;

    public Ennemi (int pv, int vitesse, int drop, ParcoursBFS parcoursBFS, Terrain terrain){
        super(new Point(0,0),vitesse);
        this.id = compteur;
        this.pv = pv;
        this.vitesse = vitesse;
        this.drop = drop;
        this.terrain = terrain;
        boolean coordonneesChemin;
        this.coordonnees = new Point(0, 0);
        do {
            double rand = Math.random();
            // Faire apparaitre en aleatoire sur coté gauche
            if (rand < 0.5){
                int randY = (int)(Math.random()*736);
                this.coordonnees = new Point(0,randY);
                coordonneesChemin = terrain.cheminSurCase(new Case((int)(randY) / terrain.getTailleCase(),0));
            //Faire apparaitre en aleatoire sur coté haut
            } else {
                int randX = (int)(Math.random()*736);
                this.coordonnees = new Point(randX,0);
                coordonneesChemin = terrain.cheminSurCase(new Case(0,(int)(randX) / terrain.getTailleCase()));
            }
        } while (!coordonneesChemin);
        this.parcoursBFS = parcoursBFS;
        parcoursBFS.remplirGrilleBFS();
        this.caseDestination = parcoursBFS.caseLaPlusProcheDArrivee(this.getCase());
        compteur++;
    }

    public int getPv() {
        return this.pv;
    }

    public int getId() {
        return id;
    }

    public Point getCoordonnees() {
        return this.coordonnees;
    }


    public Case getCase(){
        return this.getCoordonnees().getCase();
    }

    public void seDeplacer() {
        if (this.getCoordonnees().getX() < this.caseDestination.getJ() * terrain.getTailleCase()){
            this.coordonnees.setX(this.coordonnees.getX()+vitesse);


        } else if (this.getCoordonnees().getX() > this.caseDestination.getJ() * terrain.getTailleCase()){
            this.coordonnees.setX(this.coordonnees.getX()-vitesse);

        }
        if (this.getCoordonnees().getY() < this.caseDestination.getI() * terrain.getTailleCase()){
            this.coordonnees.setY(this.coordonnees.getY()+vitesse);

        } else if (this.getCoordonnees().getY() > this.caseDestination.getI() * terrain.getTailleCase()){
            this.coordonnees.setY(this.coordonnees.getY()-vitesse);

        }
        if (this.getCase().caseEgale(this.caseDestination)){
            this.caseDestination = parcoursBFS.caseLaPlusProcheDArrivee(this.caseDestination);
        }

    }

    /**
     * @param n Décrémente de n pv à l'ennemi
     */
    public void decrementerPv(int n) {
        this.pv -= n;
    }

    /**
     * @return true si les pv sont <= 0
     */

    public boolean estMort() {
        return this.pv <= 0;
    }

    public boolean aAtteintLaCible() {
        return this.terrain.arriveeSurCase(this.getCase());
    }

    @Override
    public String toString() {
        return " {" +
                "id=" + id +
                ", coordonnees=" + coordonnees +
                '}';
    }

}