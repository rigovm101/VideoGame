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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    private int score;
    private Ball ball;              //to use ball
    private boolean pause;

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
        bads = new LinkedList<Bad>();
        score = 0;
        pause = false;
    }

    /**
     * To get the width of the game window
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the score of the game 
     *
     * @return an <code>int</code> value with the score
     */
    public int getScore() {
        return score;
    }

    /**
     * To set the score of the game 
     *
     * @param score 
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * To determine if the game is paused
     *
     * @param score 
     */
    public boolean isPause() {
        return pause;
    }

    /**
     * To pause the game
     *
     * @param score 
     */
    public void setPause(boolean b) {
        pause = b;
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
        player = new Player(getWidth() - 100, getHeight() - 88, 1, 120, 100, this);
        ball = new Ball(getWidth() / 2, getHeight() / 2, 10, 10, this);
        int iNum = (int) (Math.random() * 3 + 8);
        //adding elements to bads
        for (int i = 1; i <= iNum; i++) {
            int iPosX = (int) (Math.random() * (536) + 15);
            int iPosY = (int) (Math.random() * (206) + 15);
            bads.add(new Bad(iPosX, iPosY, 60, 20, 0, this));
        }
        display.getJframe().addKeyListener(keyManager);

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

    /**
     * To get access to the KeyMaanger class 
     *
     * @return a <code>keyManager</code> value
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }
    


    private void tick() {
        keyManager.tick();
        
        if(getKeyManager().load){
            load();
        }
        if(getKeyManager().save){
            save();
        }

        if (bads.isEmpty()) {
            if (getKeyManager().space) {
                int iNum = (int) (Math.random() * 3 + 8);
                //adding elements to bads
                for (int i = 1; i <= iNum; i++) {
                    int iPosX = (int) (Math.random() * (536) + 15);
                    int iPosY = (int) (Math.random() * (206) + 15);
                    bads.add(new Bad(iPosX, iPosY, 60, 20, 0, this));
                }
            }
        } else {
            //Pause boolean
            if (getKeyManager().pause) {
                pause = true;
            } else {
                pause = false;
            }
            //Enter if the game is unpaused
            if (!isPause()) {
                player.tick();
                ball.tick();

                //Collision between player and ball
                if (player.intersects(ball)) {
                    ball.setDirectionY(-1);
                   // ball.setSpeed(4);
                    if (ball.getDirectionX() == 1) {
                        if(keyManager.left){
                           ball.setDirectionX(-1); 
                        }
                        else{
                           ball.setDirectionX(1); 
                        }       
                        
                    } else if(ball.getDirectionX() == -1){
                        if(keyManager.right){
                           ball.setDirectionX(1); 
                        }
                        else{
                           ball.setDirectionX(-1);
                        }
                        
                    }
                }

                //Collision between ball and bad
                for (int i = 0; i < bads.size(); i++) {
                    if (bads.get(i).intersecta(ball)) {
                        bads.get(i).setDamage(bads.get(i).getDamage() + 1);
                        ball.setDirectionY(ball.getDirectionY() * -1);
                    }
                }

                //Remove bad if its hit more than once
                for (int i = 0; i < bads.size(); i++) {
                    if (bads.get(i).getDamage() >= 2) {
                        bads.remove(i);
                    }
                }
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
            if (!bads.isEmpty()) {
                player.render(g);
                ball.render(g);
                for (int i = 0; i < bads.size(); i++) {
                    bads.get(i).render(g);
                }
            } else {
                g.drawImage(Assets.over, 200, 100, 200, 100, null);
            }
            bs.show();
            g.dispose();

        }
    }
    
    void load(){
        String fileName = "/loadData/loadData.txt";
        try{
            FileReader fileReader = new FileReader("loadData.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //Ball Position
            int ballX, ballY;
            ballX = bufferedReader.read();
            ballY = bufferedReader.read();
            ball.setX(ballX);
            ball.setY(ballY);
            //Ball direction
            int ballDX, ballDY;
            ballDX = bufferedReader.read();
            ballDY = bufferedReader.read();
            ball.setDirectionX(ballDX);
            ball.setDirectionY(ballDY);
            //Bad Amount
            int badAmount;
            badAmount = bufferedReader.read();
            bads.clear();
            int badPosX, badPosY, badDamage;
            for(int i = 0; i < badAmount; i++){
                badPosX = bufferedReader.read();
                badPosY = bufferedReader.read();
                badDamage = bufferedReader.read();
                bads.add(new Bad(badPosX, badPosY, 60, 20, badDamage,this));
            }
            bufferedReader.close();
        }catch(FileNotFoundException ex){
            System.out.println("Unable to open file '" + fileName + "'");
        }catch(IOException ex){
            
        }
    }
    
    void save(){
        String fileName = "/loadData/loadData.txt";
        try{
            FileWriter fileWriter = new FileWriter("loadData.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(ball.getX());
            bufferedWriter.write(ball.getY());
            bufferedWriter.write(ball.getDirectionX());
            bufferedWriter.write(ball.getDirectionY());
            bufferedWriter.write(bads.size());
            for(int i = 0; i < bads.size(); i++){
                bufferedWriter.write(bads.get(i).getX());
                bufferedWriter.write(bads.get(i).getY());
                bufferedWriter.write(bads.get(i).getDamage());
            }
            bufferedWriter.close();
        }catch(FileNotFoundException ex){
            System.out.println("Unable to open file '" + fileName + "'");
        }catch(IOException ex){
            
        }
    }

    /**
     * setting the thread for the game
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
