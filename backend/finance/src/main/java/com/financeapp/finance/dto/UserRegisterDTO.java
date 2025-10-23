package com.financeapp.finance.dto;

public class UserRegisterDTO {
    
    private String username;
    private String email;
    private String firstName;
    private String lastName;

    public UserRegisterDTO(){

    }

    public UserRegisterDTO(String username, String password, String email, String firstName, String lastName){
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername(){
        return username;
    }

    public String getEmail(){
        return email;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String ToString(){
        return "UserRegisterDTO [username=" + username + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}
