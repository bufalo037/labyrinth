/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema3;

/**
 *
 * @author mihai
 */
public class Coordonate{
    private final int x,y;

    /**
     *
     * @param x ordonata
     * @param y abscisa
     */
    public Coordonate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return valoarea ordonatei
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return valoarea abscisei
     */
    public int getY() {
        return y;
    }
}
