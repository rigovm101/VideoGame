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
    private int vidas;      //Vidas del jugador
    private Boolean recovery;   //Booleano para ver si ya habia perdido vidas previamente
    
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        int v = (int)(Math.random()*(6)+5);
        vidas = v;
        recovery = true;
    }

    public Boolean isRecovery() {
        return recovery;
    }

    public void setRecovery(Boolean recovery) {
        this.recovery = recovery;
    }
    
    

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
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
        if (game.getKeyManager().u) {
           setY(getY() - 4);
           setX(getX() - 4);
        }
        if (game.getKeyManager().o) {
           setY(getY() - 4);
           setX(getX() + 4);
        }
        if (game.getKeyManager().j) {
           setY(getY() + 4);
           setX(getX() - 4);
        }
        if (game.getKeyManager().l) {
           setY(getY() + 4);
           setX(getX() + 4);
        }
        // reset x position and y position if colision
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
        }
        else if (getX() <= -30) {
            setX(-30);
        }
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
        }
        else if (getY() <= -20) {
            setY(-20);
        }
    }
    
    public Rectangle getPerimetro(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    public boolean intersecta(Object obj){
        return obj instanceof Bad && getPerimetro().intersects(((Bad) obj).getPerimetro());
        
    }

    @Override
    public void render(Graphics g) {
        //Pintado del jugador
        g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
        //Pintado de vidas
        int pos = 400;
        for(int i = 0; i < vidas; i++){
            g.drawImage(Assets.heart, pos, 460, 50, 50, null);
            pos = pos + 40;
        }
    }
}
