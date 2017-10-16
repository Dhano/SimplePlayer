/*Create mediaLocator for each root directory and add the specified files to the mediaQueue */
package com.core;

import com.core.services.MediaLocator;
import com.core.services.MediaQueue;
import java.io.File;


public class MediaReader {
    private MediaQueue mediaQueue;
    private String[] mediaTypes;
    private MediaLocator[] mediaLocators;
    private File[] roots;

    public MediaReader(String[] mediaTypes){
        this.mediaTypes=mediaTypes;
        roots =File.listRoots();
        mediaQueue=new MediaQueue();
        mediaLocators=new MediaLocator[roots.length];
        fetch();
    }

    private void fetch(){
        int i=0;

        for (File root:roots) {
            mediaLocators[i++]=new MediaLocator(mediaTypes,mediaQueue,root);
            System.out.println("Thread for"+root.getPath());
        }
    }

    public boolean isComplete(){
        for (MediaLocator locator:mediaLocators) {
            if(!(locator==null)) {
                if (locator.isAlive())
                    return false;
            }
        }
        return true;
    }

    public MediaQueue getMediaQueue(){
        return mediaQueue;
    }
}
