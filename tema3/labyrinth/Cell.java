/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema3.labyrinth;

import java.util.ArrayList;
import tema3.Coordonate;

/**
 *
 * @author mihai
 */
public abstract class Cell {
    private static Maze maze;
    private final int x, y;
    private int vizite;
    private Cell back = null; //task2
   
    /**
     *
     * @param x coordonata liniei in interiorul labirintului
     * @param y coordonata coloanei in interiorul labirintului
     */
    public Cell(int x, int y){
        this.x = x;
        this.y = y;
        this.vizite = 0;
    }
    
    /**
     * Se seteaza ca camp static Maze clasei Cell, si este necesara aceasta functie
     * pt ca cell-urile sunt create inainte sa fie initializat maze-ul si prin
     * urmare este nevoie de o operatie ca sa seteze maze-ul in interiorul 
     * celulelor
     */
    public static void setMaze(){
        maze  = Maze.getMaze();
    }
  
    /**
     * Se face operatia doar daca celula data ca parametru este adiacenta
     * @param x celula fata de care sa se faca comparatia
     * @return se returneaza pozitia celulei relativa la celula x
     */
    public int[] poz_rel_ajs(Cell x){
        int x_cord[] = new int[2];
        int y_cord[];
        int relx, rely;
        
        x_cord[0] = x.getX();
        x_cord[1] = x.getY();
      
        relx = Math.abs(x_cord[0] - this.x);
        rely = Math.abs(x_cord[1] - this.y);
        
        if(relx > 1 || rely > 1 || (relx + rely) > 1)
            return null;//nu e ajustant
        
        y_cord = new int[2];
        y_cord[0] = x_cord[0] - this.x;
        y_cord[1] = x_cord[1] - this.y;
        return y_cord;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
    
    
    
    /**
     *
     * @return returneaza un arraylist cu celulele adiacente acestei celule
     */
    public ArrayList<Cell> getAjustantCells(){
        Cell temp;
        ArrayList<Cell> arrayCell = new ArrayList(4);
        int i = 0;
       // System.out.println(maze);
        temp = maze.getCell(x, y+1);
        if(temp != null)
            arrayCell.add(temp);
        
        temp = maze.getCell(x-1, y);
        if(temp != null)
            arrayCell.add(temp);
        
        temp = maze.getCell(x, y-1);
        if(temp != null)
            arrayCell.add(temp);
        
        temp = maze.getCell(x+1, y);
        if(temp != null)
            arrayCell.add(temp);
        
        return arrayCell;
    }
    
    /**
     * Daca celula nu este adiacenta va returna null
     * @param x cat sa adaugi la x-ul celulei sa iti dea alta celula
     * @param y cat sa adaugi la y-ul celulei sa iti dea alta celula
     * @return returneaza o celula adiacenta pe bazaa lui x si y
     */
    public Cell getAjustantCell(int x,int y){
        if(Math.abs(x) >1 ||Math.abs(y) >1)
            return null;
        return maze.getCell(this.x + x, this.y + y);
    }
    
    /**
     *
     * @return coordonatele celului sub forma clasei coordonate
     */
    public Coordonate getCoordonate(){
        return new Coordonate(getX(), getY());
    }
    
    /**
     *
     * @return numarul de ori de care eroul a trecut prin aceasta celula
     */
    public final int getVizite() {
        return vizite;
    }
    
    /**
     * Se incrementeaza numarul de vizite ale eroului pentru aceasta celula
     */
    public final void inc_Vizite(){
        vizite++;
    }

    /**
     *
     * @param back reprezinta celula care a apelat celula asta
     */
    public void setBack(Cell back) {
        this.back = back;
    }

    /**
     *
     * @return celula anterioara acestia
     */
    public Cell getBack() {
        return back;
    }
    
    
    
    
}

