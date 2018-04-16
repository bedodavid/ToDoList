
package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CreatedList;
import model.Lists;
import model.Task;
import model.User;

public class DatabaseHandler extends Configs {
    Connection dbConnection;
    
    public Connection getDbConnection () throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://"+ dbHost+ ":"
                +dbPort+"/"
                +dbName;
        
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection= DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    } 
    
    public void signUpUser(User user) {
        
        String insert = "INSERT INTO " +Const.USERS_TABLE+"("+Const.USERS_FIRSTNAME+","+Const.USERS_LASTNAME+","+
                        Const.USERS_USERNAME+","+Const.USERS_PASSWORD+","+Const.USERS_EMAILADDRESS+","+
                        Const.USERS_GENDER+","+Const.USERS_LOCATION+","+Const.USERS_BIRTHDATE+","+Const.USERS_REGISTERED+")"+"VALUES(?,?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement preparedStatement= getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmailAddress());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.setString(7, user.getLocation());
            preparedStatement.setDate(8, convert(user.getBirthdate()));
            preparedStatement.setDate(9, convert(user.getRegistered()));
            preparedStatement.executeUpdate();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private Date convert(LocalDate localDate) {
           return localDate == null ? null : Date.valueOf(localDate);
    }
    
    
    public ResultSet getUser(User user) {
        ResultSet resultSet=null;
        
        if (!user.getUserName().equals("")||!user.getPassword().equals("")){
          String query ="SELECT * FROM " + Const.USERS_TABLE+ " WHERE "+ Const.USERS_USERNAME+ "=?"+
                  " AND "+Const.USERS_PASSWORD+"=?";
          
          //select all from USERTABLE where USERNAME=..... and Password=....
          
        
              try { 
                  PreparedStatement preparedStatement =getDbConnection().prepareStatement(query);
                  preparedStatement.setString(1, user.getUserName());
                  preparedStatement.setString(2, user.getPassword());
                  
                  resultSet = preparedStatement.executeQuery();
                  
              } catch (ClassNotFoundException ex) {
                  Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
              } catch (SQLException ex) {
                  Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
              }  
              
        }else{
            System.out.println("please enter you credentials");
        }
        return resultSet;
    }
    
    public ResultSet getUserUsername(User user) {
        ResultSet resultSet=null;
        
        if (!user.getUserName().equals("")){
          String query ="SELECT * FROM " + Const.USERS_TABLE+ " WHERE "+ Const.USERS_USERNAME+ "=?";
          
          //select all from USERTABLE where USERNAME=..... and Password=....
          
        
              try { 
                  PreparedStatement preparedStatement =getDbConnection().prepareStatement(query);
                  preparedStatement.setString(1, user.getUserName());                 
                  
                  resultSet = preparedStatement.executeQuery();
                  
              } catch (ClassNotFoundException ex) {
                  Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
              } catch (SQLException ex) {
                  Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
              }  
              
        }else{
            System.out.println("please enter you credentials");
        }
        return resultSet;
    }
    
    public ResultSet getUserEmailAddress(User user) {
        ResultSet resultSet=null;
        
        if (!user.getEmailAddress().equals("")){
          String query ="SELECT * FROM " + Const.USERS_TABLE+ " WHERE "+ Const.USERS_EMAILADDRESS+ "=?";
          
          //select all from USERTABLE where Email adress=...
          
        
              try { 
                  PreparedStatement preparedStatement =getDbConnection().prepareStatement(query);
                  preparedStatement.setString(1, user.getEmailAddress());                 
                  
                  resultSet = preparedStatement.executeQuery();
                  
              } catch (ClassNotFoundException ex) {
                  Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
              } catch (SQLException ex) {
                  Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
              }  
              
        }else{
            System.out.println("please enter you credentials");
        }
        return resultSet;
    }
    
    
    public void addStandardListDatabase(Lists list) {
        
        String insert = "INSERT INTO " +Const.LIST_TABLE+"("+Const.LIST_LISTNAME+","+Const.LIST_LISTGROUPNAME+","+Const.LIST_USERS_ID+")"+"VALUES(?,?,?)";
        
        try {
            PreparedStatement preparedStatement= getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, list.getListname());
            preparedStatement.setString(2, list.getListgroup()); 
            preparedStatement.setInt(3, list.getUser_ID());
            preparedStatement.executeUpdate();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int getListIDFromDatabase(Lists list){
        int listId=0;
        ResultSet resultSet=null;
        if (list.getUser_ID()!=0&&!list.getListgroup().equals("")&&!list.getListname().equals("")){
          String query ="SELECT * FROM " + Const.LIST_TABLE+ " WHERE "+Const.LIST_LISTNAME+"=?"+ " AND "+Const.LIST_LISTGROUPNAME+"=?"+
                  " AND "+Const.LIST_USERS_ID+"=?";
         try { 
                 PreparedStatement preparedStatement =getDbConnection().prepareStatement(query);
                  preparedStatement.setString(1, list.getListname());
                  preparedStatement.setString(2, list.getListgroup());
                  preparedStatement.setInt(3, list.getUser_ID());
                  
                  resultSet = preparedStatement.executeQuery();
                  
              } catch (ClassNotFoundException ex) {
                  Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
              } catch (SQLException ex) {
                  Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
              }  
              
        }else{
            System.out.println("No List have found");
        } 
        
        try {
            while (resultSet.next()){
                listId=resultSet.getInt("id");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listId;
    }
    
    
    public ResultSet getListFromDatabase(Lists list){
     ResultSet resultSet=null;
        
        if (list.getUser_ID()!=0&&!list.getListgroup().equals("")){
          String query ="SELECT * FROM " + Const.LIST_TABLE+ " WHERE "+Const.LIST_LISTGROUPNAME+"=?"+
                  " AND "+Const.LIST_USERS_ID+"=?";
          
          //select all from Lists whereListgroupname=..... and userid=....
          
        
              try { 
                  PreparedStatement preparedStatement =getDbConnection().prepareStatement(query);
                  preparedStatement.setString(1, list.getListgroup());
                  preparedStatement.setInt(2, list.getUser_ID());
                  
                  resultSet = preparedStatement.executeQuery();
                  
              } catch (ClassNotFoundException ex) {
                  Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
              } catch (SQLException ex) {
                  Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
              }  
              
        }else{
            System.out.println("Itemns not found");
        }
        return resultSet;   
    }
    
    public void addCreatedListToDatabase(Lists list) {
        
        String insert = "INSERT INTO " +Const.LIST_TABLE+"("+Const.LIST_LISTNAME+","+Const.LIST_LISTGROUPNAME+","+Const.LIST_USERS_ID+")"+"VALUES(?,?,?)";
        
        try {
            PreparedStatement preparedStatement= getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, list.getListname());
            preparedStatement.setString(2, list.getListgroup());
            preparedStatement.setInt(3, list.getUser_ID());
       preparedStatement.executeUpdate();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public ResultSet mainListSeach(Lists list) {
        ResultSet resultSet=null;
        
        if (!list.getListname().equals("")||list.getUser_ID()!=0){
          String query ="SELECT * FROM " + Const.LIST_TABLE+ " WHERE "+ Const.LIST_LISTNAME+ "=?"+
                  " AND "+Const.LIST_USERS_ID+"=?";
          
          //select all from Lists whereListname=..... and userid=....
          
              try { 
                  PreparedStatement preparedStatement =getDbConnection().prepareStatement(query);
                  preparedStatement.setString(1, list.getListname());
                  preparedStatement.setInt(2, list.getUser_ID());
                  
                  resultSet = preparedStatement.executeQuery();
                  
              } catch (ClassNotFoundException ex) {
                  Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
              } catch (SQLException ex) {
                  Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
              }  
              
        }else{
            System.out.println("Itemns not found");
        }
        return resultSet;
    }
  
    
    public void addTask(Task task){
        
         String insert = "INSERT INTO " +Const.TASK_TABLE+"("+Const.TASK_TASK+","+Const.TASK_DATETIME+","+
                        Const.TASK_PRIORITY+","+Const.TASK_FINISHED+","+Const.TASK_LIST_ID+","+
                        Const.TASK_LIST_USERS_ID+")"+"VALUES(?,?,?,?,?,?)";
        
        try {
            PreparedStatement preparedStatement= getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, task.getTask());
            preparedStatement.setLong(2, task.getDatetime());
            preparedStatement.setByte(3, task.getPriority());
            preparedStatement.setByte(4, task.getFinished());
            preparedStatement.setInt(5, task.getList_ID());
            preparedStatement.setInt(6, task.getList_users_ID());
            preparedStatement.executeUpdate();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

