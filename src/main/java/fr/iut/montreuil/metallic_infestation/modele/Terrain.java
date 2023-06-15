package fr.iut.montreuil.metallic_infestation.modele;

import java.util.ArrayList;

public class Terrain {

    /**
     * Pour le terrain :  0 = Case vide (Interdite)
     *                    1 = Chemin
     *                    2 = Emplacement de Tour
     *                    3 = Tourelle semiAuto
     *                    4 = Tourelle Auto
     *                    5 = Tourelle Missiles
     *                    11 = Bunker Nord Ouest
     *                    12 = Bunker Nord Est
     *                    13 = Bunker Sud Est
     *                    14 = Bunker Sud Ouest
     */
    private int[][] terrain;
    private static final int tailleCase = 32;
    private ArrayList<Case> listeCasesDepartsPossibles;

    public Terrain() {
        if (LiaisonEntreLeMenuEtLeJeu.nbTerrain == 1) {
            this.terrain = new int[][]{
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 2, 2, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0},
                    {2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 2, 2, 0, 0, 0, 0, 2, 1, 1, 1, 1, 2, 2, 0, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 1, 2, 2, 0, 0, 0, 2, 2, 1, 1, 1, 1, 2, 0, 0, 0, 0, 0},
                    {2, 1, 1, 1, 1, 1, 1, 2, 2, 0, 0, 0, 2, 1, 1, 1, 1, 2, 2, 0, 0, 0, 0},
                    {0, 2, 2, 2, 2, 1, 1, 1, 2, 2, 0, 2, 1, 1, 1, 1, 1, 1, 2, 2, 0, 0, 0},
                    {0, 0, 0, 0, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 0, 0},
                    {0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 2, 0, 0},
                    {0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 2, 2, 2, 0, 0, 2, 1, 1, 1, 2, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 0, 0, 2, 2, 1, 1, 1, 1, 2, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1, 1, 1, 1, 1, 2, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 2, 2, 2, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 2, 2, 2, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 2, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 2, 2, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 2, 2, 2, 2, 2, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1,11,12},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1, 1, 1, 1,14,13}
            };
        }
        else if (LiaisonEntreLeMenuEtLeJeu.nbTerrain == 2) {
            this.terrain = new int[][]{
                    {0, 0, 0, 0, 2, 1, 1, 2, 0, 0, 0, 0, 0, 0, 2, 1, 1, 2, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 2, 1, 1, 2, 0, 0, 0, 0, 0, 0, 2, 1, 1, 2, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 2, 1, 1, 2, 0, 0, 0, 0, 0, 0, 2, 1, 1, 2, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 2, 1, 1, 2, 0, 0, 0, 0, 0, 0, 2, 1, 2, 2, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 2, 1, 1, 2, 0, 0, 0, 0, 0, 2, 2, 1, 2, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 2, 1, 1, 2, 2, 0, 0, 0, 2, 2, 1, 1, 2, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 2, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 2, 2, 1, 1, 1, 1, 1, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 2, 2, 1, 1, 1, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0},
                    {2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0, 0, 0, 0},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 2, 2, 2, 1, 2, 2, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 0},
                    {0, 0, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 0, 0, 2, 1, 1, 2, 2, 0, 0},
                    {2, 2, 2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 0, 0, 2, 1, 1, 1, 2, 2, 0},
                    {1, 1, 1, 1, 1, 1, 1, 2, 2, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1, 1, 1, 2, 2},
                    {1, 1, 1, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1,11,12},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1,14,13}
            };
        }
        else {
            this.terrain = new int[][]{
                    {1, 1, 2, 1, 1, 2, 0, 0, 0, 0, 0, 0, 2, 1, 2, 0, 0, 0, 0, 2, 2, 1, 1},
                    {1, 1, 2, 1, 1, 2, 0, 0, 0, 0, 0, 0, 2, 1, 2, 0, 0, 0, 2, 2, 1, 1, 2},
                    {2, 1, 1, 2, 1, 1, 2, 0, 0, 0, 0, 0, 2, 1, 2, 0, 0, 2, 1, 1, 1, 1, 2},
                    {0, 2, 1, 1, 2, 1, 1, 2, 2, 2, 0, 0, 2, 1, 2, 0, 0, 2, 1, 2, 2, 2, 0},
                    {0, 0, 2, 1, 1, 2, 1, 1, 1, 1, 2, 0, 2, 1, 2, 0, 2, 1, 1, 2, 0, 0, 0},
                    {0, 0, 2, 2, 1, 1, 2, 1, 1, 1, 1, 2, 2, 1, 2, 0, 2, 1, 2, 0, 0, 0, 0},
                    {0, 0, 0, 2, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 2, 1, 2, 0, 0, 0, 0},
                    {2, 2, 2, 2, 1, 1, 2, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0, 0, 0, 0},
                    {1, 1, 2, 2, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 2, 0, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 2, 0, 0},
                    {2, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2, 0, 0},
                    {0, 2, 1, 1, 2, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0, 0, 0},
                    {0, 0, 2, 1, 1, 2, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0, 0, 0, 0},
                    {2, 2, 2, 2, 1, 2, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 0, 0, 0},
                    {1, 1, 1, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 2, 0, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 2, 0, 0},
                    {2, 2, 2, 2, 1, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 2, 0, 0},
                    {0, 0, 0, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 0, 2, 2, 2, 2, 1, 1, 2, 0, 0},
                    {0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 1, 1, 2, 2, 0},
                    {0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2},
                    {2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2},
                    {1, 1, 1, 1, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1,11,12},
                    {2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1, 1,14,13}
            };
        }


    }

    /**
     * Retourne le terrain
     * @return
     *      char[][]
     */
    public int[][] getTerrain() {
        return terrain;
    }
    public int getTailleCase(){
        return tailleCase;
    }
    public void setCase(Case c, int n){
        this.terrain[c.getI()][c.getJ()] = n;
    }
    public ArrayList<Case> getListeCasesDepartsPossibles() {
        return listeCasesDepartsPossibles;
    }
    public boolean caseEstDansTerrain(Case c){
        return  (c.getI() >= 0 && c.getI() < terrain.length) && (c.getJ() >= 0 && c.getJ() < terrain[0].length);
    }

    
    public boolean arriveeSurCase(Case c){
        return terrain[c.getI()][c.getJ()] == 11;
    }

    public boolean cheminSurCase(Case c){
        return terrain[c.getI()][c.getJ()] == 1;
    }

    public boolean tourSurCase(Case c){
        return (terrain[c.getI()][c.getJ()] == 3);
    }

    public boolean videSurCase(Case c){
        return terrain[c.getI()][c.getJ()] == 0;
    }
    public boolean emplacementVideSurCase(Case c){
        return terrain[c.getI()][c.getJ()] == 2;
    }

    public void afficherTerrain(){
        for (int i = 0 ; i < terrain.length ; i++){
            for (int j = 0 ; j < terrain[i].length ; j++){
                if (terrain[i][j] < 10) {
                    System.out.print(" " + terrain[i][j]);
                }else {
                    System.out.print(terrain[i][j]);
                }
                System.out.print(",");
            }
            System.out.println();

        }
    }

    public void afficherTab() {

        for(int i = 0 ; i < terrain.length ; i++){
            for (int j = 0 ; j < terrain[i].length ; j++){
                if (terrain[i][j] < 9)
                    System.out.print(" " + terrain[i][j]);
                else
                    System.out.print(terrain[i][j]);
            }
            System.out.println();
        }
    }

    public Case obtenirCaseAdjacenteLibre(Case caseActuelle) {
        ArrayList<Case> casesAdjacentes = new ArrayList<>();
        int i = caseActuelle.getI();
        int j = caseActuelle.getJ();

        // Ajouter les cases adjacentes à la liste des cases possibles
        Case c1 = new Case(i + 1, j);
        Case c2 = new Case(i - 1, j);
        Case c3 = new Case(i, j + 1);
        Case c4 = new Case(i, j - 1);

        if (this.caseEstDansTerrain(c1) && terrain[c1.getI()][c1.getJ()] == 1) {
            casesAdjacentes.add(c1);
        }
        if (this.caseEstDansTerrain(c2) && terrain[c2.getI()][c2.getJ()] == 1) {
            casesAdjacentes.add(c2);
        }
        if (this.caseEstDansTerrain(c3) && terrain[c3.getI()][c3.getJ()] == 1) {
            casesAdjacentes.add(c3);
        }
        if (this.caseEstDansTerrain(c4) && terrain[c4.getI()][c4.getJ()] == 1) {
            casesAdjacentes.add(c4);
        }

        if (casesAdjacentes.isEmpty()) {
            return null;
        }

        // Choisir une case adjacente libre aléatoire
        int randomIndex = (int) (Math.random() * casesAdjacentes.size());
        return casesAdjacentes.get(randomIndex);
    }
}

