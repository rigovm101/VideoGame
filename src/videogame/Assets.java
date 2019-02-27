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
    public static BufferedImage baby;
    public static BufferedImage heart;

    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/Background.png");
        player = ImageLoader.loadImage("/images/RV.png");
        bad = ImageLoader.loadImage("/images/link.png");
        baby = ImageLoader.loadImage("/images/baby.jpg");
        heart = ImageLoader.loadImage("/images/heart.png");
    }
    
}
