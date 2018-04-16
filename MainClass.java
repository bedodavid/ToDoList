/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.lang.reflect.InvocationTargetException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author MyPC
 */
public class MainClass extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
      //  ClassLoader loader = MainClass.class.getClassLoader();
    //    System.out.println(loader.getResource("view/login.fxml"));
      FXMLLoader loader = new FXMLLoader();
    
      
      Parent root = loader.load(getClass().getClassLoader().getResource("view/login.fxml"));
       
   
      Scene scene = new Scene(root);
      stage.setTitle("ToDoList");
      stage.setScene(scene);
      stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        launch(args);
    }
    
}
