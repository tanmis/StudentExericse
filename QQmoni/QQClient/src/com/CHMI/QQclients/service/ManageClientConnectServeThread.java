package com.CHMI.QQclients.service;

import java.util.HashMap;

/**
 * @author 茶米酱
 * @version 1.0
 * 管理集合
 */
public class ManageClientConnectServeThread {
    private static   HashMap<String, ClientConnectServeThread> manageuserthread = new HashMap<>();
//把线程加入集合
    public static void addManageuserthread(String userid,ClientConnectServeThread ccst) {
        manageuserthread.put(userid,ccst);
    }
//取出对应线程
    public static ClientConnectServeThread getClientConnectServeThread(String userid) {
     return manageuserthread.get(userid);
    }
}
