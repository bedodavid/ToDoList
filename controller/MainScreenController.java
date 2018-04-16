package controller;

import Database.DatabaseHandler;
import animation.MoveNode;
import animation.Shaker;
import model.User;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Lists;
import model.MainmenuBase;
import model.ScrollFreeTextArea;
import model.Task;
import model.UserMenuAdditions;



public class MainScreenController  {
    boolean menuIn=false;
    int useridentification;
    private String username;
    private int userid;
    private int taskId;
    private int listId;
    private short taskPriority;
    private boolean firstUpdate=false;
    private String listgroup;
    Lists selectedList;
   

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainScreenBaseAnchorpane;

    @FXML
    private ImageView addItemShowMenu;

    @FXML
    private Label userMenuUsername;

    @FXML
    private ImageView userMenuPicture;

    @FXML
    private AnchorPane taskScreenPane;

    @FXML
    private ScrollPane taskScroll;

    @FXML
    private VBox taskContainerVBox;

    @FXML
    private Label taskListName;

    @FXML
    private HBox addNewTaskHBox;

    @FXML
    private AnchorPane userMenuPane;

    @FXML
    private Label userMenuToday;

    @FXML
    private Label userMenuTomorrow;

    @FXML
    private Label userMenuNext7days;

    @FXML
    private Label userMenuNextMonth;

    @FXML
    private VBox usermenuVboxProject;

    @FXML
    private Label userMenuWork;

    @FXML
    private Label userMenuHealth;

    @FXML
    private Label userMenuPersonal;

    @FXML
    private Label userMenuShoppingList;

    @FXML
    private Label userMenuMeetings;

    @FXML
    private Label userMenuMovies;

    @FXML
    private Label userMenuMusic;

    @FXML
    private Label userMenuFamily;

    @FXML
    private Label userMenuBook;

    @FXML
    private Label userMenuTheater;

    @FXML
    private JFXListView<?> userMenuListViewCreated;

    @FXML
    private HBox userMenuAddNewProject;

    @FXML
    private VBox proba;

    @FXML
    private AnchorPane mainScreenNewProjectPane;

    @FXML
    private JFXTextField mainScreenNewProjectName;

    @FXML
    private JFXButton mainScreenNewProjectCreate;

    @FXML
    private JFXButton mainScreenNewProjectCancel;

    @FXML
    private AnchorPane addNewTaskPane;

    @FXML
    private JFXTextArea newTaskDescription;

    @FXML
    private JFXButton newTaskSave;

    @FXML
    private JFXButton newTaskCancel;

    @FXML
    private JFXDatePicker newTaskDate;

    @FXML
    private JFXTimePicker newTaskTime;

    @FXML
    private HBox newTaskPriority;

    @FXML
    private ToggleGroup prioritygroup;
    
    @FXML
    private JFXRadioButton priorityFirst;
     @FXML
    private JFXRadioButton prioritySecond;
     @FXML
    private JFXRadioButton priorityThird;
     @FXML
    private JFXRadioButton priorityFourth;
     
     
    @FXML
    private void selectLabel(MouseEvent event){
    String source = ((Label)event.getSource()).getText();
    listgroup="Standard";
    Lists list=new Lists(source,listgroup,userid);
    DatabaseHandler handler=new DatabaseHandler();
    listId=handler.getListIDFromDatabase(list);
    showProject(source);
    }
    
    @FXML
    private void selectList(MouseEvent event){
    String source = ((Label)((HBox)((JFXListView)event.getSource()).getSelectionModel().getSelectedItem()).getChildren().get(0)).getText();
    listgroup="Created";
    //getListParameters(source,listgroup,userid);
   

    System.out.println(listId+source+" "+listgroup+" "+ userid);
       showProject(source);
    }
    
    @FXML
    private void mouseEnterList(MouseEvent event){
        ((Node)event.getSource()).setStyle("-fx-background-color: #dddddd;"); 
    }
    
    
    @FXML
    private void mouseExitList(MouseEvent event){
        ((Node)event.getSource()).setStyle("-fx-background-color: #eeeeee;"); 
    }
    
    
    @FXML
    private void mouseEnterButton(MouseEvent event){
         ((Node)event.getSource()).setStyle("-fx-background-color: #452a6a;");    
    }
    
    @FXML
    private void mouseExitButton(MouseEvent event){
         ((Node)event.getSource()).setStyle("-fx-background-color: #19023f;");    
    }
    
    @FXML
    void initialize() {
       
       DatabaseHandler databaseHandler=new DatabaseHandler();
       userMenuPane.setVisible(false);
                   
       addItemShowMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler->{
          showUserMenu(); 
       });
        
        userMenuAddNewProject.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler->{
            slideNewProjectIn();
            
        });
        
        mainScreenNewProjectCancel.setOnAction(event ->{
            slideNewProjectOut();
        });
        
        mainScreenNewProjectCreate.setOnAction(event ->{
           createUserMenu();
          //createUserMenu1();
        });
        
      addNewTaskHBox.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler->{
         slideNewTaskIn();
         newTaskTime.setDisable(true);
        
      });
      
      newTaskDate.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler->{
         // if (newTaskDate.getValue()!=null){                  
           newTaskTime.setDisable(false);
        // } 
      });
      
      newTaskSave.addEventHandler(MouseEvent.MOUSE_PRESSED, eventHandler->{
         LocalDate date=newTaskDate.getValue();
         LocalTime time=newTaskTime.getValue();
         LocalDate actualdate=LocalDate.now();
         LocalTime actualtime=LocalTime.now();
         
         if ((date==null||date.isAfter(actualdate))||(date.isEqual(actualdate)&&(time==null||time.isAfter(actualtime)))){
             addNewTask();   
             }
         slideNewTaskOut();
         newTaskDescription.setText("");
      });
       
       newTaskCancel.addEventHandler(MouseEvent.MOUSE_PRESSED, eventHandler->{
         slideNewTaskOut();
         newTaskDescription.setText("");
      });
      
    }
    
    
    public LocalDateTime convertLongToDateTime(){
      LocalDateTime dateTimeFromLong=null;
      // get the time as long for the current timezone
          long scund =LocalDateTime.now().toEpochSecond(ZoneId.systemDefault().getRules().getOffset(Instant.now())); 
      
      return dateTimeFromLong ;  
    }
    

    public long convertDateTimeToLong(){
     LocalDate date=newTaskDate.getValue();
     LocalTime time=newTaskTime.getValue();
     long longFromDateTime;
     
     if (date!=null){
         if (time!=null){
          
          String dateString=date.toString();
          String timeString=time.toString();
          String dateTimeString=dateString+" "+timeString;
          
          DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
          LocalDateTime dateTime=LocalDateTime.parse(dateTimeString, formatter);
          
          // get the time as long for the UTC timezone, this will be stored
          long timeInSecondsUTC=dateTime.toEpochSecond(ZoneOffset.UTC);
          longFromDateTime= timeInSecondsUTC;
         }
         else{
             String dateString=date.toString();
             String timeString="23:59:59";
             String dateTimeString=dateString+" "+timeString;
             DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
             LocalDateTime dateTime=LocalDateTime.parse(dateTimeString, formatter);
             long timeInSecondsUTC=dateTime.toEpochSecond(ZoneOffset.UTC);
             longFromDateTime= timeInSecondsUTC;
            
         }
     }
     else{
         longFromDateTime=0;
     } 
     return longFromDateTime;
    }
    
    
    
    public byte getSelectedPriority(){
        byte priority=0;
      if (priorityFirst.isSelected()){
          priority=1;
      }
      else{
          if(prioritySecond.isSelected()){
             priority=2;  
          }
          else{
             if (priorityThird.isSelected()){
               priority=3;   
             }              
             else{
                if (priorityFourth.isSelected()){
                  priority=4;      
                }
              }
          }
      }
      return priority;
    }  
    
    
    
    
    public void addNewTask(){
        
     String description =newTaskDescription.getText().trim();
     long dateTime=convertDateTimeToLong();
     Byte priority=getSelectedPriority();
     Byte finished=0;
     System.out.println(listId+" "+userid);
     Task task=new Task(description,dateTime,priority,finished,listId,userid);
     DatabaseHandler databaseHandler=new DatabaseHandler();
     databaseHandler.addTask(task);
     addTaskToPane(description, priority, dateTime, taskContainerVBox);
     taskScroll.vvalueProperty().bind(taskContainerVBox.heightProperty()); 
      
    }
    
    
    public void getChildrenVBOX(VBox node){
        for( Node child: node.getChildren()){
            if (child instanceof HBox){
             String ListName=(((Label)((HBox)child).getChildren().get(0)).getText());
             String ListID=(((Label)((HBox)child).getChildren().get(0)).getId());
                         }
           
        } 
    }
    
    
    
    public void showProject(String listname){
        taskListName.setText(listname);
        taskScreenPane.setLayoutX(0);
        userMenuPane.setVisible(false);
        menuIn=!menuIn;
    }
    
    public void addUserMenuProject(String name, JFXListView node){
            HBox box=new HBox();
            Label lbl=new Label(name);
            try {
                ImageView image=new ImageView(new Image(new FileInputStream("src/assets/todolisticon.png")));
                image.setPreserveRatio(true);
                image.setFitHeight(20);
                image.setFitWidth(20);
                
                ImageView optionImage=new ImageView(new Image(new FileInputStream("src/assets/dots_green.png")));
                optionImage.setPreserveRatio(true);
                optionImage.setFitHeight(20);
                optionImage.setFitWidth(20);
                
                lbl.setGraphic(image);
                lbl.setFont(new javafx.scene.text.Font("System", 18));
                lbl.setPrefSize(190, 35);
                
                
                JFXButton button= new JFXButton();
                button.setGraphic(optionImage);
                
                box.getChildren().add(lbl);
                box.getChildren().add(button);
                node.getItems().add(box);
                node.setFixedCellSize(35);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        }
    
    public Color priorityBoxColor(int priority){
      Color color= Color.web("#5a5a5a");  
        if (priority==1){
             color = Color.web("#c90606");
        }else{
            if (priority==2){
               color= Color.web("#FFA500"); 
            }else{
                if (priority==3){
                  color= Color.web("#ffe500");    
                }else{
                  color= Color.web("#7edd2c");       
                }
            }
            
        }
        return color;
    }
    
    
    
    public void addTaskToPane(String name, int priority, long date, VBox node){
            HBox box=new HBox();
            VBox textContent=new VBox();
            JFXCheckBox priorityBox= new JFXCheckBox();
            priorityBox.setUnCheckedColor(priorityBoxColor(priority));
            priorityBox.setText("");
            priorityBox.setPrefSize(20,20);
            
            
            ScrollFreeTextArea taskDescription=new ScrollFreeTextArea();
            taskDescription.setTextArea(name);
            try {
                ImageView image=new ImageView(new Image(new FileInputStream("src/assets/todolisticon.png")));
                image.setPreserveRatio(true);
                image.setFitHeight(20);
                image.setFitWidth(20);
                
                ImageView optionImage=new ImageView(new Image(new FileInputStream("src/assets/dots_green.png")));
                optionImage.setPreserveRatio(true);
                optionImage.setFitHeight(20);
                optionImage.setFitWidth(20);
                                
                JFXButton button= new JFXButton();
                button.setGraphic(optionImage);
                
                Label dateTime=new Label(Long.toString(date));
                
                textContent.getChildren().add(taskDescription);
                textContent.getChildren().add(dateTime);
                //VBox.getVgrow(taskDescription);
                
                box.getChildren().add(priorityBox);
                box.getChildren().add(textContent);
                box.getChildren().add(button);
                node.getChildren().add(box);
                node.setPadding(new Insets(0,0,5,0));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        }
    
     
    public void userStandardListUpdate( String username){
     //Add the standard menu to the database
      UserMenuAdditions userMenuRefresh= new UserMenuAdditions(username); 
      userMenuRefresh.standardMenuFirstUpdate(); 
    }
    
    
    
    public void userMenuPopulate(){        
     UserMenuAdditions userMenuRefresh= new UserMenuAdditions(username); 
        
    //Populate the CreatedList from the Database;
    ArrayList <String>  createdList=userMenuRefresh.getCreatedList();
    for (int i=0;i<createdList.size();i++){
        this.addUserMenuProject(createdList.get(i), userMenuListViewCreated);        // gets the name of the createdList listitems and adds to the Listview
        
    }
    
    
    }
    
    
    public void setUser(String username, int userId){
        
           this.username=username;
           this.userid=userId;
           userMenuUsername.setText(username);
           useridentification=userid;
           
           
       }
   public String getUser(){
       return username;
   }
       
   private void addStandardListToDataBase(String listname, String listgroup, int userID){
       DatabaseHandler isertListDatabase =new DatabaseHandler();
       Lists list= new Lists(listname, listgroup, userID);
       isertListDatabase.addStandardListDatabase(list); 
       
   };
   
      
   
    public void createUserMenu(){
         String projectName=mainScreenNewProjectName.getText().trim();
            if (!projectName.equals("")){              
                addUserMenuProject(projectName,userMenuListViewCreated);
                slideNewProjectOut();
                UserMenuAdditions userMenuRefresh= new UserMenuAdditions(username);
                String listgroup="Created";
                userMenuRefresh.addListDataBase(projectName,listgroup);    
                mainScreenNewProjectName.setText("");
            }
            else{
             Shaker shake =new Shaker(mainScreenNewProjectName);
             shake.shake();
            }
    }
    
    public void addNodeUserMenu(String projectName){
        Label label= new Label(projectName);
        label.setMinSize(215, 35);
        label.setPrefSize(215, 35);
        label.setStyle("-fx-background-color: #eeeeee;");
        label.setFont(new javafx.scene.text.Font("System", 18));
        File file =new File("src/assets/todolisticon.png");
        ImageView image=new ImageView(new Image(file.toURI().toString()));
        image.setPreserveRatio(true);
        image.setFitHeight(20);
        image.setFitWidth(20);
        label.setGraphic(image);
        label.setId("userMenu"+projectName);
        usermenuVboxProject.getChildren().add(label);
    }
    
    
    public void slideNewProjectOut(){
     //MoveNode moveInMenu = new MoveNode(mainScreenNewProjectPane,0f,150f);
      // moveInMenu.move();
       mainScreenNewProjectPane.setLayoutY(600);
    } 
    
    public void slideNewProjectIn(){
      // MoveNode moveInMenu = new MoveNode(mainScreenNewProjectPane,0f,-150f);
      
      // moveInMenu.move(); 
       mainScreenNewProjectPane.setLayoutY(450);
    }
    
    public void slideNewTaskIn(){
       addNewTaskPane.setLayoutY(385);
       addNewTaskPane.setLayoutX(5);
       addNewTaskPane.setVisible(true);
    }
    
    public void slideNewTaskOut(){
       addNewTaskPane.setVisible(false);
       addNewTaskPane.setLayoutY(600);
       addNewTaskPane.setLayoutX(500);
       
    }
    
    private void showOtherScreen(String fxmlLocation){
        FXMLLoader loader= new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("fxmlLocation"));
                     
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Parent root= loader.getRoot();
            Stage stage= new Stage();
            stage.setScene(new Scene(root));
            stage.show(); 
    }
    
    
    private void showUserMenu(){
        if (!menuIn){
        menuIn=!menuIn;         
         userMenuPane.setVisible(true);
         ((Stage)userMenuPane.getScene().getWindow()).toFront();
        }else{
        menuIn=!menuIn;         
         userMenuPane.setVisible(false);
        }
      } 
    
    
    
}
