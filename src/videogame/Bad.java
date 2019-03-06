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
 * @author rigobertovaladez
 */
public class Bad extends Item{

    private int width;
    private int height;
    private Game game;
    private int damage;
    
    /**
     * initializes the values for the application game
     * @param x to set the position on the x-axis
     * @param y to set the position on the y-axis
     * @param width to set the width
     * @param height to set the height
     * @param damage to set how many times Bad has been hit
     */
    public Bad(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.damage = 0;
    }

    /**
     * To get the width of bad 
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * To get the height of bad 
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * To set the width of bad 
     *
     * @param width width of bad
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * To set the height of bad 
     *
     * @param height height of bad
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * To get the damage of bad
     *
     * @return an <code>int</code> value with the damage.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * To set the damage of bad 
     *
     * @param damage to determine the amount of times bad has been hit.
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void tick() {
                
    }
    
    /**
     * creates a new rectangle that covers Bad, using the x and y position
     * of the image and its width and height for collision purposes.
     * 
     * @return a <code>Rectangle</code> value with the specified parameters 
     * from the image
     */
    public Rectangle getPerimetro(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Draws the different images of bad.If it hasn't been hit, show the
     * default image bad.If it's hit once, draw the image of bad2.
     * 
     * @param g <b>Graphics</b> object to paint the item
     */
    @Override
    public void render(Graphics g) {
        if(damage == 0){
            g.drawImage(Assets.bad, getX(), getY(), getWidth(), getHeight(), null);
        }else if(damage == 1){
            g.drawImage(Assets.bad2, getX(), getY(), getWidth(), getHeight(), null);
        }
    }
    
    /**
     * Determines whether Bad intersects with ball
     * 
     * @param obj object Ball
     * 
     * @return a <code>boolean</code> value 
     */ 
    public boolean intersecta(Object obj){
        return obj instanceof Ball && getPerimetro().intersects(((Ball) obj).getPerimetro());     
    }
}
