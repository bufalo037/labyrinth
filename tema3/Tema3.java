/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import tema3.hero.HeroTask2;
import tema3.labyrinth.Maze;

/**
 *
 * @author mihai
 */
public class Tema3 {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
    
        Scanner scan = new Scanner(new File(args[1]));
        BufferedWriter buffwrite = new BufferedWriter(new FileWriter(args[2]));
        Maze labyrinth;
        ArrayList<Coordonate> path = new ArrayList<Coordonate>(5);
       
        int n, m, i;
        String maze[];
        n = scan.nextInt();
        m = scan.nextInt();
        maze = new String[n];

        //System.out.println(n + " " + m);
        for(i = 0;i < n;i++ ){
            maze[i] = scan.next();
           // System.out.println(maze[i]);
        }
        scan.close();
        
        labyrinth =  Maze.getMaze(n,m,maze);

//==============================================================================        
        if(args[0].equals("1")){
            
            path.add(labyrinth.getEntry_portal_cord());
        
            while(!labyrinth.getHero().reachedExit()){
                labyrinth.getHero().moveHero();
                path.add(labyrinth.getHero().getCel().getCoordonate());
            }   
            
            buffwrite.write(((Integer)path.size()).toString());
            buffwrite.newLine();
            
            n = path.size();
            //scrierea
            for( i = 0; i < n; i++){
                Coordonate aux;
                aux = path.get(i);
                buffwrite.write(aux.getX() + " " + aux.getY()); 
                buffwrite.newLine();
            }
            
        }
        else
            if(args[0].equals("2")){
                path = new HeroTask2(labyrinth).getPath();
                buffwrite.write(((Integer)path.size()).toString());
                buffwrite.newLine();
                n = path.size() -1;

                while(!path.isEmpty()){
                    Coordonate aux;
                    aux = path.remove(n--);
                    buffwrite.write(aux.getX() + " " + aux.getY());
                    buffwrite.newLine();
                }
            }
        
        buffwrite.close();
        
    }
    
}
