
package model;

import java.time.LocalDate;



public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String emailAddress;
    private String gender;
    private String location;
    private LocalDate birthdate;
    private LocalDate registered;
   

    public User(String firstName, String lastName, String userName, String password,String emailAddress,String gender, String location,LocalDate birthdate,LocalDate registered) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.emailAddress=emailAddress;
        this.gender = gender;
        this.location = location;
        this.birthdate=birthdate;
        this.registered=registered;
        
    }
   public User(){
       
   }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public LocalDate getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDate registered) {
        this.registered = registered;
    }
   
   
}
    
    