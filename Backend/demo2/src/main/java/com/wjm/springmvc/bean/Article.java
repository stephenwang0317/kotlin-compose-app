package com.wjm.springmvc.bean;

public class Article {
    public Integer art_id;
    public Integer art_author;
    public String art_author_name;
    public Integer art_like;
    public String art_title;
    public String art_content;
    public String art_summary;
    public String art_time;

    public Integer getArt_like() {
        return art_like;
    }

    public void setArt_like(Integer art_like) {
        this.art_like = art_like;
    }

    public String getArt_summary() {
        return art_summary;
    }

    public void setArt_summary(String art_summary) {
        this.art_summary = art_summary;
    }

    public Article(Integer art_id,
                   Integer art_author,
                   Integer art_like,
                   String art_title,
                   String art_content,
                   String art_summary,
                   String art_time,
                   String art_author_name) {
        this.art_id = art_id;
        this.art_author = art_author;
        this.art_like = art_like;
        this.art_title = art_title;
        this.art_content = art_content;
        this.art_summary = art_summary;
        this.art_time = art_time;
        this.art_author_name = art_author_name;
    }

    public Article() {
        this.art_like = 0;
    }

    public Integer getArt_id() {
        return art_id;
    }

    public void setArt_id(Integer art_id) {
        this.art_id = art_id;
    }

    public Integer getArt_author() {
        return art_author;
    }

    public void setArt_author(Integer art_author) {
        this.art_author = art_author;
    }

    public String getArt_title() {
        return art_title;
    }

    public void setArt_title(String art_title) {
        this.art_title = art_title;
    }

    public String getArt_content() {
        return art_content;
    }

    public void setArt_content(String art_content) {
        this.art_content = art_content;
    }

    public String getArt_time() {
        return art_time;
    }

    public void setArt_time(String art_time) {
        this.art_time = art_time;
    }

    public String getArt_author_name() {
        return art_author_name;
    }

    public void setArt_author_name(String art_author_name) {
        this.art_author_name = art_author_name;
    }
}
