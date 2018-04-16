/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class CreatedList {
    
    private String listname;
    private int user_ID;
    
    public CreatedList(String listname, int userID){
        this.listname=listname;
        this.user_ID=userID;
    }

    public CreatedList(){
        
    }
    
    public String getListname() {
        return listname;
    }

    public void setListname(String listname) {
        this.listname = listname;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }
    
    
}
