/*Queue to cache the media objects to reduce time
**/

package com.core.services;


import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;
import java.util.ListIterator;

public class MediaQueue {
    private ArrayList<MediaPlayer> mediaList;

    public MediaQueue(){
        this(10);
    }

    public MediaQueue(int size){
        mediaList=new ArrayList<>(size);
    }

    public synchronized void add(MediaPlayer mediaPlayer){
        if(mediaList.contains(mediaPlayer))
            return;
        mediaList.add(mediaPlayer);
    }

    public MediaPlayer get(int index){
        return mediaList.get(index);
    }

    public ListIterator<MediaPlayer> listIterator(){
        return mediaList.listIterator();
    }

    public int size(){
        return mediaList.size();
    }

}
