package com.CHMI.qqserve.serve;

import com.CHMI.Common.Message;
import com.CHMI.Common.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.util.Set;

/**
 * @author 茶米酱
 * @version 1.0
 * 服务端群发消息类
 */
public class ServerInformationPush  implements Runnable{
   private boolean loop =true;

    @Override
    public void run() {
        while (loop){
            //是否要发送消息
            System.out.println("是否发送服务端推送 是|不是");
            String s1 = Utility.readString(2);
            //判断服务端是否要进行推送，不推送返回
            if (!(s1.equals("是"))){
               continue;
            }
            System.out.println("请输入推送(需要退出输入退出)");
            String s2 = Utility.readString(100);
            if (s2.equals("退出")){
                break;
            }

            Set<String> keySet = ManageServeConnectClientThread.getHashMap().keySet();
            for (String key:keySet){
                Message message = new Message();
                message.setContent(s2);
                message.setSender("系统");
                message.setReceiver(key);
                message.setTimes(new java.util.Date().toString());
                message.setContenttype(MessageType.MESSAGE_TO_ALL_MES);
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(ManageServeConnectClientThread.getserveConnectClientThread(key).getSocket().getOutputStream());
                    oos.writeObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
