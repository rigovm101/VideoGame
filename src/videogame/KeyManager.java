/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author antoniomejorado
 */
public class KeyManager implements KeyListener {
    
    public boolean left;    // flag to move left the player
    public boolean right;   // flag to move right the player
    public boolean pause;
    public boolean space;

    private boolean keys[];  // to store all the flags for every key
    
    public KeyManager() {
        keys = new boolean[256];
        keys[KeyEvent.VK_P] = false;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // set true to every key pressed
        if(e.getKeyCode() == KeyEvent.VK_P){
            if(keys[KeyEvent.VK_P]){
                keys[KeyEvent.VK_P] = false;
            }else{
                keys[KeyEvent.VK_P] = true;
            }
        }else{
            keys[e.getKeyCode()] = true;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        if(e.getKeyCode() != KeyEvent.VK_P){
            keys[e.getKeyCode()] = false;
        }
    }
    
    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        pause = keys[KeyEvent.VK_P];
        space = keys[KeyEvent.VK_R];

    }
}
