package view;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
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
import javafx.util.Duration;
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

    private Scene scoreScene, creditScene;

    public ViewManager() {
        MENUBUTTONS = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createBackground();
        createButtons();
        createLogo();
        howToPlay();
        createMusic();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void addMenuButton(menuButtons button) {
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + MENUBUTTONS.size() * 100);
        MENUBUTTONS.add(button);
        mainPane.getChildren().add(button);
    }
    private void createButtons() {
        createStartButton();
        createScoresButton();
        createCreditsButton();
        createExitButton();
    }
    private void createStartButton() {
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
    private void createScoresButton() { //incomplete
        menuButtons scoreButton = new menuButtons("SCORES");
        addMenuButton(scoreButton);
        scoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.setScene(scoreScene);
            }
        });

        Group group = new Group();
        scoreScene = new Scene(group, 800, 600);
        Image bgImg = new Image("https://img.itch.zone/aW1hZ2UvOTg0Nzc1LzU2MDE4MDYucG5n/794x1000/XZyJ50.png",800, 600, false, false);
        ImageView background = new ImageView(bgImg);
        ImageView background2 = new ImageView(bgImg);
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
        group.getChildren().addAll(background, background2);

        Rectangle r = new Rectangle(200,100,400,400);
        r.setFill(Color.IVORY);
        r.setStrokeWidth(4);
        r.setStroke(Color.BLACK);

        textBoxes scoresText = new textBoxes("SCORES");
        scoresText.setLayoutX(300);
        scoresText.setLayoutY(-50);

        menuButtons backButton = new menuButtons("BACK");
        backButton.setLayoutX(300);
        backButton.setLayoutY(325 + MENUBUTTONS.size() * 100);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.setScene(mainScene);
            }
        });

        group.getChildren().addAll(r, scoresText, backButton);
    }

    private void createCreditsButton() {
        menuButtons creditButton = new menuButtons("CREDIT");
        addMenuButton(creditButton);

        creditButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.setScene(creditScene);
            }
        });

        Group group = new Group();
        creditScene = new Scene(group, 800, 600);
        Image bgImg = new Image("https://img.itch.zone/aW1hZ2UvOTg0Nzc1LzU2MDE4MDYucG5n/794x1000/XZyJ50.png",800, 600, false, false);
        ImageView background = new ImageView(bgImg);
        ImageView background2 = new ImageView(bgImg);
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
        group.getChildren().addAll(background, background2);

        Rectangle r = new Rectangle(100,100,600,400);
        r.setFill(Color.IVORY);
        r.setStrokeWidth(4);
        r.setStroke(Color.BLACK);

        textBoxes creditText = new textBoxes("CREDIT");
        creditText.setLayoutX(300);
        creditText.setLayoutY(-50);

        menuButtons backButton = new menuButtons("BACK");
        backButton.setLayoutX(300);
        backButton.setLayoutY(225 + MENUBUTTONS.size() * 100);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.setScene(mainScene);
            }
        });

        Text credit0 = new Text("Made by: Reed O, Luke T, Allysa N");
        credit0.setX(120);
        credit0.setY(200);
        credit0.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        credit0.setFill(Color.BLACK);
        Text credit3 = new Text ("Art made with: https://www.pixilart.com/");
        credit3.setX(120);
        credit3.setY(230);
        credit3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        credit3.setFill(Color.BLACK);
        Text credit1 = new Text ("Background music: https://pixabay.com/music/video-games\n-8-bit-background-music-for-arcade-game-come-on-mario-164702/");
        credit1.setX(120);
        credit1.setY(260);
        credit1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        credit1.setFill(Color.BLACK);
        Text credit2 = new Text ("Coin sound: https://pixabay.com/sound-effects/collectcoin-6075/");
        credit2.setX(120);
        credit2.setY(310);
        credit2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        credit2.setFill(Color.BLACK);
        group.getChildren().addAll(r, creditText, backButton, credit0, credit1, credit2, credit3);
    }

    private void createExitButton() {
        menuButtons exitButton = new menuButtons("EXIT");
        addMenuButton(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.close();
            }
        });
    }

    private void createBackground() {
        Image bgImg = new Image("https://img.itch.zone/aW1hZ2UvOTg0Nzc1LzU2MDE4MDYucG5n/794x1000/XZyJ50.png",800, 600, false, false);
        ImageView background = new ImageView(bgImg);
        ImageView background2 = new ImageView(bgImg);

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

        mainPane.getChildren().addAll(background, background2);
    }

    private void createLogo() {
        Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/player/logo.png")));
        ImageView logoView = new ImageView(logo);
        logoView.setPreserveRatio(true);
        logoView.setFitHeight(400);
        logoView.setFitWidth(500);
        logoView.setLayoutX(150);
        logoView.setLayoutY(0);
        mainPane.getChildren().add(logoView);
    }

    public void howToPlay() {
        Text howToPlayText = new Text("HOW TO PLAY: HOLD SPACE TO MOVE BAT BRO, AVOID THE ROCKS, THE FURTHER YOU PROGRESS, THE HIGHER YOUR SCORE");
        howToPlayText.setX(12);
        howToPlayText.setY(580);
        howToPlayText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 11));
        howToPlayText.setFill(Color.WHITE);
        mainPane.getChildren().add(howToPlayText);
    }

    private void createMusic() {
        String musicFile = "/player/8-bit-background-music-for-arcade-game-come-on-mario-164702.mp3";
        Media sound = new Media(Objects.requireNonNull(getClass().getResource(musicFile)).toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.08);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
}
