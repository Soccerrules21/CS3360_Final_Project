package main;

import Entity.Player;
import Entity.Rock;
import main.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn;
    final int screenHeight = tileSize * maxScreenRow;

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,keyH);
    Rock rock = new Rock(this);
    Rock rock2 = new Rock(this);
    Rock rock3 = new Rock(this);
    Rock rock4 = new Rock(this);

    boolean collision = false; //***********************************************************
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;


        while(gameThread != null) {

            update();

            repaint();



            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        if (!collision) {
            player.update();
            rock.update();
            rock2.update();
            rock3.update();
            rock4.update();
        }
    }
    public void checkCollision(){ //*****************************
        Rectangle r = rock.getBounds();
        Rectangle r2 = rock2.getBounds();
        Rectangle r3 = rock3.getBounds();
        Rectangle r4 = rock4.getBounds();
        Rectangle p = player.getBounds();
        if (p.intersects(r)|p.intersects(r2)|p.intersects(r3)|p.intersects(r4))
            collision=true;
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        checkCollision();
        if (collision){
            g.setColor(Color.RED);
            g.setFont(new Font("Times New Roman", Font.BOLD,30));
            g.drawString("GAME OVER", 275,200);
            System.out.println("ggs");
        }

            Graphics2D g2 = (Graphics2D) g;
            player.draw(g2);
            rock.draw(g2);
            rock2.draw(g2);
            rock3.draw(g2);
            rock4.draw(g2);
            g2.dispose();


    }

}