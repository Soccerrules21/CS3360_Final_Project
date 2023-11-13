package Entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Rectangle;
public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x = 100;
        y = 500;
        speed = 4;
    }

    public void getPlayerImage(){

        try{

            bat = ImageIO.read(getClass().getResourceAsStream("/player/guy.png"));

        } catch(IOException e){
            e.printStackTrace();
        }

    }

    public void update() {

        if (keyH.spacePressed) {
            y -= speed;
        } else if (!keyH.spacePressed) {
            y += speed;
        }


    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        image = bat;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }
    public Rectangle getBounds(){ //*******************************************
        return new Rectangle (x, y, gp.tileSize, gp.tileSize);
    }
}