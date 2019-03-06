/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author antoniomejorado
 */
public class MouseManager  implements MouseListener, MouseMotionListener {
    private boolean izquierdo;          // to check if left has been pushed
    private boolean derecho;            // to check if right has been pushed
    private int x;                      // to get x position of the mouse
    private int y;                      // to get y position of the mouse
    
    public MouseManager() {
        izquierdo = false;
        derecho = false;
    }

    /**
     * To get the position of the mouse on the x-axis
     *
     * @return an <code>int</code> value with the x position
     */
    public int getX() {
        return x;
    }

    /**
     * To get the position of the mouse on the t-axis
     *
     * @return an <code>int</code> value with the y position
     */
    public int getY() {
        return y;
    }

    /**
     * To determine if left was pushed
     *
     * @return a <code>boolean</code> value 
     */
    public boolean isIzquierdo() {
        return izquierdo;
    }

    /**
     * To determine if right was pushed
     *
     * @return a <code>boolean</code> value 
     */
    public boolean isDerecho() {
        return derecho;
    }

    /**
     * To set if left was pushed
     *
     * @param izquierdo boolean  
     */
    public void setIzquierdo(boolean izquierdo) {
        this.izquierdo = izquierdo;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * if the left button from mouse was release, set true to boolean left and 
     * get the x and y position of where it was released
     *
     * @param e button used
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            izquierdo = true;
            x = e.getX();
            y = e.getY();            
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * if the left button from mouse was used in a drag motion, set true to
     * boolean left and get the x and y position of the mouse
     *
     * @param e button used
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            izquierdo = true;
            x = e.getX();
            y = e.getY();            
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
