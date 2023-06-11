package com.wjm.springmvc.bean;

public class User {
    Integer user_id;
    String user_name;
    String user_pwd;
    String user_avater;

    public User() {
    }

    public User(Integer user_id, String user_name, String user_pwd, String user_avater) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_pwd = user_pwd;
        this.user_avater = user_avater;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_pwd='" + user_pwd + '\'' +
                ", user_avater='" + user_avater + '\'' +
                '}';
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public void setUser_avater(String user_avater) {
        this.user_avater = user_avater;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public String getUser_avater() {
        return user_avater;
    }
}
