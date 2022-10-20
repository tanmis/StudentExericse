package com.CHMI.QQclients.service;

import com.CHMI.Common.Message;
import com.CHMI.Common.MessageType;
import com.CHMI.Common.User;
import com.sun.java_cup.internal.runtime.Scanner;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author 茶米酱
 * @version 1.0
 * 客户端把客户类信息进行处理
 */
public class UserClientServe {
    private  User u = null;
    private Socket socket =null;
    public boolean ChekUser(String uid,String pwd)  {
        try {
            u = new User(uid,pwd);
            socket = new Socket(InetAddress.getByName("192.168.199.1"), 9999);
            //将u序列化发给服务端
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u);
            //拿到客户端返回的比对
            ObjectInputStream oip = new ObjectInputStream(socket.getInputStream());
            Message message= (Message) oip.readObject();
            if (message.getContenttype().equals(MessageType.Login_success_prompt)){
                return true;
            }
            else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return false;

    }
}
