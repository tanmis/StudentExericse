package com.CHMI.qqserve.serve;

import com.CHMI.Common.Message;
import com.CHMI.Common.MessageType;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author 茶米酱
 * @version 1.0
 */
public class ServeConnectClientThread extends Thread{
    private  Socket socket ;
    private String userid;

    public  ServeConnectClientThread(String userid,Socket socket) {
        this.socket = socket;
        this.userid = userid;
    }
    @Override
    public void run() {
        while (true){
            try {
                System.out.println("服务器端和客户端" + userid +"保持通讯");
                ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
                //只负责接收信息
                Message oib =(Message) oin.readObject();
                //后面会用message进行通讯
                //如果判断用户是否要拉取在线用户名单
                if (oib.getContenttype().equals(MessageType.MESSAGE_GET_ONLINE_FRIEND)){
                    System.out.println(oib.getSender() + "请求在线用户列表");
                    //将名单返回给客户端
                    Message oib2 = new Message();
                    oib2.setContent(ManageServeConnectClientThread.getuserid());
                    oib2.setContenttype(MessageType.MESSAGE_RET_ONLINE_FRIEND);
                    oib2.setReceiver(oib.getSender());
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(oib2);
                }else {
                    System.out.println("暂不处理");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }  finally {
            }
        }
    }
}
