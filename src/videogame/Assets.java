/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author antoniomejorado
 */
public class Assets {
    public static BufferedImage background; // to store background image
    public static BufferedImage player;     // to store the player image
    public static BufferedImage bad;
    public static BufferedImage bad2;
    public static BufferedImage bad3;

    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/Background.png");
        player = ImageLoader.loadImage("/images/mario.png");
        bad = ImageLoader.loadImage("/images/Meth1.png");
        bad2 = ImageLoader.loadImage("/images/Meth2.png");
        bad3 = ImageLoader.loadImage("/images/Meth3.png");
    }
    
}
