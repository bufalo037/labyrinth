/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema3.labyrinth;

/**
 *
 * @author mihai
 */
public class EntryCell extends EmptyCell{

    /**
     *
     * @param i ordonata
     * @param j abscisa
     */
    public EntryCell(int i,int j) {
        super(i,j);
        inc_Vizite();
    }
    
}