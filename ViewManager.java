package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.menuButtons;
//import model.menuSubscenes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewManager
{
    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private final static int MENU_BUTTONS_START_X = 300;
    private final static int MENU_BUTTONS_START_Y = 200;
    List<menuButtons> MENUBUTTONS;


    public ViewManager()
    {
        MENUBUTTONS = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButtons();
        createBackground();
        createLogo();

        /*menuSubscenes subScene = new menuSubscenes(); //NOT COMPLETE
        subScene.setLayoutX(100);
        subScene.setLayoutY(100);
        mainPane.getChildren().add(subScene);*/
    }

    private void createSubScenes(){ //****************************************** NOT COMPLETE

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
    }
    private void createCreditsButton()
    {
        menuButtons creditsButton = new menuButtons("CREDIT");
        addMenuButton(creditsButton);
    }
    private void createExitButton()
    {
        menuButtons exitButton = new menuButtons("EXIT");
        addMenuButton(exitButton);
    }

    private void createBackground()//************************************************************************************
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
}