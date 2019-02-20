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
    
    public boolean u;      // flag to move up the player
    public boolean o;    // flag to move down the player
    public boolean j;    // flag to move left the player
    public boolean l;   // flag to move right the player

    private boolean keys[];  // to store all the flags for every key
    
    public KeyManager() {
        keys = new boolean[256];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // set true to every key pressed
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        keys[e.getKeyCode()] = false;
    }
    
    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        u = keys[KeyEvent.VK_U];
        o = keys[KeyEvent.VK_O];
        j = keys[KeyEvent.VK_J];
        l = keys[KeyEvent.VK_L];
        
//        u = keys[KeyEvent.VK_UP];
//        o = keys[KeyEvent.VK_RIGHT];
//        j = keys[KeyEvent.VK_LEFT];
//        l = keys[KeyEvent.VK_DOWN];
    }
}
