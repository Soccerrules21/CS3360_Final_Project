package Entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Rectangle;
import java.util.concurrent.ThreadLocalRandom;

public class Rock extends Entity {

    GamePanel gp;


    public Rock(GamePanel gp) {

        this.gp = gp;

        setDefaultValues();
        getRockImage();
    }

    public void setDefaultValues() {
        int randomNum = ThreadLocalRandom.current().nextInt(0,500+1);
        x = 600;
        y = randomNum;
        speed = 4;
    }
    public Rectangle getBounds(){ //*********************************************************
        return new Rectangle (x, y, gp.tileSize, gp.tileSize);
    }
    public void getRockImage() {

        try {

            rock = ImageIO.read(getClass().getResourceAsStream("/player/rock.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        int randomNum = ThreadLocalRandom.current().nextInt(0,500+1);
        x -= speed ;
        if (x < -50){
            y=randomNum;
            x=1500;
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        image = rock;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }

}