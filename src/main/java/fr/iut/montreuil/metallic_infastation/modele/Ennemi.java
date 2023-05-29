package fr.iut.montreuil.metallic_infastation.modele;

public abstract class Ennemi {
    private static int compteur = 0;
    private int id;
    private int pv;
    private double vitesse;
    private Point coordonnees;
    private Terrain terrain;
    private ParcoursBFS parcoursBFS;
    private Case caseDestination;


    public Ennemi (int pv, double vitesse, Terrain terrain){
        this.id = compteur;
        this.pv = pv;
        this.vitesse = vitesse;
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

        this.terrain = terrain;
        // TODO: Mettre BFS en paramètre de terrain
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


    // TODO: Déplacement et Mettre à jour case destination si besoin pour chaque appel de la fonction
    public void seDeplacer(){

        if (this.getCoordonnees().getX() < this.caseDestination.getJ() * terrain.getTailleCase()){
            this.coordonnees.setX(this.coordonnees.getX()+1);
        } else if (this.getCoordonnees().getX() > this.caseDestination.getJ() * terrain.getTailleCase()){
            this.coordonnees.setX(this.coordonnees.getX()-1);
        }

        if (this.getCoordonnees().getY() < this.caseDestination.getI() * terrain.getTailleCase()){
            this.coordonnees.setY(this.coordonnees.getY()+1);
        } else if (this.getCoordonnees().getY() > this.caseDestination.getI() * terrain.getTailleCase()){
            this.coordonnees.setY(this.coordonnees.getY()-1);
        }

        if (this.getCase().caseEgale(this.caseDestination)){
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

    public Point coordonneesDepart(){
        return new Point(0,(int)(Math.random() * 80));
    }

}
