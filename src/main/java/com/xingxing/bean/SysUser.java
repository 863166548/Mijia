package com.xingxing.bean;

public class SysUser {
    private Integer id;

    private String username;

    private String psaaword;

    private String realName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPsaaword() {
        return psaaword;
    }

    public void setPsaaword(String psaaword) {
        this.psaaword = psaaword == null ? null : psaaword.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }
}