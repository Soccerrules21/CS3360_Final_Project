package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bat Bro");

        GamePanel gamePanel = new GamePanel();
        Scene scene = new Scene(gamePanel, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        gamePanel.startGameThread();
    }
}

class GamePanel extends Pane {

    public final int tileSize = 48;

    final int screenWidth = 768;
    final int screenHeight = 576;
    KeyHandler keyH = new KeyHandler();
    Player player;
    Rock rock;
    Rock rock2;
    Rock rock3;
    Rock rock4;
    boolean collision;
    Canvas canvas;

    public GamePanel() {
        this.player = new Player(this, this.keyH);
        this.rock = new Rock(this);
        this.rock2 = new Rock(this);
        this.rock3 = new Rock(this);
        this.rock4 = new Rock(this);
        this.collision = false;
        this.setPrefSize(768, 576);
        this.setBackground(Background.fill(new Color(1, 1, 1, 1)));
        this.setFocusTraversable(true);

        this.setOnKeyPressed(e -> keyH.handleKeyPress(e.getCode()));
        this.setOnKeyReleased(e -> keyH.handleKeyRelease(e.getCode()));

        canvas = new Canvas(screenWidth, screenHeight);
        getChildren().add(canvas);
    }

    public void startGameThread() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                checkCollision();
                repaint();
            }
        }.start();
    }

    public void update() {
        if (!collision) {
            player.update();
            rock.update();
            rock2.update();
            rock3.update();
            rock4.update();
        }
    }

    public void checkCollision() {
        javafx.geometry.Rectangle2D playerBounds = player.getBounds();
        javafx.geometry.Rectangle2D rockBounds = rock.getBounds();
        javafx.geometry.Rectangle2D rock2Bounds = rock2.getBounds();
        javafx.geometry.Rectangle2D rock3Bounds = rock3.getBounds();
        javafx.geometry.Rectangle2D rock4Bounds = rock4.getBounds();

        if (playerBounds.intersects(rockBounds) ||
                playerBounds.intersects(rock2Bounds) ||
                playerBounds.intersects(rock3Bounds) ||
                playerBounds.intersects(rock4Bounds)) {
            collision = true;
        }
    }

    public void repaint() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, screenWidth, screenHeight);

        player.draw(gc);
        rock.draw(gc);
        rock2.draw(gc);
        rock3.draw(gc);
        rock4.draw(gc);
    }
}


class KeyHandler {
    private final Set<KeyCode> pressedKeys;

    public KeyHandler() {
        this.pressedKeys = new HashSet<>();
    }

    public void handleKeyPress(KeyCode code) {
        pressedKeys.add(code);
    }

    public void handleKeyRelease(KeyCode code) {
        pressedKeys.remove(code);
    }

    public boolean isSpacePressed() {
        return pressedKeys.contains(KeyCode.SPACE);
    }
}


class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.setDefaultValues();
        this.getPlayerImage();
    }

    public void setDefaultValues() {
        this.x = 100;
        this.y = 500;
        this.speed = 4;
    }

    public void getPlayerImage() {
        try {
            this.bat = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/player/guy.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.isSpacePressed()) {
            this.y -= this.speed;
        } else {
            this.y += this.speed;
        }
    }

    public void draw(GraphicsContext g2) {
        g2.drawImage(bat, x, y, gp.tileSize, gp.tileSize);
    }

    public javafx.geometry.Rectangle2D getBounds() {
        return new javafx.geometry.Rectangle2D(x, y, gp.tileSize, gp.tileSize);
    }
}

class Rock extends Entity {
    GamePanel gp;

    public Rock(GamePanel gp) {
        this.gp = gp;
        setDefaultValues();
        getRockImage();
    }

    public void setDefaultValues() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 500 + 1);
        x = 600;
        y = randomNum;
        speed = 4;
    }

    public javafx.geometry.Rectangle2D getBounds() {
        return new javafx.geometry.Rectangle2D(x, y, gp.tileSize, gp.tileSize);
    }

    public void getRockImage() {
        try {
            this.rock = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/player/rock.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 500 + 1);
        x -= speed;
        if (x < -50) {
            y = randomNum;
            x = 800;
        }
    }

    public void draw(GraphicsContext g2) {
        g2.drawImage(rock, x, y, gp.tileSize, gp.tileSize);
    }
}

class Entity {
    public int x;
    public int y;
    public int speed;
    public Image bat;
    public Image rock;


    public Entity() {
    }
}
