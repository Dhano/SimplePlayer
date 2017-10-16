package com.ui.pages.contants;

import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;

public class SongButton extends Button {
    MediaPlayer mediaPlayer;

    public SongButton(MediaPlayer mediaPlayer){
        super(mediaPlayer.getMedia().getSource().substring(mediaPlayer.getMedia().getSource().lastIndexOf('/')));
        this.mediaPlayer=mediaPlayer;
        super.setPrefSize(340,30);
    }

    public MediaPlayer getMediaPlayer(){
        return mediaPlayer;
    }
}
