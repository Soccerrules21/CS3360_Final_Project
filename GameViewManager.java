package view;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Random;

public class GameViewManager
{
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;

    private Stage menuStage;
    Image player = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/player/guy.png")));
    ImageView playerView = new ImageView(player);
    Image rock = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/player/rock.png")));
    ImageView rockView = new ImageView(rock);

    private boolean isSpacePressed;
    private AnimationTimer gameTimer;

    private ImageView[] rocks;
    Random randomPositionGenerator;

    private double rockSpeed = 2;

    private final static int PLAYER_RADIUS = 20;
    private final static int ROCK_RADIUS = 20;


    public GameViewManager()
    {
        initializeStage();
        createKeyListeners();
        randomPositionGenerator = new Random();
    }

    private void createKeyListeners()
    {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.SPACE)
                {
                    isSpacePressed=true;
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.SPACE)
                {
                    isSpacePressed=false;
                }
            }
        });
    }

    private void initializeStage()
    {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);

    }

    public void createNewGame(Stage menuStage)
    {
        this.menuStage = menuStage;
        this.menuStage.hide();
        createPlayer();
        createGameElements();
        createGameLoop();
        gameStage.show();
    }

    private void createGameElements() {
        rocks = new ImageView[4];
        for(int i = 0; i < rocks.length; i++) {
            rocks[i] = new ImageView(rock);
            setNewElementPosition(rocks[i]);
            gamePane.getChildren().add(rocks[i]);
        }
    }

    private void moveGameElements() {
        for (int i = 0; i < rocks.length; i++) {
            rocks[i].setX(rocks[i].getX() - rockSpeed);
        }
    }

    private void checkElements() {
        for (int i = 0; i < rocks.length; i++) {
            if(rocks[i].getX() + rocks[i].getFitWidth() < -100)
            setNewElementPosition(rocks[i]);
        }
    }

    private void setNewElementPosition(ImageView image) {

        int minY = 50;  // Adjust this value based on your floor position
        int maxY = 500;
        image.setY(-randomPositionGenerator.nextInt(maxY - minY) + maxY);
        image.setX(randomPositionGenerator.nextInt(GAME_WIDTH) + 800);
    }

    private void createPlayer()
    {
        playerView.setPreserveRatio(true);
        playerView.setFitHeight(48);
        playerView.setFitWidth(48);
        playerView.setX(GAME_WIDTH-750);
        playerView.setY(GAME_HEIGHT-100);
        gamePane.getChildren().add(playerView);
    }

    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            private long frameCounter = 0;
            @Override
            public void handle (long now) {
                movePlayer();
                moveGameElements();
                checkElements();
                checkCollision();

                if (frameCounter % 2000 == 0)
                {
                    rockSpeed = rockSpeed + .25;
                }
                frameCounter++;
            }
        };

        gameTimer.start();
    }
    private void movePlayer() {
        int newY = 0;

            if (isSpacePressed) {
                newY = (int) (playerView.getY() - 2);
            } else {
                newY = (int) (playerView.getY() + 2);
            }

            int minY = 50;  // Minimum Y position
            int maxY = 500;  // Maximum Y position

            // Check if the new Y position is within the boundaries
            if (newY >= minY && newY <= maxY) {
                playerView.setY(newY);
            }
    }
    private void checkCollision()  //**********************************************************************************************************************
    {
        for(int i = 0; i < rocks.length; i++){
            if ((PLAYER_RADIUS + ROCK_RADIUS) > calculateDistance(playerView.getX() , rocks[i].getX() , playerView.getY() , rocks[i].getY() )){
                gameStage.close();
                gameTimer.stop();
                menuStage.show();
            }
        }
    }
    private double calculateDistance(double x1, double x2, double y1, double y2)  //*******************************************************************************
    {
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }
}
