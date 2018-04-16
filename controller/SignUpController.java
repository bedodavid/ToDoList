/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Database.DatabaseHandler;
import animation.Shaker;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;
import model.UserMenuAdditions;

/**
 * FXML Controller class
 *
 * @author MyPC
 */
public class SignUpController{
    
    private User user= new User();
    private boolean completedAllData; 
    FXMLLoader loader;
    
        

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label signupLastnameMemo;

    @FXML
    private JFXTextField signUpFirstName;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXPasswordField signUpPassword;

    @FXML
    private JFXRadioButton signUpRadioButtonFemale;

    @FXML
    private Label signupLocationMemo;

    @FXML
    private Label signupUsernameMemo;

    @FXML
    private JFXTextField signUpLastName;

    @FXML
    private Label signupFirstnameMemo;

    @FXML
    private Label signupGenderMemo;

    @FXML
    private JFXTextField signUpLocation;

    @FXML
    private JFXButton signUpButton;

    @FXML
    private JFXTextField signUpUsername;

    @FXML
    private Label signupPasswordMemo;

    @FXML
    private JFXRadioButton signUpRadioButtonMale;
   
    @FXML
    private JFXTextField signUpEmail;

    @FXML
    private Label signupEmailMemo;

    @FXML
    private Label signUpSignIn;

    
    
    
    @FXML
    void initialize() {
          
     signUpButton.setOnAction(event ->{
        createUser();
        
      
    });
     
     signUpSignIn.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler->{
           signUpSignIn.getScene().getWindow().hide();  
           showOtherScreen("view/login.fxml");
        });
        
        signUpSignIn.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandler->{
           signUpSignIn.setStyle("-fx-underline: true");
        });
        
        signUpSignIn.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandler->{
           signUpSignIn.setStyle("-fx-underline: false");
        });
     
    }
    
    private void showOtherScreen(String fxmlLocation){
        loader= new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource(fxmlLocation));
                     
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
    
    private void checkFieldEmpty(String fieldContent, Node node){
        if (!fieldContent.equals("")){ 
                    node.setVisible(false);
                }
                else{
                    completedAllData=false;
                    node.setVisible(true);  
                }
    }
    
    private boolean validateEmailAddress(String email){
        boolean validEmail;
        if ((Pattern.matches("^[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$", email))||
                        (Pattern.matches("^[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$", email))){
            validEmail=true;
            
        }else{
             validEmail=false;   
                }
        return validEmail ;
           
        }
    
    
    
    private void createUser(){
        DatabaseHandler databaseHandler= new DatabaseHandler();
        String email=signUpEmail.getText().trim();
        String firstName=signUpFirstName.getText().trim();
        String lastName =signUpLastName.getText().trim();
        String userName= signUpUsername.getText().trim();
        String password=signUpPassword.getText().trim();
        String location= signUpLocation.getText().trim();
        String gender="";
        boolean availableUsername=false;
        boolean availableEmail=false;
        
        if (signUpRadioButtonMale.isSelected()&&!signUpRadioButtonFemale.isSelected()){
                        gender="Male";
                    }
        if (!signUpRadioButtonMale.isSelected()&&signUpRadioButtonFemale.isSelected()){
                        gender="Female";
                    }           
       completedAllData=!(email.equals("")||firstName.equals("")||lastName.equals("")||userName.equals("")||
                   password.equals("")|| location.equals("")||gender.equals("")); 
           
         /*  RequiredFieldValidator validator=new RequiredFieldValidator();
           signUpFirstName.getValidators().add(validator);
           validator.setMessage("Please enter your first name!");
           
           
           signUpFirstName.focusedProperty().addListener(new ChangeListener<Boolean>(){
              @Override
              public void changed(ObservableValue<? extends Boolean> observable,Boolean oldValue, Boolean newValue){
                  if (!newValue){
                      signUpFirstName.validate();
                  }
              }
           });*/
           
           
                
        if (!completedAllData){
            
            // check if email adress is completed, if not activate the label to complete    
            if (!email.equals("")){
                    signupEmailMemo.setVisible(false);  
                }
                else{
                    completedAllData=false;
                    signupEmailMemo.setText("Please enter your email address");
                    signupEmailMemo.setVisible(true);
                }
            
            // check if firstname is completed, if not activate the label to complete
             checkFieldEmpty(firstName,signupFirstnameMemo);
               
            // check if the lastName is completed,  if not activate the label to complete
             checkFieldEmpty(lastName, signupLastnameMemo); 
            
            // check if the userName is completed, if not activate the label to complete
                if (!userName.equals("")){
                    signupUsernameMemo.setVisible(false);
                }
                else{
                    completedAllData=false;
                    signupUsernameMemo.setText("Please enter your username");
                    signupUsernameMemo.setVisible(true);
                }
         
            // check if the password is completed, if not activate the label to complete
             checkFieldEmpty(password, signupPasswordMemo); 
                           
             // check if the location is completed, if not activate the label to complete
             checkFieldEmpty(location, signupLocationMemo); 
             
            if(!signUpRadioButtonMale.isSelected()&&!signUpRadioButtonFemale.isSelected()){
                    signupGenderMemo.setVisible(true); 
                
            }else{
                if(signUpRadioButtonMale.isSelected()||signUpRadioButtonFemale.isSelected()){
                    signupGenderMemo.setVisible(false); 
                    }
                }
            }else{
                signupEmailMemo.setVisible(false);
                signupFirstnameMemo.setVisible(false);
                signupLastnameMemo.setVisible(false);
                signupUsernameMemo.setVisible(false);
                signupPasswordMemo.setVisible(false);
                signupLocationMemo.setVisible(false);
                signupGenderMemo.setVisible(false);
              
                
                User checkUsername= new User();
                checkUsername.setUserName(userName);
                ResultSet userNameCheck=databaseHandler.getUserUsername(checkUsername);
                    int counter=0;
                    try {
                        while (userNameCheck.next()){
                            counter++;  
                        }
                        if (counter==0){
                            availableUsername=true;
                        }else{
                            signupUsernameMemo.setText("Username taken, please choose another one");
                            signupUsernameMemo.setVisible(true);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
              
                    
                if (validateEmailAddress(email)){
                    User checkEmail= new User();
                    checkEmail.setEmailAddress(email);
                    ResultSet emailCheck=databaseHandler.getUserEmailAddress(checkEmail);
                    int countEmail=0;
                    try {
                        while (emailCheck.next()){
                            countEmail++;  
                        }
                        if (countEmail==0){
                            availableEmail=true;
                        }else{
                            signupEmailMemo.setText("Email already used, please enter another one");
                            signupEmailMemo.setVisible(true);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                 }else{
                    signupEmailMemo.setText("Invalid email address, please enter another one");
                    signupEmailMemo.setVisible(true);
                }
                    
                if (availableEmail&&availableUsername){
                    LocalDate birthdate=LocalDate.now();
                    LocalDate registered=LocalDate.now();
                   // java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
                    user= new User(firstName, lastName,userName,password,email, gender, location,birthdate,registered);
                    databaseHandler.signUpUser(user); 
                    UserMenuAdditions userMenuRefresh= new UserMenuAdditions(userName); 
                    userMenuRefresh.standardMenuFirstUpdate(); 
                    signUpButton.getScene().getWindow().hide();
                     showOtherScreen("view/login.fxml");
                    }
                 
              
              
            }
        }
    }



