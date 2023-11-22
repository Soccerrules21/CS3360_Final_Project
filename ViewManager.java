package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.menuButtons;
import model.textBoxes;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.util.Objects;

import java.util.*;

public class ViewManager
{
    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private MediaPlayer mediaPlayer;
    private final static int MENU_BUTTONS_START_X = 300;
    private final static int MENU_BUTTONS_START_Y = 200;
    List<menuButtons> MENUBUTTONS;

    Scene scoreScene, creditScene;

    public ViewManager()
    {
        MENUBUTTONS = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButtons();
        createBackground();//********************************************************************calling the create background method************************************
        createLogo();
        howToPlay();
        createMusic();

    }

    public Stage getMainStage()
    {
        return mainStage;
    }

    public void addMenuButton(menuButtons button)
    {
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + MENUBUTTONS.size() * 100);
        MENUBUTTONS.add(button);
        mainPane.getChildren().add(button);
    }
    private void createButtons()
    {
        createStartButton();
        createScoresButton();
        createCreditsButton();
        createExitButton();
    }
    private void createStartButton()
    {
        menuButtons startButton = new menuButtons("PLAY");
        addMenuButton(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameViewManager gameManager = new GameViewManager();
                gameManager.createNewGame(mainStage);
            }
        });
    }
    private void createScoresButton()
    {
        menuButtons scoreButton = new menuButtons("SCORES");
        addMenuButton(scoreButton);
        scoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.setScene(scoreScene);
            }
        });

        Group group = new Group();
        scoreScene = new Scene(group, 800, 600, Color.LIGHTGRAY);
        Rectangle r = new Rectangle(200,100,400,400);
        r.setFill(Color.GRAY);
        r.setStrokeWidth(4);
        r.setStroke(Color.DARKGRAY);
        group.getChildren().add(r);

        textBoxes scoresText = new textBoxes("SCORES");
        scoresText.setLayoutX(300);
        scoresText.setLayoutY(-50);
        group.getChildren().add(scoresText);
    }
    private void createCreditsButton()
    {
        menuButtons creditButton = new menuButtons("CREDIT");
        addMenuButton(creditButton);

        creditButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.setScene(creditScene);
            }
        });

        Group group = new Group();
        creditScene = new Scene(group, 800, 600, Color.LIGHTGRAY);
        Rectangle r = new Rectangle(200,100,400,400);
        r.setFill(Color.GRAY);
        r.setStrokeWidth(4);
        r.setStroke(Color.DARKGRAY);
        group.getChildren().add(r);

        textBoxes creditText = new textBoxes("CREDIT");
        creditText.setLayoutX(300);
        creditText.setLayoutY(-50);
        group.getChildren().add(creditText);
    }
    private void createExitButton()
    {
        menuButtons exitButton = new menuButtons("EXIT");
        addMenuButton(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.close();
            }
        });
    }

    private void createBackground()//*************************************************************************************************************************************************
    {

    }

    private void createLogo()
    {
        Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/player/logo.png")));
        ImageView logoView = new ImageView(logo);
        logoView.setPreserveRatio(true);
        logoView.setFitHeight(400);
        logoView.setFitWidth(500);
        logoView.setLayoutX(150);
        logoView.setLayoutY(0);
        mainPane.getChildren().add(logoView);
    }

    public void howToPlay()
    {
    Text howToPlayText = new Text("HOW TO PLAY: HOLD SPACE TO MOVE BAT BRO, AVOID THE ROCKS, THE FURTHER YOU PROGRESS, THE HIGHER YOUR SCORE");
    howToPlayText.setX(12);
    howToPlayText.setY(580);
    howToPlayText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 11));
    mainPane.getChildren().add(howToPlayText);
    }

    private void createMusic() {
        String musicFile = "/player/8-bit-background-music-for-arcade-game-come-on-mario-164702.mp3";
        Media sound = new Media(Objects.requireNonNull(getClass().getResource(musicFile)).toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
}
