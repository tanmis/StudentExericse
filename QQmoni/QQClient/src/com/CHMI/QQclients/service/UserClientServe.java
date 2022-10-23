package com.CHMI.QQclients.service;

import com.CHMI.Common.Message;
import com.CHMI.Common.MessageType;
import com.CHMI.Common.User;
import sun.rmi.server.MarshalOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author 茶米酱
 * @version 1.0
 * 客户端把客户类信息进行处理
 */
public class UserClientServe {
    //用户信息类
    private  User u =  new User();
    //网络连接服务端用
    private Socket socket =null;
    private boolean certain = false;//取得返回值是否成功
    public boolean ChekUser(String uid,String pwd)  {
        try {

            u.setUserid(uid);
            u.setPassword(pwd);
            socket = new Socket(InetAddress.getByName("192.168.199.1"), 9999);
            //将u序列化发给服务端
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u);
            //拿到客户端返回的比对
            ObjectInputStream oip = new ObjectInputStream(socket.getInputStream());
            Message message= (Message) oip.readObject();
            if (message.getContenttype().equals(MessageType.Login_success_prompt)){
                //启动线程将socket交给线程·持有
                ClientConnectServeThread ccst = new ClientConnectServeThread(socket);
                ccst.start();
                //把线程加入线程管理集合
                ManageClientConnectServeThread.addManageuserthread(uid,ccst);
                 certain = true;
            }
            else {
                //如果失败关闭网络io
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return certain;
    }
    //要求服务端给上线成员信息
    public void onlineFriendList(){
        //准备数据包
        Message message = new Message();
        message.setSender(u.getUserid());
        message.setContenttype(MessageType.MESSAGE_GET_ONLINE_FRIEND);
        //得到对应的网络连接
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(
                            ManageClientConnectServeThread.getClientConnectServeThread(
                                    u.getUserid()).getSocket().getOutputStream());
            oos.writeObject(message);//发送给服务端请求读取用户类表

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    //退出程序
    public void logout(){
        Message message = new Message();
        message.setSender(u.getUserid());
        message.setContenttype(MessageType.MESSAGE_CLIENT_EXIT);
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(
                            ManageClientConnectServeThread.getClientConnectServeThread(
                                    u.getUserid()).getSocket().getOutputStream());
            oos.writeObject(message);
            System.out.println(u.getUserid() + "退出系统");
            System.exit(0);//结束进程
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
