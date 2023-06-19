package com.wjm.springmvc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Article {
    public Integer art_id;
    public Integer art_author;
    public String art_author_name;
    public Integer art_like;
    public String art_title;
    public String art_content;
    public String art_summary;
    public String art_time;


}
