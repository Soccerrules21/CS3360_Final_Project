package view;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
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
    private long frameCounter = 0;
    private long score = 0;
    private Label scoreLabel;



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
        createBackground();
        createPlayer();
        createGameElements();
        createGameLoop();
        scoreLabel = new Label("Score: 0");
        scoreLabel.setLayoutX(675);
        scoreLabel.setLayoutY(25);
        gamePane.getChildren().add(scoreLabel);
        gameStage.show();
    }

    private void createBackground() {
        Image bgImg = new Image("https://img.itch.zone/aW1hZ2UvOTg0Nzc1LzU2MDE4MDYucG5n/794x1000/XZyJ50.png",800, 600, false, false);
        ImageView background = new ImageView(bgImg);
        ImageView background2 = new ImageView(bgImg);
        Image roofimage = new Image("https://i.imgur.com/y5w2xwP.png", 800, 75, false, false);
        ImageView roof = new ImageView(roofimage);
        ImageView roof2 = new ImageView(roofimage);
        Image floorimage = new Image("https://lesterbanks.com/lxb_metal/wp-content/uploads/2018/02/Working-With-Hexels-for-Drawing-Tiled-Pixel-Art.jpg", 800, 75, false, false);
        ImageView floor = new ImageView(floorimage);
        ImageView floor2 = new ImageView(floorimage);
        floor.setY(525);
        floor2.setY(525);

        TranslateTransition trans1 = new TranslateTransition(Duration.seconds(10), background);
        trans1.setFromX(0);
        trans1.setToX(-800);
        trans1.setInterpolator(Interpolator.LINEAR);
        trans1.setCycleCount(Animation.INDEFINITE);
        TranslateTransition trans2 = new TranslateTransition(Duration.seconds(10), background2);
        trans2.setFromX(800);
        trans2.setToX(0);
        trans2.setCycleCount(Animation.INDEFINITE);
        trans2.setInterpolator(Interpolator.LINEAR);
        ParallelTransition parTrans = new ParallelTransition(trans1, trans2);
        parTrans.play();

        TranslateTransition trans3 = new TranslateTransition(Duration.seconds(3), roof);
        trans3.setFromX(0);
        trans3.setToX(-800);
        trans3.setInterpolator(Interpolator.LINEAR);
        trans3.setCycleCount(Animation.INDEFINITE);
        TranslateTransition trans4 = new TranslateTransition(Duration.seconds(3), roof2);
        trans4.setFromX(800);
        trans4.setToX(0);
        trans4.setCycleCount(Animation.INDEFINITE);
        trans4.setInterpolator(Interpolator.LINEAR);
        TranslateTransition trans5 = new TranslateTransition(Duration.seconds(3), floor);
        trans5.setFromX(0);
        trans5.setToX(-800);
        trans5.setInterpolator(Interpolator.LINEAR);
        trans5.setCycleCount(Animation.INDEFINITE);
        TranslateTransition trans6 = new TranslateTransition(Duration.seconds(3), floor2);
        trans6.setFromX(800);
        trans6.setToX(0);
        trans6.setCycleCount(Animation.INDEFINITE);
        trans6.setInterpolator(Interpolator.LINEAR);
        ParallelTransition partrans = new ParallelTransition(trans3, trans4, trans5, trans6);
        partrans.play();

        gamePane.getChildren().addAll(background, background2, roof, roof2, floor, floor2);
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

            @Override
            public void handle (long now) {
                movePlayer();
                moveGameElements();
                checkElements();
                checkCollision();
                updateScoreLabel();

                if (frameCounter % 1500 == 0)
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

            int minY = 50; 
            int maxY = 500; 

            if (newY >= minY && newY <= maxY) {
                playerView.setY(newY);
            }
    }
    private void checkCollision()  
    {
        for(int i = 0; i < rocks.length; i++){
            if ((PLAYER_RADIUS + ROCK_RADIUS) > calculateDistance(playerView.getX() , rocks[i].getX() , playerView.getY() , rocks[i].getY() )){
                gameStage.close();
                gameTimer.stop();
                menuStage.show();
            }
        }
    }
    private double calculateDistance(double x1, double x2, double y1, double y2)  
    {
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }

    private void updateScoreLabel() {
        if (frameCounter % 6 == 0) {
            score++;
            scoreLabel.setText("Score: " + score);
        }
    }
}
