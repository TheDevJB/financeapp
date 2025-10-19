package com.financeapp.finance.dto;

public class FindUsernameDTO {
    
    private String username;
    private String email;
    private String phone;

    public FindUsernameDTO(){
    }

    public FindUsernameDTO(String username, String email, String phone){
        super();
        this.email = email;
        this.username = username;
        this.phone = phone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    @Override
    public String toString(){
        return "FindUsernameDTO [email=" + email + ", phone=" + phone + ", username=" + username + "]";
    }
}
