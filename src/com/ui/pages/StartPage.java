package com.ui.pages;

import com.core.MediaReader;
import com.ui.pages.contants.DefaultController;
import com.ui.pages.contants.SongButton;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;

public class StartPage implements DefaultController {

    @FXML
    AnchorPane rootPanel,songsListPanel,playingPanel;

    /*+++++++++++++++++++++ROOT++++++++++++++++++++++++++++*/
    @FXML
    Button btn_Start;
    MediaReader mediaReader;

    /*+++++++++++++++++++++SONGS LIST++++++++++++++++++++++++++*/
    @FXML
    ListView<SongButton> songsList;
    ObservableList<SongButton> observableList;
    /*++++++++++++++++++++++++PLAYING+++++++++++++++++++++++*/
    @FXML Button btn_Next;
    @FXML Button btn_Play;
    @FXML Button btn_Previous;
    @FXML Button btn_Close_Playing;
    @FXML
    Slider progressSlider,volumeSlider;
    SongButton currentSong;

    DoubleProperty progressProperty;
    DoubleProperty volumeProperty;






    @FXML protected void initialize(){
        initializeRootPanel();
        initializePlayingPanel();
    }

    public void initializeRootPanel(){
        songsListPanel.setVisible(false);
        try {
            System.out.println("I am inside initialize of start page");
            btn_Start.setDisable(true);
            String str[] = {".mp3"};
            mediaReader = new MediaReader(str);
            new Thread(){
                public void run(){
                    while (!mediaReader.isComplete());
                    btn_Start.setDisable(false);
                    initializeSongsListPanel();
                }
            }.start();

        }catch (Exception e){System.out.println("Inisde initiallizeRootPanel");}
    }

    public void initializeSongsListPanel(){
        try{
             observableList= FXCollections.observableArrayList();


            for(int i=0;i<mediaReader.getMediaQueue().size();i++){
                MediaPlayer mediaPlayer=mediaReader.getMediaQueue().get(i);
                SongButton songButton=new SongButton(mediaPlayer);
                songButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        showPlayingPanel(event);
                    }
                });
                observableList.add(songButton);
            }
            songsList.setItems(observableList);
        }
        catch (Exception e){System.out.println("Inisde initiallizeSongList");}
    }

    public void initializePlayingPanel(){
        try{
            progressProperty=progressSlider.valueProperty();
            volumeProperty=volumeSlider.valueProperty();
            playingPanel.setVisible(false);
            btn_Play.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(MediaPlayer.Status.valueOf("PAUSED")==currentSong.getMediaPlayer().getStatus())
                        playSong();
                    else
                        pauseSong();
                }
            });

            btn_Next.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    nextSong();
                }
            });

            btn_Previous.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    previousSong();
                }
            });
        }
        catch (Exception e){
            System.out.println("Inisde initilaize of PlayingPanel");
        }
    }

    /**************************************************ROOT PANEL*****************************************************************/

    @FXML private void showSongsList(ActionEvent actionEvent){
        try{songsListPanel.setVisible(true);}catch (Exception e){System.out.println(e+"hey are you there");}
    }




    /**************************************************SONGS LIST PANEL***********************************************************/

    @FXML protected void hideSongsList(ActionEvent actionEvent){
        /*Code to hide the list*/
        songsListPanel.setVisible(false);
    }


    /**************************************************PLAYING PANEL***************************************************************/

    @FXML protected void hidePlayingPanel(ActionEvent actionEvent){
        playingPanel.setVisible(false);
    }

    void showPlayingPanel(ActionEvent actionEvent){
        playingPanel.setVisible(true);
        currentSong=((SongButton)actionEvent.getSource());
        playSong();

    }

    SongButton getNextSong(){
        int index=observableList.indexOf(currentSong);
        if(index+1>=observableList.size())
            return observableList.get(0);
        return observableList.get(observableList.indexOf(currentSong)+1);
    }

    SongButton getPreviousSong(){
        int index=observableList.indexOf(currentSong);
        if(index-1==-1)
            return observableList.get(observableList.size()-1);
        return observableList.get(observableList.indexOf(currentSong)-1);
    }

    void playSong(){
        //progressProperty.bindBidirectional(currentSong.getMediaPlayer().cycleDurationProperty());
        currentSong.getMediaPlayer().play();
    }

    void pauseSong(){
        currentSong.getMediaPlayer().pause();
    }

    void nextSong(){
        currentSong.getMediaPlayer().stop();
        currentSong=getNextSong();
        playSong();
    }

    void previousSong(){
        currentSong.getMediaPlayer().stop();
        currentSong=getPreviousSong();
        playSong();
    }

    @Override
    public Node getRoot(){
        return rootPanel;
    }
}
