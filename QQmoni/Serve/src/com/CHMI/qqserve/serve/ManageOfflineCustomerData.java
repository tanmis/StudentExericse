package com.CHMI.qqserve.serve;

import com.CHMI.Common.Message;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 茶米酱
 * @version 1.0
 */
public class ManageOfflineCustomerData {
  private static   ConcurrentHashMap<String , Message>managemessages = new ConcurrentHashMap<>();
//取出托管文件
    public static Message getmessage(String uerid) {
       return managemessages.get(uerid);
    }
//将需管理文件发送至文件
    public static void putManagemessages(String userid,Message message) {
        managemessages.put(userid,message);
    }
    //删除托管文件
    public static void deleteManagemessages(String userid){
        managemessages.remove(userid);
    }

}

