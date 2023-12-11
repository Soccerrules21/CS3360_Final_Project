package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class textBoxes extends Label
{
    public textBoxes(String text) {
        setPrefWidth(600);
        setPrefHeight(400);
        setPadding(new Insets(40, 40, 40,40));
        setText(text);
        setWrapText(true);
        setTextBoxesFont();
    }

    private void setTextBoxesFont() {
        try {
            String FRONT_PATH = "src/main/java/model/resource/dogica.ttf";
            setFont(Font.loadFont(new FileInputStream(FRONT_PATH),23));
        }catch(FileNotFoundException e){
            setFont(Font.font("dogica", 23));
        }
    }
}
