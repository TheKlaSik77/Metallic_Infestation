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


    public Ennemi (Point coordonnees, int pv, int vitesse, int drop, Terrain terrain){
        super(coordonnees,vitesse);
        this.id = compteur;
        this.pv = pv;
        this.vitesse = vitesse;
        // Piece Lootées par les ennemis
        this.drop = drop;
        this.terrain = terrain;
        // Position de Départ Aléatoire
        boolean coordonneesChemin;
        do {
            double rand = Math.random();

            // Faire apparaitre en aleatoire sur coté gauche
            if (rand < 0.5){
                int randY = (int)(Math.random()*737);
                this.coordonnees = new Point(0,randY);
                coordonneesChemin = terrain.cheminSurCase(new Case((int)(randY) / terrain.getTailleCase(),0));
            //Faire apparaitre en aleatoire sur coté haut
            } else {
                int randX = (int)(Math.random()*737);
                this.coordonnees = new Point(randX,0);
                coordonneesChemin = terrain.cheminSurCase(new Case(0,(int)(randX) / terrain.getTailleCase()));
            }
        } while (!coordonneesChemin);
        System.out.println("coordonée :  " + this.coordonnees.toString()  );


        // TODO : Charger une seule fois le BFS
        this.parcoursBFS = new ParcoursBFS(terrain);
        parcoursBFS.remplirGrilleBFS();
        this.caseDestination = parcoursBFS.caseLaPlusProcheDArrivee(this.getCase());

        System.out.println("Case de destination : " + caseDestination.toString());
        compteur++;
    }
    public int getPv(){
        return this.pv;
    }

    public int getId() {
        return id;
    }
    public Point getCoordonnees(){
        return this.coordonnees;
    }

    public Case getCase(){
        int i = this.coordonnees.getY() / 32;
        int j = this.coordonnees.getX() / 32;
        return new Case(i,j);
    }

    public void seDeplacer() {
        int distanceX = this.caseDestination.getJ() * terrain.getTailleCase() - this.getCoordonnees().getX();
        int distanceY = this.caseDestination.getI() * terrain.getTailleCase() - this.getCoordonnees().getY();

        int deplacementX = Math.min(vitesse, Math.abs(distanceX));
        int deplacementY = Math.min(vitesse, Math.abs(distanceY));

        if (distanceX < 0) {
            this.coordonnees.setX(this.coordonnees.getX() - deplacementX);
        } else if (distanceX > 0) {
            this.coordonnees.setX(this.coordonnees.getX() + deplacementX);
        }

        if (distanceY < 0) {
            this.coordonnees.setY(this.coordonnees.getY() - deplacementY);
        } else if (distanceY > 0) {
            this.coordonnees.setY(this.coordonnees.getY() + deplacementY);
        }

        if (this.getCase().caseEgale(this.caseDestination)) {
            this.caseDestination = parcoursBFS.caseLaPlusProcheDArrivee(this.caseDestination);
        }
    }
    /**
     *
     * @param n
     * Décrémente de n pv à l'ennemi
     */
    public void decrementerPv(int n){
        this.pv -= n;
    }
    /**
     *
     * @return true si les pv sont <= 0
     */

    public boolean estMort(){
        return this.pv <= 0;
    }

    public boolean aAtteintLaCible(){
        return  this.terrain.arriveeSurCase(this.getCase());
    }

    @Override
    public String toString() {
        return " {" +
                "id=" + id +
                ", coordonnees=" + coordonnees +
                '}';
    }

}
