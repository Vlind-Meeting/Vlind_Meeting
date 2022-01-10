package com.example.vlind_meeting;

public class ProfileClass {
    private static ProfileClass instance;
    private String user_name;
    private String user_number;
    private String user_password;
    private String user_filename;
    private String user_nickname;
    public static ProfileClass getInstance(){
        if(instance == null)
            instance = new ProfileClass();
        return instance;
    }
    public void setUser_name(String user_name){
        this.user_name = user_name;
    }
    public void setUser_number(String user_number){
        this.user_number = user_number;
    }
    public void setUser_password(String user_password){
        this.user_password = user_password;
    }
    public void setUser_filename(String user_filename){
        this.user_filename = user_filename;
    }
    public void setUser_nickname(String user_nickname){
        this.user_nickname = user_nickname;
    }

    public String getUser_name() {
        return this.user_name;
    }
    public String getUser_number() {
        return this.user_number;
    }
    public String getUser_password() {
        return this.user_password;
    }
    public String getUser_filename() {
        return this.user_filename;
    }
    public String getUser_nickname() {
        return this.user_nickname;
    }

}
