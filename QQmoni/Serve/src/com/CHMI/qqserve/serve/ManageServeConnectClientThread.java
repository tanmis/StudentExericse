package com.CHMI.qqserve.serve;

import java.util.HashMap;
import java.util.Set;

/**
 * @author 茶米酱
 * @version 1.0
 * 即指管理线程的集合
 */
public class ManageServeConnectClientThread {
    private static HashMap<String,ServeConnectClientThread > hashMap = new HashMap<>();
   //返回一个用户登录列表
    public static String getuserid(){
        String alluseriding = "";
        Set<String> keySet = hashMap.keySet();
        for (Object key:keySet){
            alluseriding += key + " ";
        }
        return alluseriding;
    }
    //增加线程的方法
    public static void addManageServeConnectClientThread(String user,ServeConnectClientThread serveConnectClientThread){
        hashMap.put(user,serveConnectClientThread);

    }
//    得到对应线程方法
    public static ServeConnectClientThread getserveConnectClientThread(String user) {
       return hashMap.get(user);
    }
}
