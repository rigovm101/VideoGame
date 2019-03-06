/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author antoniomejorado
 */
public class Player extends Item{

    private int direction;  //Dirección en la que se movera el jugador
    private int width;      //Ancho del jugador
    private int height;     //Alto del jugador
    private Game game;      //Juego
    
    /**
     * initializes the values of the player
     * @param x position of player on the x-axis
     * @param y  position of player on the y-axis
     * @param direction direction of player
     * @param width width of player
     * @param height height of player
     * @param game to access width and height of game window
     */
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
    }

    /**
     * To get the direction of the player
     *
     * @return an <code>int</code> value with the direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * To get the width of the player
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the player
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * To set the direction of the player
     *
     * @param direction direction of player
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * To set the width of the player
     *
     * @param width direction of player
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * To set the height of the player
     *
     * @param height height of player
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Sets the movement of player and resets position if it collision with
     * a wall
     */
    @Override
    public void tick() {
        //player movement
        if (game.getKeyManager().left) {
           setX(getX() - 4);
        }
        if (game.getKeyManager().right) {
           setX(getX() + 4);
        }

        // reset x position and y position if colision
        if (getX() + 100 >= game.getWidth()) {
            setX(game.getWidth() - 100);
        }
        else if (getX() <= -20) {
            setX(-20);
        }
   
    }
    
    /**
     * creates a new rectangle that covers the player, using the x and y position
     * of the image and its width and height for collision purposes.
     * 
     * @return a <code>Rectangle</code> value with the specified parameters 
     * from the image
     */
    public Rectangle getPerimetro(){
        return new Rectangle(getX(), getY()+20, getWidth(), getHeight());
    }
    
    /**
     * Determines whether player intersects with bad
     * 
     * @param obj object bad
     * 
     * @return a <code>boolean</code> value 
     */ 
    public boolean intersecta(Object obj){
        return obj instanceof Bad && getPerimetro().intersects(((Bad) obj).getPerimetro());     
    }
    
    /**
     * Determines whether player intersects with ball
     * 
     * @param obj object Ball
     * 
     * @return a <code>boolean</code> value 
     */ 
    public boolean intersects(Object obj){
        return obj instanceof Ball && getPerimetro().intersects(((Ball) obj).getPerimetro());     
    }

    
    /**
     * Draws the image of player
     * 
     * @param g <b>Graphics</b> object to paint the item
     */
    @Override
    public void render(Graphics g) {
        //Pintado del jugador
        g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
    }
}
