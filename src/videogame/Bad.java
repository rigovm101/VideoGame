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

    private int direction;
    private int width;
    private int height;
    private Game game;
    private int speed;
    
    public Bad(int x, int y, int direction, int width, int height, Game game, int speed) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public int getDirection() {
        return direction;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void tick() {
        //Moving bad to right
        if(getX() <= -10){
            setX(getX() + 1);
        }else{
            //Up
            if(getDirection() <= 1){
                setX(getX() + getSpeed());
                setY(getY() - getSpeed());
            }
            //Down
            else{
                setX(getX() + getSpeed());
                setY(getY() + getSpeed());
            }
        }
        
        // reset x position and y position if colision
        if(getX() >= 500 || getY() >= 500 || getY() <= 0){
            int iPosX = (int)(Math.random() * (-199) - 200);
            int iPosY = (int)(Math.random() * (341) + 40);
            setX(iPosX);
            setY(iPosY);
            game.setScore(game.getScore() - 2);
        }
        
        //Números aleatorios
        //Metodo 1
        //(int)*(Math.random() * (b-a+1)+a)
        //Metodo 2
        //Rand rnd = new Random();
        //int azar = rnd.nextInt(b-a)+a;
        
//        if (getX() + 60 >= game.getWidth()) {
//            setX(game.getWidth() - 60);
//        }
//        else 
        
    }
    
    public Rectangle getPerimetro(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bad, getX(), getY(), getWidth(), getHeight(), null);
    }
}
