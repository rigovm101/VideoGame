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
    
    public Bad(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.damage = 0;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void tick() {
        
        
    }
    
    public Rectangle getPerimetro(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void render(Graphics g) {
        if(damage == 0){
            g.drawImage(Assets.bad, getX(), getY(), getWidth(), getHeight(), null);
        }else if(damage == 1){
            g.drawImage(Assets.bad2, getX(), getY(), getWidth(), getHeight(), null);
        }
    }
    
    public boolean intersecta(Object obj){
        return obj instanceof Ball && getPerimetro().intersects(((Ball) obj).getPerimetro());     
    }
}
