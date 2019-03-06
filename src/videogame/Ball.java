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
 * @author luis_
 */
public class Ball extends Item {
    private int width;
    private int height;
    private int directionX;
    private int directionY;
    private Game game;
    
    /**
     * initializes the values for the ball
     * @param x to set the position on the x-axis
     * @param y to set the position on the y-axis
     * @param width to set the width
     * @param height to set the height
     * @param game to access width and height of game window
     */
    public Ball(int x, int y, int width, int height, Game game) {
        super(x, y);        
        this.width = width;
        this.height = height;
        this.game = game;
        this.directionX = directionX;
        this.directionY = directionY;
    }

    /**
     * To get the width of ball 
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of ball
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * To get the direction of the ball on the x-axis
     *
     * @return an <code>int</code> value with the direction
     */
    public int getDirectionX() {
        return directionX;
    }

    /**
     * To get the direction of the ball on the y-axis
     *
     * @return an <code>int</code> value with the direction
     */
    public int getDirectionY() {
        return directionY;
    }

    /**
     * To set the direction of the x-axis
     *
     * @param directionX direction of the ball on x-axis
     */
    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }

    /**
     * To set the direction of the y-axis
     *
     * @param directionY direction of the ball on y-axis
     */
    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }
    
    /**
     * creates a new rectangle that covers the ball, using the x and y position
     * of the image and its width and height for collision purposes.
     * 
     * @return a <code>Rectangle</code> value with the specified parameters 
     * from the image
     */
    public Rectangle getPerimetro(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    
    /**
     * Sets movement of the ball and collision with the walls
     */
    @Override
    public void tick() {
        if(getDirectionX()==1){
           setX(getX()+3);
        }
        else{
           setX(getX()-3);
        }
        
        if(getDirectionY()==1){
           setY(getY()+3);       
        }
        else{
            setY(getY()-3);
        }
      
        
        //collision with the walls from the left and rights side
        if(getX()>= game.getWidth()-10 || getX()<=0){
            if(getDirectionX()==1){
                setDirectionX(-1);           
            }
            else{
                setDirectionX(1);
            }            
        }
        
        //collsion with the walls from the upper and botoom side
        if(getY()<=0){
            if(getDirectionY()==1){
                setDirectionY(-1);           
            }
            else{
                setDirectionY(1);
            }          
        }
        if(getY()>=game.getHeight()-10){
            setX(game.getHeight()/2);
            setY(game.getWidth()/2);
            //setDirectionY()
        }
        
        
    }

    /**
     * Draws the image of ball
     * 
     * @param g <b>Graphics</b> object to paint the item
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bullet, getX(), getY(), getWidth(), getHeight(),null);        
    }
    
}
