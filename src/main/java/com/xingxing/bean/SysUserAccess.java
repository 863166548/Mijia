package com.xingxing.bean;

public class SysUserAccess {
    private Integer id;

    private Integer userId;

    private Integer accessId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAccessId() {
        return accessId;
    }

    public void setAccessId(Integer accessId) {
        this.accessId = accessId;
    }

    @Override
    public String toString() {
        return "SysUserAccess{" +
                "id=" + id +
                ", userId=" + userId +
                ", accessId=" + accessId +
                '}';
    }
}