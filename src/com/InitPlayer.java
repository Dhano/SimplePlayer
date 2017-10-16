package com;


import com.core.MediaReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controlsfx.glyphfont.FontAwesome;

import java.net.URL;

public class InitPlayer extends Application{
    public static void main(String arsg[]){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String str[]={".mp3"};
        //MediaReader mediaReader=new MediaReader(str);
        //while (!mediaReader.isComplete());
        Parent root = FXMLLoader.load(getClass().getResource("ui//pages//fxml//start.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.show();
    }
}
