/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import model.ScrollFreeTextArea;

/**
 * FXML Controller class
 *
 * @author MyPC
 */
public class MainmenuController implements Initializable {

    @FXML
    private AnchorPane addNewTaskPane;
    @FXML
    private JFXButton newTaskCreate;
    @FXML
    private JFXButton newTaskCancel;
    @FXML
    private ScrollFreeTextArea newTaskDescription;
    @FXML
    private JFXDatePicker newTaskDate;
    @FXML
    private HBox newTaskPriority;
    @FXML
    private ToggleGroup prioritygroup;
    @FXML
    private JFXTimePicker newTaskTime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void mouseExitButton(MouseEvent event) {
    }

    @FXML
    private void mouseEnterButton(MouseEvent event) {
    }
    
}
