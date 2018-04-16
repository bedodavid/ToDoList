package model;

import Database.Const;
import Database.DatabaseHandler;
import com.jfoenix.controls.JFXListView;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;


public class UserMenuAdditions {
    private String username;
    private int userid;
          
    
    public UserMenuAdditions(String username){
        this.username=username;
    }    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
     
     
   public UserMenuAdditions(){
       
   } 
   
    private void getActualUserId() throws SQLException{
      // System.out.println(username);
     
        User user= new User();
        user.setUserName(username);
        DatabaseHandler databaseHandler=new DatabaseHandler();
        ResultSet userRow=databaseHandler.getUserUsername(user);
         
        userid=0;           
        while (userRow.next()){
            userid=userRow.getInt("id");
                 
        }
    }
    
    
    public void standardMenuFirstUpdate(){
       
        DatabaseHandler databaseHandler=new DatabaseHandler();
        String listgroupName= "Standard";
        try {
            this.getActualUserId();
        } catch (SQLException ex) {
            Logger.getLogger(UserMenuAdditions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String[] standardmenu={"Work","Health","Personal","Shopping list","Meetings", "Movies to watch","Music","Family","Books","Theater"};
        for (int i=0;i<standardmenu.length; i++){
            Lists listItems =new Lists(standardmenu[i],listgroupName,userid);
            databaseHandler.addStandardListDatabase(listItems);   
        } 
    }  
    
    public  ArrayList<String> getCreatedList(){
     DatabaseHandler databaseHandler=new DatabaseHandler();
     ArrayList<String>listnamesDB=new ArrayList<String>();
         
        try {
            this.getActualUserId();
        } catch (SQLException ex) {
            Logger.getLogger(UserMenuAdditions.class.getName()).log(Level.SEVERE, null, ex);
        }
        Lists list= new Lists();
        list.setUser_ID(userid);
        list.setListgroup("Created");
        ResultSet listItems=databaseHandler.getListFromDatabase(list);
        
        int i=0;
        try {
            while (listItems.next()){
                String listname=listItems.getString(Const.CREATEDLIST_LISTNAME);
                //System.out.print(listname);
                listnamesDB.add(listname);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserMenuAdditions.class.getName()).log(Level.SEVERE, null, ex);
        }
     return listnamesDB;   
    }
    
     
    public void addListDataBase(String listname, String listgroup){
       DatabaseHandler databaseHandler=new DatabaseHandler();
        try {
            this.getActualUserId();
        } catch (SQLException ex) {
            Logger.getLogger(UserMenuAdditions.class.getName()).log(Level.SEVERE, null, ex);
        }
       // System.out.println(userid); 
       Lists listItems =new Lists(listname,listgroup,userid);
       databaseHandler.addCreatedListToDatabase(listItems);
    }
    
    
    
    /*private boolean checkIfStandardListUpdated(Lists list){
        boolean firstUpdated=false;
        DatabaseHandler databaseHandler=new DatabaseHandler();
        ResultSet userRow=databaseHandler.mainListSeach(list);
        
        int counter=0;
        try {
            while (userRow.next()){
                counter++;
            }
            if (counter==0){
              firstUpdated=false;  
            }else{
              firstUpdated=true;    
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserMenuAdditions.class.getName()).log(Level.SEVERE, null, ex);
        }
     return firstUpdated;
     }
    */
    
    
}
    


