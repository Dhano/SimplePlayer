package com.ui.pages;

import com.ui.pages.contants.DefaultController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;



public class SongsListPage implements DefaultController {
    @FXML
    AnchorPane root;

    @FXML
    ListView songsList;

    @FXML protected void intialize(){

    }

    @Override public Node getRoot(){
        return root;
    }


}
