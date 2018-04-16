package controller;
import Database.DatabaseHandler;
import animation.Shaker;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.activation.DataContentHandler;
import model.User;

/**
 *
 * @author MyPC
 */
public class LoginController{
    private DatabaseHandler databaseHandler;
    private String loginUser;
    @FXML
    private int userid;
    
     
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXPasswordField loginPasswordfield;

    @FXML
    private JFXTextField loginUsernameTextField;

    @FXML
    private JFXButton loginButton;

    @FXML
    private Label loginForgottenPassword;

    @FXML
    private JFXButton loginSignupButton;

    @FXML
    private Label loginLoginProblem;
    
    
                 
    @FXML
    void initialize() {
        
       DatabaseHandler databaseHandler=new DatabaseHandler();
       
       
       
       loginButton.setOnAction(event ->{
          loginUser=loginUsernameTextField.getText().trim();
          String loginPwd=loginPasswordfield.getText().trim();
          
          if (loginUser.equals("")||loginPwd.equals("")){
                    loginLoginProblem.setText("Missing username or password");
                    loginLoginProblem.setVisible(true);
                }
                else{
                    
                    loginLoginProblem.setVisible(false); 
                    User user= new User();
                    user.setUserName(loginUser);
                    user.setPassword(loginPwd);
                    ResultSet userRow=databaseHandler.getUser(user);
                    
                    int counter=0;
                    try {
                        while (userRow.next()){
                            counter++;
                            userid=userRow.getInt("id");
                            
                        }
                        if (counter==1){
                            System.out.println("Login Successfull!");
                            showMainScreen();
                        }else{
                            loginLoginProblem.setText("Username or password does not match");
                            loginLoginProblem.setVisible(true);
                            Shaker shaker=new Shaker(loginButton);
                            shaker.shake();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
          
                }
       });
        
        loginButton.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandler->{
            changeButtonColorMouseIn(loginButton);
        });
        
        loginButton.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandler->{
            changeButtonColorMouseOut(loginButton);
        }); 
        
        loginSignupButton.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandler->{
            changeButtonColorMouseIn(loginSignupButton);
        });
        
        loginSignupButton.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandler->{
            changeButtonColorMouseOut(loginSignupButton);
        }); 
        
        
       loginSignupButton.setOnAction(event ->{
         // take user to SignUp screen
        // this hides the login screen, can use any of the login screen items, not just the signUpButton
            loginSignupButton.getScene().getWindow().hide();   
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/signUp.fxml"));
                     
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Parent root= loader.getRoot();
            Stage stage= new Stage();
            stage.setScene(new Scene(root));
            stage.show(); 
            
       });
          
       loginForgottenPassword.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandler->{
        loginForgottenPassword.setStyle("-fx-underline: true");
       });
       
       loginForgottenPassword.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandler->{
          loginForgottenPassword.setStyle("-fx-underline: false"); 
       });
    }
    
    private void changeButtonColorMouseIn(Node node){
        node.setStyle("-fx-background-color: #452a6a;");  
    }
    
     private void changeButtonColorMouseOut(Node node){
       node.setStyle("-fx-background-color: #19023f;"); 
    }
    
    private void showMainScreen () throws SQLException{
         // take user to AddItem screen
        // this hides the login screen, can use any of the login screen items, not just the signUpButton
            loginSignupButton.getScene().getWindow().hide(); 
            String userName=loginUser;
           
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/mainScreen.fxml"));
            
            
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
               
            Parent root= loader.getRoot();            
            Stage stage= new Stage();
            stage.setScene(new Scene(root));
           
           MainScreenController controller=loader.<MainScreenController>getController();
            //System.out.println(userName+","+userid+"!");
            controller.setUser(userName, userid);
            controller.userMenuPopulate();   
            
                         
            
            
            stage.show();  
    } 
    
}
