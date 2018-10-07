/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema3.hero;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import tema3.Coordonate;
import tema3.labyrinth.Cell;
import tema3.labyrinth.EmptyCell;
import tema3.labyrinth.EntryCell;
import tema3.labyrinth.ExitCell;
import tema3.labyrinth.Maze;

/**
 *
 * @author root
 */
public class HeroTask2 {
    
    private final Maze maze;
    private final LinkedList<Cell> queuecel = new LinkedList<Cell>();
    
    /**
     * Constructoul creaza pe baza portalului de intrare prima inregistrare in
     * coada queuecel, coada care marcheaza celulel ce urmeaza sa fie vizitate de
     * algoritm
     * @param maze labirintul 
     */
    public HeroTask2(Maze maze) {
        
        Coordonate coord_entry;
        this.maze = maze;
        
        coord_entry = maze.getEntry_portal_cord();
        queuecel.add(maze.getCell(coord_entry.getX(), coord_entry.getY()));
    }
    
    /**
     * Algoritmul merge in felul urmator: se presupune ca eroul o poate lua pe
     * toate drumurile simultan, am folosit visits de la task-ul anterior sa spun
     * daca eroul ar fi putut vizita celula in drum spre iesire. Odata ce a ajuns intr-o 
     * celula o sa bage vecini ei in coada daca acestia u au fost vizitati si 
     * sunt instante ale lui EmptyCell, urmand ca si acestia la randul lor sa 
     * fie verificati si tot asa. Cand o celula este verificata celulele 
     * adiacente care urmeaza sa fie vizitate vor fi notificate prin intermediul
     * atributului back specific fiecarei celule ce celula ia pus in coada,
     * urmand ca atunci cand se ajunge la celula cu portalul de iesire sa se
     * poata gasi cel mai scurt drum urmarind lista simplu inlantuita de celule 
     * care a fost creata, urmand ca coordonatele membrilor acesteia sa fie 
     * folosite ca sa creeze un ArrayList de coordonate care va fi fi returnat
     * @return o lista cu drumul in ordine inversa
     */
    public ArrayList<Coordonate> getPath(){
        
        Cell cell;
        ArrayList<Cell> ajs_cells;
        ArrayList<Coordonate> path = new ArrayList<Coordonate>(20);
        Iterator<Cell> iter_ajs_cell;
        Cell aux = null;
        while(!queuecel.isEmpty()){
            
            cell = queuecel.removeFirst();
            ajs_cells = cell.getAjustantCells();
            iter_ajs_cell = ajs_cells.iterator();
           
            while(iter_ajs_cell.hasNext()){
                aux = iter_ajs_cell.next();
                if(aux instanceof EmptyCell && aux.getVizite() == 0){
                    aux.inc_Vizite();
                    queuecel.addLast(aux);
                    aux.setBack(cell);
                    if(aux instanceof ExitCell){
                        break;
                    }
                }
            }
            if(aux instanceof ExitCell){
                break;
            }
            
        }
        while(!(aux instanceof EntryCell)){
            path.add(aux.getCoordonate());
            aux = aux.getBack();
        }
        path.add(aux.getCoordonate());
        return path;
    }

    
   
    
    
    
}
