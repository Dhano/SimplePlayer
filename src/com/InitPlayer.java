package com;


import com.core.MediaReader;
import javafx.application.Application;
import javafx.stage.Stage;
import org.controlsfx.glyphfont.FontAwesome;

public class InitPlayer extends Application{
    public static void main(String arsg[]){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String str[]={".mp3"};
        MediaReader mediaReader=new MediaReader(str);
        while (!mediaReader.isComplete());
        mediaReader.getMediaQueue().get(0).play();
    }
}
