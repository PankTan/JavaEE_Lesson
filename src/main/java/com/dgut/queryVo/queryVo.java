package com.dgut.queryVo;

import com.dgut.entity.User;

import java.util.List;

public class queryVo {
    private String user_name;
    private List<Integer> ids;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
