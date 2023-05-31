package fr.iut.montreuil.metallic_infastation.modele;

import fr.iut.montreuil.metallic_infastation.JeuApplication;

import java.io.FileReader;
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
        this.terrain = new int[][]{
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 2, 2, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0},
                { 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0},
                { 1, 1, 1, 1, 1, 2, 2, 0, 0, 0, 0, 2, 1, 1, 1, 1, 2, 2, 0, 0, 0, 0, 0},
                { 1, 1, 1, 1, 1, 1, 2, 2, 0, 0, 0, 2, 2, 1, 1, 1, 1, 2, 0, 0, 0, 0, 0},
                { 2, 1, 1, 1, 1, 1, 1, 2, 2, 0, 0, 0, 2, 1, 1, 1, 1, 2, 2, 0, 0, 0, 0},
                { 0, 2, 2, 2, 2, 1, 1, 1, 2, 2, 0, 2, 1, 1, 1, 1, 1, 1, 2, 2, 0, 0, 0},
                { 0, 0, 0, 0, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 0, 0},
                { 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 2, 0, 0},
                { 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 2, 2, 2, 0, 0, 2, 1, 1, 1, 2, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 0, 0, 2, 2, 1, 1, 1, 1, 2, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1, 1, 1, 1, 1, 2, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 2, 2, 2, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 2, 2, 2, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 2, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 2, 2, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 2, 2, 2, 2, 2, 2},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1,11,12},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1, 1, 1, 1,14,13}
        };
        /*
        listeDepartsPossibles = new ArrayList<>();
        Point depart1 = new Point(0, 0);
        Point depart2 = new Point(1, 0);
        Point depart3 = new Point(0, 1);
        Point depart4 = new Point(0, 2);
        Point depart5 = new Point(0, 3);
        Point depart6 = new Point(0, 4);
        Point depart7 = new Point(0, 5);
        listeDepartsPossibles.addAll(Arrays.asList(depart1, depart2, depart3, depart4, depart5, depart6, depart7));
        */

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
}

