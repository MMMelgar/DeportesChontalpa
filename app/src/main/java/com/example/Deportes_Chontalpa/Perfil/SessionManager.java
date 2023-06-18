package com.example.Deportes_Chontalpa.Perfil;

import java.util.Objects;

public class SessionManager {

    private static SessionManager instance;
    private String userEmail, userId;
    protected Boolean LogIn;

    private SessionManager() {
        LogIn=false;
    }

    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Boolean getAdmi() {
        if(Objects.equals(userEmail, "monicamelgar061@gmail.com")){
            return true;
        }else{
            return false;
        }
    }

    public Boolean getLogIn() {
        return LogIn;
    }

    public void Logout(){
        LogIn=false;
        userEmail=null;
    }

    public void setUserId(String userId){
        this.userId=userId;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        LogIn=true;
    }

}
