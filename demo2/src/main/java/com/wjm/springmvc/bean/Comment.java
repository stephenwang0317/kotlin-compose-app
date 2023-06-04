package com.wjm.springmvc.bean;

public class Comment {

    public Integer cmt_id;
    public Integer cmt_art_id;
    public Integer cmt_author;
    public String cmt_content;

    public String cmt_time;

    public Comment() {
    }

    public Integer getCmt_id() {
        return cmt_id;
    }

    public void setCmt_id(Integer cmt_id) {
        this.cmt_id = cmt_id;
    }

    public Integer getCmt_art_id() {
        return cmt_art_id;
    }

    public void setCmt_art_id(Integer cmt_art_id) {
        this.cmt_art_id = cmt_art_id;
    }

    public Integer getCmt_author() {
        return cmt_author;
    }

    public void setCmt_author(Integer cmt_author) {
        this.cmt_author = cmt_author;
    }

    public String getCmt_content() {
        return cmt_content;
    }

    public void setCmt_content(String cmt_content) {
        this.cmt_content = cmt_content;
    }

    public String getCmt_time() {
        return cmt_time;
    }

    public void setCmt_time(String cmt_time) {
        this.cmt_time = cmt_time;
    }

    public Comment(Integer cmt_id, Integer cmt_art_id, Integer cmt_author, String cmt_content, String cmt_time) {
        this.cmt_id = cmt_id;
        this.cmt_art_id = cmt_art_id;
        this.cmt_author = cmt_author;
        this.cmt_content = cmt_content;
        this.cmt_time = cmt_time;
    }
}
