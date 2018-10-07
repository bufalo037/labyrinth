/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema3.labyrinth;

import tema3.Coordonate;
import tema3.hero.Hero;
/**
 *
 * @author mihai
 */
public class Maze {
    
    private final int n,m;
    private final Cell[][] cell;
    private static Maze maze = null;
    private Hero hero;
    private Coordonate entry_portal_cord;
    
    private Maze(int n, int m,String maze[] ) {
        this.n = n;
        this.m = m;
        this.cell = new Cell[n][m];
        
        int i,j;
        
        for(i = 0; i < n;i++){
            for(j = 0; j < m ;j++){
                switch(maze[i].charAt(j)){
                    case'.':{
                        cell[i][j] = new EmptyCell(i,j);
                        break;
                    }
                    case'#':{
                        cell[i][j] = new WallCell(i, j);
                        break;
                    }
                    case'I':{
                        cell[i][j] = new EntryCell(i, j);
                        hero = Hero.putHero(cell[i][j]);
                        setEntry_portal_cord(i,j);
                        break;
                    }
                    case'O':{
                        cell[i][j] = new ExitCell(i, j);
                    }
                        
                }
            }
        }
        
    }
    
    boolean isAjustant(Cell x,Cell y){
    
        int x_cord[] = new int[2];
        int y_cord[] = new int[2];
        int relx, rely;
        
        x_cord[0] = x.getX();
        x_cord[1] = x.getY();
        y_cord[0] = y.getX();
        y_cord[1] = y.getY();
        
        relx = Math.abs(x_cord[0] - y_cord[0]);
        rely = Math.abs(x_cord[1] - y_cord[1]);
        
        return !(relx > 1 || rely > 1 || (relx + rely) > 1);
        
        /*
            Pt claritate returnul de mai sus este echivalent cu secventa urmatoare
            if(relx > 1 || rely > 1 || (relx + rely) > 1)
                return false;
            return true;
        
        */
    }

    public Hero getHero() {
        return hero;
    }

    private void setEntry_portal_cord(int i, int j) {
        this.entry_portal_cord = new Coordonate(i, j);
    }

    /**
     * 
     * @return coordonatale eroului, returneza pozitia portallui de intrare in
     */
    public Coordonate getEntry_portal_cord() {
        return entry_portal_cord;
    }
    
    static Maze getMaze(){
        return Maze.maze;
    }
    
    /**
     * La prima apelare va crea maze-ul in functie de datele de intrare dupa
     * aceea tot ce va face va f sa returneze maze-ul creat indiferent de 
     * parametrii care o sa ii fie dati
     * 
     * @param n numarul de linii
     * @param m numarul de coloane
     * @param maze un array de strings care e menit sa dea informatii despre cum
     * sa fie construit labirintul
     * @return labirintul creat conform descrierii functiei
     */
    public static Maze getMaze(int n, int m,String maze[]){
        if(Maze.maze == null){
            Maze.maze = new Maze(n,m,maze);
            Cell.setMaze();
        }
        return Maze.maze; 
    }

    /**
     *
     * @param i ordonata
     * @param j abscisa
     * @return cell-ul de la coordnatele specificate la parametrii, return null
     * daca nu exista ceula in labirint
     */
    public Cell getCell(int i,int j) {
        if(i >= 0 && i < n && j >= 0 &&  j < m)
            return cell[i][j];
        return null;
    }   
    
}
