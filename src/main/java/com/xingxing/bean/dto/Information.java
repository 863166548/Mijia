package com.xingxing.bean.dto;

import java.util.ArrayList;
import java.util.HashMap;

public class Information {
    private int id;

    private int roleId;

    private ArrayList<Integer> access;


    @Override
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", access=" + access +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public ArrayList<Integer> getaccess() {
        return access;
    }

    public void setaccess(ArrayList<Integer> access) {
        this.access = access;
    }
}
