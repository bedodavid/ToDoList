package model;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
//import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTimePicker;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.ScrollFreeTextArea;

public class MainmenuBase extends AnchorPane {

    protected final VBox vBox;
    protected final HBox hBox;
    protected final ScrollFreeTextArea newTaskDescription;
    protected final HBox hBox0;
    protected final VBox vBox0;
    protected final JFXDatePicker newTaskDate;
    protected final JFXTimePicker newTaskTime;
    protected final VBox vBox1;
    protected final Label label;
    protected final HBox newTaskPriority;
    protected final JFXRadioButton highPriority;
    protected final ToggleGroup prioritygroup;
    protected final JFXRadioButton mediumPriority;
    protected final JFXRadioButton lowPriority;
    protected final JFXRadioButton noPriority;
    protected final HBox hBox1;
    protected final JFXButton newTaskCreate;
    protected final JFXButton newTaskCancel;
    private String eventName;
    

    public MainmenuBase() {
        
        eventName="";
        vBox = new VBox();
        hBox = new HBox();
        newTaskDescription = new ScrollFreeTextArea();
        hBox0 = new HBox();
        vBox0 = new VBox();
        newTaskDate = new JFXDatePicker();
        newTaskTime = new JFXTimePicker();
        vBox1 = new VBox();
        label = new Label();
        newTaskPriority = new HBox();
        prioritygroup = new ToggleGroup();
        highPriority = new JFXRadioButton();
        mediumPriority = new JFXRadioButton();
        lowPriority = new JFXRadioButton();
        noPriority = new JFXRadioButton();
        hBox1 = new HBox();
        newTaskCreate = new JFXButton("Save");
        newTaskCancel = new JFXButton("Cancel");
        

        setPrefHeight(145.0);
        setPrefWidth(470.0);
        setStyle("-fx-background-color: #eeeeee;");

        vBox.setMargin(newTaskDescription,new Insets(0.0, 15.0, 0.0, 0.0)); 
        hBox0.setPrefHeight(70.0);
        hBox0.setPrefWidth(200.0);
        
        

        vBox0.setAlignment(javafx.geometry.Pos.CENTER);
        vBox0.setPrefHeight(70.0);
        vBox0.setPrefWidth(240.0);
        vBox0.setMargin(newTaskDate, new Insets(0.0, 0.0, 5.0, 0.0) );

        vBox1.setAlignment(javafx.geometry.Pos.CENTER);
        vBox1.setPrefHeight(70.0);
        vBox1.setPrefWidth(240.0);
        vBox1.setMargin(label,new Insets(0.0, 5.0, 0.0, 0.0));

        label.setPrefHeight(20.0);
        label.setPrefWidth(120.0);
        label.setText("Priority");

        newTaskPriority.setPrefHeight(30.0);
        newTaskPriority.setPrefWidth(200.0);

        highPriority.setStyle("-fx-background-color: #ff0000;");
        highPriority.setPrefSize(44, 25);
        HBox.setMargin(highPriority, new Insets(2.0, 2.0, 0.0, 0.0));

          

        mediumPriority.setStyle("-fx-background-color: #0d7200;");
        mediumPriority.setPrefSize(44, 25);
        HBox.setMargin(mediumPriority, new Insets(2.0, 2.0, 0.0, 0.0));                

        lowPriority.setStyle("-fx-background-color: #ffe500;");
        lowPriority.setPrefSize(44, 25);
        HBox.setMargin(lowPriority, new Insets(2.0, 2.0, 0.0, 0.0));

        noPriority.setStyle("-fx-background-color: #aeaeab;");
        noPriority.setPrefSize(44, 25);     
        HBox.setMargin(noPriority, new Insets(2.0, 2.0, 0.0, 0.0));
        HBox.setMargin(vBox1, new Insets(0.0, 10.0, 0.0, 0.0));

        highPriority.setToggleGroup(prioritygroup);      
        mediumPriority.setToggleGroup(prioritygroup);
        lowPriority.setToggleGroup(prioritygroup);
        noPriority.setToggleGroup(prioritygroup);
        
        hBox1.setAlignment(javafx.geometry.Pos.CENTER);
        hBox1.setPrefHeight(30.0);
        hBox1.setPrefWidth(500.0);

        newTaskCreate.setOnMouseEntered(this::mouseEnterButton);
        newTaskCreate.setOnMouseExited(this::mouseExitButton);
        newTaskCreate.setOnMousePressed(this::mousePressed);
        newTaskCreate.setStyle("-fx-background-color: #19023f; -fx-text-fill: white;");
        //newTaskCreate.setStyle("-fx-text-fill: white;");
        newTaskCreate.setPrefSize(90,30);
        HBox.setMargin(newTaskCreate, new Insets(0.0, 25.0, 0.0, 0.0));

        newTaskCancel.setOnMouseEntered(this::mouseEnterButton);
        newTaskCancel.setOnMouseExited(this::mouseExitButton);
        newTaskCancel.setOnMousePressed(this::mousePressed);
        newTaskCancel.setStyle("-fx-background-color: #19023f; -fx-text-fill: white;");
        newTaskCancel.setPrefSize(90,30);
        HBox.setMargin(newTaskCancel, new Insets(0.0, 25.0, 0.0, 0.0));
        VBox.setMargin(hBox1, new Insets(5.0, 0.0, 0.0, 0.0));

        
        vBox.getChildren().add(newTaskDescription);
        vBox0.getChildren().add(newTaskDate);
        vBox0.getChildren().add(newTaskTime);
        hBox0.getChildren().add(vBox0);
        vBox1.getChildren().add(label);
        newTaskPriority.getChildren().add(highPriority);
        newTaskPriority.getChildren().add(mediumPriority);
        newTaskPriority.getChildren().add(lowPriority);
        newTaskPriority.getChildren().add(noPriority);
        vBox1.getChildren().add(newTaskPriority);
        hBox0.getChildren().add(vBox1);
        vBox.getChildren().add(hBox0);
        hBox1.getChildren().add(newTaskCreate);
        hBox1.getChildren().add(newTaskCancel);
        vBox.getChildren().add(hBox1);
        getChildren().add(vBox);

    }

    public ScrollFreeTextArea getNewTaskDescription() {
        return newTaskDescription;
    }

    public JFXDatePicker getNewTaskDate() {
        return newTaskDate;
    }

    public JFXTimePicker getNewTaskTime() {
        return newTaskTime;
    }

    public JFXRadioButton getHighPriority() {
        return highPriority;
    }

    public JFXRadioButton getMediumPriority() {
        return mediumPriority;
    }

    public JFXRadioButton getLowPriority() {
        return lowPriority;
    }

    public JFXRadioButton getNoPriority() {
        return noPriority;
    }

    public JFXButton getNewTaskCreate() {
        return newTaskCreate;
    }

    public JFXButton getNewTaskCancel() {
        return newTaskCancel;
    }

    protected  Node mouseEnterButton(javafx.scene.input.MouseEvent mouseEvent){
        return (Node) mouseEvent.getSource();
    };

    protected  Node mouseExitButton(javafx.scene.input.MouseEvent mouseEvent){
        return (Node) mouseEvent.getSource();
    };

    public void  mousePressed(MouseEvent mouseEvent){
        
        eventName=((Button )mouseEvent.getSource()).getText();
        System.out.println(eventName);
    };

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    

    
    
}
