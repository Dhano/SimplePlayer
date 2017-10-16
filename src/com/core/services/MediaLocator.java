/*It is thread which is used to locate media files with specified extension and add it to the media queue*/

package com.core.services;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.io.File;
import java.io.FilenameFilter;

public class MediaLocator extends Thread implements FilenameFilter{


    private String[] mediaTypes;
    private MediaQueue mediaQueue;
    private File directory;
    public MediaLocator(String[] mediaTypes,MediaQueue mediaQueue,File directory){
        this.mediaTypes=mediaTypes;
        this.mediaQueue=mediaQueue;
        this.directory=directory;
        start();
    }

    public void run(){
        checkFiles(this.directory);
        System.out.println("**********************************Finished");
    }

    @Override
    public boolean accept(File dir, String name) {
        try {
            //System.out.println(dir.getName());
            if (dir.getName().indexOf('.') == -1)
                return false;
            //System.out.println("For file"+dir.getName().substring(dir.getName().lastIndexOf('.')));
            if (contains(dir.getName().substring(dir.getName().indexOf('.')), mediaTypes))
                return true;
            return false;
        }catch (Exception e){
            System.out.println("Exception in accept"+e);
        }
        return false;
    }

    private boolean contains(String key,String[] values){
        try{
        for (String s:values) {
            if(s.equalsIgnoreCase(key))
                return true;
        }
        return false;
        }catch (Exception e) {
            System.out.println("Exception in contains" + e);
            return false;
        }

    }

    private void  checkFiles(File directoryOrFile) {
        //System.out.println("Going for "+directoryOrFile);
        if(!directoryOrFile.canRead()||!directoryOrFile.canExecute()){
            System.out.println("cannot read");
            return;
        }
        if(directoryOrFile.isHidden()&&!(directoryOrFile.getParent()==null)) {
            System.out.println("cannot read");
            return;
        }
        //System.out.println(directory+directory.getPath());
        try {
            if (directoryOrFile.isDirectory()) {
              //  System.out.println("It is a directory "+directoryOrFile.getPath());
                File[] files = directoryOrFile.listFiles();
                for (File f:files) {
                    //System.out.println("Iniside "+directoryOrFile+":"+f);
                }
                //System.in.read();
                for (File file : files) {
                    checkFiles(file);
                }
                return;
            }
          //  System.out.println("It is a File "+directoryOrFile.getPath());
        /*If the file is not directory*/
            if (accept(directoryOrFile, "")) {
                mediaQueue.add(new MediaPlayer(new Media(directoryOrFile.toURI().toString())));
                System.out.println("File Accepted:" + directoryOrFile);
            }
        } catch (Exception e) {
            System.out.println("Exception in checkfiles" + e);
            return;
        }
    }
}
