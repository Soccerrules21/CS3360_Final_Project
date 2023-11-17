package model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class menuButtons extends Button
{
    private static final String BUTTON_STYLE = "-fx-background-color: GRAY; -fx-border-width: 4px; -fx-border-color: DARKGRAY;";

    public menuButtons(String text)
    {
        setText(text);
        setButtonFont();
        setPrefWidth(190);
        setPrefHeight(49);
        setStyle(BUTTON_STYLE);
        initializeButtonListener();
    }

    private void setButtonFont()
    {
        try {
            String FRONT_PATH = "src/main/java/model/resource/dogica.ttf";
            setFont(Font.loadFont(new FileInputStream(FRONT_PATH),23));
        }catch(FileNotFoundException e){
            setFont(Font.font("dogica", 23));
        }
    }

    private void setButtonStyle()
    {
        setStyle(BUTTON_STYLE);
        setPrefHeight(49);
        setLayoutY(getLayoutY());
    }

    private void initializeButtonListener()
    {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY))
                {
                    setButtonStyle();
                }
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }
        });
    }
}