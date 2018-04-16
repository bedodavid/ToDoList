package model;

import java.sql.Timestamp;

public class Task {
    private String task;
    private long datetime;
    private byte priority;
    private byte finished;
    private int list_ID;
    private int list_users_ID;
    

    public Task(String task, long datetime, byte priority, byte finished, int list_ID, int list_users_ID) {
        this.task = task;
        this.datetime = datetime;
        this.priority = priority;
        this.finished = finished;
        this.list_ID = list_ID;
        this.list_users_ID = list_users_ID;
    }
    
   public Task(){
       
   }
    
    

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public byte getFinished() {
        return finished;
    }

    public void setFinished(byte finished) {
        this.finished = finished;
    }

    public int getList_ID() {
        return list_ID;
    }

    public void setList_ID(int list_ID) {
        this.list_ID = list_ID;
    }

    public int getList_users_ID() {
        return list_users_ID;
    }

    public void setList_users_ID(int list_users_ID) {
        this.list_users_ID = list_users_ID;
    }

    
      
}
