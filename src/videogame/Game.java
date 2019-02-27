/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

/**
 *
 * @author antoniomejorado
 */
public class Game implements Runnable {

    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private Player player;          // to use a player
    private LinkedList<Bad> bads;   // to use a bad guys
    private KeyManager keyManager;  // to manage the keyboard
    private MouseManager mouseManager; // to manage the mouse
    private int score;

    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        bads = new LinkedList<Bad>();
        score = 0;
    }

    /**
     * To get the width of the game window
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * To get the height of the game window
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        player = new Player(getWidth() - 100, getHeight()-88, 1, 120, 120, this);
        int iNum = (int) (Math.random() * 3 + 10);
        //adding elements to bads
        for (int i = 1; i <= iNum; i++) {
            int iPosX = (int) (Math.random() * (-199) - 200);
            int iPosY = (int) (Math.random() * (341) + 40);
            int d = (int) (Math.random() * (2) + 1);
            int s = (int) (Math.random() * (5) + 2);
            bads.add(new Bad(iPosX, iPosY, 200, 100, this));
        }
        display.getJframe().addKeyListener(keyManager);
       /* display.getJframe().addMouseListener(mouseManager);
        display.getJframe().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);*/
    }

    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;

            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    private void tick() {
        //Solo se hará tick si hay vidas
        if (player.getVidas() > 0) {
            keyManager.tick();
            // avancing player with colision
            player.tick();
            //ticking the bad guys
            for (int i = 0; i < bads.size(); i++) {
                Bad bad = bads.get(i);
                bad.tick();
                //Colision enemigo / jugador
                if (player.intersecta(bad)) {
                    int iPosX = (int) (Math.random() * (-199) - 200);
                    int iPosY = (int) (Math.random() * (341) + 40);
                    bad.setX(iPosX);
                    bad.setY(iPosY);
                    setScore(getScore() + 10);
                    //Si sube de puntaje y era negativo
                    if(getScore() >= 0 && !player.isRecovery()){
                        player.setRecovery(true);
                    }
                }
            }
            //Resta de vidas si se baja de 0 o positivo
            if (getScore() < 0 && player.isRecovery()) {
                player.setVidas(player.getVidas() - 1);
                player.setRecovery(false);
            }
        }

    }

    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, width, height, null);
            g.setColor(Color.white);
            //Si hay vidas, dibujar al jugador y a enemigos
            if (player.getVidas() > 0) {
                player.render(g);
                for (int i = 0; i < bads.size(); i++) {
                    Bad bad = bads.get(i);
                    bad.render(g);
                }
            } else {
                //Dibujar Hasta la Vista
                
            }
            //Dibujar score
            String text = Integer.toString(getScore());
            g.drawString(text, 50, 470);
            bs.show();
            g.dispose();

        }
    }

    /**
     * setting the thead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

}
