/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema3.hero;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;
import tema3.exceptions.CannotMoveIntoWallsException;
import tema3.exceptions.HeroOutOfGroudException;
import tema3.labyrinth.Cell;
import tema3.labyrinth.EmptyCell;
import tema3.labyrinth.ExitCell;
import tema3.labyrinth.WallCell;

/**
 *
 * @author mihai
 */
public class Hero {
    
    private static Hero hero = null;
    //aceste valori sunt relative la planul numerelor complexe, dar deoarece
    //eu voi lucra cu o matrice care cand creste in sus defapt scade indicele de
    //lini va trebui sa inversez stanga cu dreapta
    private final int left[] = new int[]{1,0};
    private final int right[] = new int[]{-1,0};
    private final int back[] = new int[]{0,-1};
    
    //prin orientare ma gandesc la un vector in planul complex
    //orientare[1] reperzinta partea reala iar orientare[0] partea imaginara
    //am ales aceasta abordare din lene de hardcodare (nu imi place hardcodarea)
    private final int orientare[];
    private Cell cel;
    TreeSet<Cell> AjustantCells = new TreeSet<Cell>(new CellCmp());
    
    private class CellCmp implements Comparator<Cell>{

        @Override
        public int compare(Cell o1, Cell o2) {
            
            if(o1 instanceof ExitCell)
                return -1;
            if(o2 instanceof ExitCell)
                return 1;
            
            if(o1 instanceof WallCell)
                return 1;
            if(o2 instanceof WallCell)
                return -1;
            
            if(o1.getVizite() != o2.getVizite())
            {
                if(o1.getVizite() > o2.getVizite())
                    return 1;
                else
                    return -1;
            }
            
            int dir[];
            Cell cell;
            
            //fac rost de directia relativa la orientarea eroului
            //gasesc cell-ul adiacent de la directia respectiva
            //fac comparatii daca se gaseste printre cell-urile comparate
            dir = getRight();
            cell = cel.getAjustantCell(dir[0], dir[1]);
            if(o1.equals(cell))
                return -1;
            else
                if(o2.equals(cell))
                    return 1; 
            
            dir = getForward();
            cell = cel.getAjustantCell(dir[0], dir[1]);
            if(o1.equals(cell))
                return -1;
            else
                if(o2.equals(cell))
                    return 1; 
            
            dir = getLeft();
            cell = cel.getAjustantCell(dir[0], dir[1]);
            if(o1.equals(cell))
                return -1;
            else
                if(o2.equals(cell))
                    return 1; 
            
            dir = GetBackward();
            cell = cel.getAjustantCell(dir[0], dir[1]);
            if(o1.equals(cell))
                return -1;
            
            return 1;
                     
        }

    }

    private Hero(Cell cel) {

        this.orientare = new int[2];
        
        orientare[0] = -1;
        orientare[1] = 0;
        this.cel = cel;
    }

    
    private void setAjustantCells() {
        
        int n, i;
        ArrayList<Cell> cell = cel.getAjustantCells();
        n = cell.size();
        
        AjustantCells.clear();
        for(i = 0;i < n;i++){
            AjustantCells.add(cell.get(i));
        }
    }

    /**
     *
     * @throws CannotMoveIntoWallsException
     */
    public void moveHero() throws CannotMoveIntoWallsException, HeroOutOfGroudException{
        
        Cell dest;
        int orientare[];
       
        setAjustantCells();
        dest = AjustantCells.first();
        if(dest instanceof WallCell)
            throw new CannotMoveIntoWallsException();
        if(!(dest instanceof Cell))
            throw new HeroOutOfGroudException();
        if(dest instanceof EmptyCell){
            orientare = cel.poz_rel_ajs(dest);
            dest.inc_Vizite();
            setOrientare(orientare[0], orientare[1]);
            cel = dest;
        }
        
    }

    /**
     *
     * @return true daca eroul a ajuns la iesire else return false
     */
    public boolean reachedExit(){
        return cel instanceof ExitCell;
    }
    
    /**
     * Implementare de singleton
     * Merge ca getHero daca eroul este deja in labirint
     * @param cel celula in care se afla eroul
     * @return hero
     */
   
    public static Hero putHero(Cell cel) {
        if(hero == null){
            hero = new Hero(cel);
        }
        return hero;
    }

    /**
     * Returneaza unica instanta a lui hero
     * @return hero
     */
    public static Hero getHero() {
        return hero;
    }
    
    //imultirea a 2 numere complexe
    private int[] aritmethic(int x[],int y[]){
        int r[] = new int[2];
        
        if(x.length != 2 || y.length != 2)
            return null;
        else
        {
                r[0] = x[1] * y[0] + y[1] * x[0];
                r[1] = x[1] * y[1] - x[0] * y[0];
        }
        return r;
    }

    /**
     *
     * @return celula in care se afla eroul
     */
    public Cell getCel() {
        return cel;
    }

    private void setOrientare(int x,int y) {
        this.orientare[0] = x;
        this.orientare[1] = y;

    }
    
    private int[] getRight(){
        return aritmethic(orientare,left);
    }
    
    private int[] getLeft(){
        return aritmethic(orientare,right);
    }
    
    private int[] getForward(){
        return orientare;
    }
    
    private int[] GetBackward(){
        return aritmethic(orientare,back);
    }
    
}
