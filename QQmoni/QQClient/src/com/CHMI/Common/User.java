package com.CHMI.Common;

import java.io.Serializable;

/**
 * @author 茶米酱
 * @version 1.0
 *用户信息存储
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;//提高兼容性
    private String userid;//用户id
    private String password;//密码

    public User(String userid, String password) {
        this.userid = userid;
        this.password = password;

    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
