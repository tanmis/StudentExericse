package com.CHMI.QQclients.service;

import com.CHMI.Common.Message;
import com.CHMI.Common.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @author 茶米酱
 * @version 1.0
 * 客户端线程
 */
public class ClientConnectServeThread extends Thread {
    private Socket socket =null;
    public ClientConnectServeThread(Socket socket){
        this.socket = socket;
        //拿到网络连接

    }
    @Override
    public void run() {
        while (true){
            try {
                System.out.println("客户端和服务器端保持通讯");
                ObjectInputStream oit = new ObjectInputStream(socket.getInputStream());
                Message om = (Message) oit.readObject();
                //拿到服务端返回进行对应处理
                //如果返回是拉取客户数据
                if (om.getContenttype().equals(MessageType.MESSAGE_GET_ONLINE_FRIEND)){
                    //分割取出
                    System.out.println("==========用户列表===========");
                    String[] s = om.getContent().split(" ");
                    for (int i = 0; i < s.length; i++) {
                        System.out.println((i+1) + s[i]);
                    }

                }
                else {
                    System.out.println("其他不处理目前");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
            }
        }

    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
