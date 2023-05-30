package fr.iut.montreuil.metallic_infastation.modele;

public abstract class Ennemi {
    private static int compteur = 0;
    private int id;
    private int pv;
    private int vitesse;
    private Point coordonnees;
    private Terrain terrain;
    private ParcoursBFS parcoursBFS;
    private Case caseDestination;


    public Ennemi (int pv, int vitesse, Terrain terrain){
        this.id = compteur;
        this.pv = pv;
        this.vitesse = vitesse;
        this.terrain = terrain;
        this.coordonnees = coordonneesDepart();
        this.parcoursBFS = new ParcoursBFS(terrain);
        parcoursBFS.remplirGrilleBFS();
        this.caseDestination = parcoursBFS.caseLaPlusProcheDArrivee(this.getCase());
        System.out.println(caseDestination.toString());
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
        int i = this.coordonnees.getY() / 16;
        int j = this.coordonnees.getX() / 16;
        return new Case(i,j);
    }


    // TODO: Déplacement et Mettre à jour case destination si besoin pour chaque appel de la fonction
   /*
    public void seDeplacer(){

        if (this.getCoordonnees().getX() < this.caseDestination.getJ() * terrain.getTailleCase()){
            this.coordonnees.setX(this.coordonnees.getX() + vitesse);
        } else if (this.getCoordonnees().getX() > this.caseDestination.getJ() * terrain.getTailleCase()){
            this.coordonnees.setX(this.coordonnees.getX() - vitesse);
        }

        if (this.getCoordonnees().getY() < this.caseDestination.getI() * terrain.getTailleCase()){
            this.coordonnees.setY(this.coordonnees.getY() + vitesse);
        } else if (this.getCoordonnees().getY() > this.caseDestination.getI() * terrain.getTailleCase()){
            this.coordonnees.setY(this.coordonnees.getY() - vitesse);
        }

        if (this.getCase().caseEgale(this.caseDestination)){
            this.caseDestination = parcoursBFS.caseLaPlusProcheDArrivee(this.caseDestination);
        }
    }
*/

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

    public Point coordonneesDepart(){
        return new Point(0,(int)(Math.random() * 80));
    }

}
