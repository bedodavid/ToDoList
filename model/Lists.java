/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class Lists {
    private String listname;
    private String listgroup;
    private int user_ID;
    
    
    public Lists(String listname, String listgroup,int userID){
        this.listname=listname;
        this.listgroup=listgroup;
        this.user_ID=userID;
    }

    public Lists(){
        
    }

   
    public String getListname() {
        return listname;
    }

    public void setListname(String listname) {
        this.listname = listname;
    }

    public String getListgroup() {
        return listgroup;
    }

    public void setListgroup(String listgroup) {
        this.listgroup = listgroup;
    }
    
   
    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }
    
    
}
