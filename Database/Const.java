
package Database;


public class Const {
    public static final String USERS_TABLE="users";
    public static final String LIST_TABLE="list";    
    public static final String TASK_TABLE="task";
    public static final String CREATEDLIST_TABLE="createdlist";  
    
    
    //USERS Table Columns Names
    public static final String USERS_ID="id";
    public static final String USERS_FIRSTNAME="firstname";
    public static final String USERS_LASTNAME="lastname";
    public static final String USERS_USERNAME="username";
    public static final String USERS_PASSWORD="password";
    public static final String USERS_EMAILADDRESS="email";
    public static final String USERS_GENDER="gender";
    public static final String USERS_LOCATION="location";
    public static final String USERS_BIRTHDATE="birthdate";
    public static final String USERS_REGISTERED="registeredtime";
    
    
    //LIST Table Columns Names
            
    public static final String LIST_ID="id";
    public static final String LIST_LISTNAME="listname";
    public static final String LIST_LISTGROUPNAME="listgroup";
    public static final String LIST_USERS_ID="users_id";
    
    //CREATEDLIST Table Columns Names
            
    public static final String CREATEDLIST_ID="id";
    public static final String CREATEDLIST_LISTNAME="listname";
    public static final String CREATEDLIST_USERS_ID="users_id";
    
    
    //TASK Table Column Names
    public static final String TASK_ID="id";
    public static final String TASK_TASK="task";
    public static final String TASK_DATETIME="date";
    public static final String TASK_PRIORITY="priority";
    public static final String TASK_FINISHED="finished";
    public static final String TASK_LIST_ID="list_id";
    public static final String TASK_LIST_USERS_ID="list_users_id";
    public static final String TASK_CREATEDLIST_ID="createdlist_id";
    public static final String TASK_CREATEDLIST_USERS_ID="createdlist_users_id";
    
}
