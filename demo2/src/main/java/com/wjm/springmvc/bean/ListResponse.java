package com.wjm.springmvc.bean;

import java.util.List;

public class ListResponse<T> extends BaseResponse{
    Integer len = 0;
    List<T> list;

    String type = "";

    public Integer getLen() {
        return len;
    }

    public void setLen(Integer len) {
        this.len = len;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ListResponse() {
    }

    public ListResponse(Integer len, List<T> list, String type) {
        this.len = len;
        this.list = list;
        this.type = type;
    }
}
