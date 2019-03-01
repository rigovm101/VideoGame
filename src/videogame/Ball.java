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
    
    public Ball(int x, int y, int width, int height, Game game) {
        super(x, y);        
        this.width = width;
        this.height = height;
        this.game = game;
        this.directionX = directionX;
        this.directionY = directionY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDirectionX() {
        return directionX;
    }

    public int getDirectionY() {
        return directionY;
    }

    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }

    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }
    
    public Rectangle getPerimetro(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    
    
    

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
      
        
        
        if(getX()>= game.getWidth()-10 || getX()<=0){
            if(getDirectionX()==1){
                setDirectionX(-1);           
            }
            else{
                setDirectionX(1);
            }            
        }
        
        if(getY()>=game.getHeight()-10 || getY()<=0){
            if(getDirectionY()==1){
                setDirectionY(-1);           
            }
            else{
                setDirectionY(1);
            }          
        }
        
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bullet, getX(), getY(), getWidth(), getHeight(),null);        
    }
    
}
