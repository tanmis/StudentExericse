package com.CHMI.qqserve.serve;

import com.CHMI.Common.Message;
import com.CHMI.Common.MessageType;
import com.CHMI.Common.User;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

/**
 * @author 茶米酱
 * @version 1.0
 */
public class ServeConnectClientThread extends Thread{
    private boolean bloon = true;
    private  Socket socket ;
    private String userid;

    public  ServeConnectClientThread(String userid,Socket socket) {
        this.socket = socket;
        this.userid = userid;
        //离线问件查看是否有
        if (ManageOfflineCustomerData.getmessage(userid)!= null){
            try {
                System.out.println("发送存储过到文件给"+userid);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(ManageOfflineCustomerData.getmessage(userid));
                System.out.println("删除托管无用文件");
                ManageOfflineCustomerData.deleteManagemessages(userid);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    @Override
    public void run() {
        while (bloon){
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
                    //如果是退出程序
                }else if (oib.getContenttype().equals(MessageType.MESSAGE_CLIENT_EXIT)){
                    ManageServeConnectClientThread.DeleteserveConnectClientThread(userid);
                    System.out.println("关闭"+ userid + "用户");
                   bloon = false;
                   socket.close();


                }//如果是私聊信息发送
                else if (oib.getContenttype().equals(MessageType.MESSAGE_COMM_MES)){
                    //将要传输信息打包
                    Message oib2 = new Message();
                    oib2.setContent(oib.getContent());
                    oib2.setSender(oib.getSender());
                    System.out.println(oib.getSender());
                    oib2.setReceiver(oib.getReceiver());
                    oib2.setTimes(new java.util.Date().toString());
                    oib2.setContenttype(MessageType.MESSAGE_COMM_MES);
//                    如果用户不在线处理
                   if (ManageServeConnectClientThread.getserveConnectClientThread(oib.getReceiver())== null){
                        System.out.println("拿到离线用户信息");
                        ManageOfflineCustomerData.putManagemessages(oib.getReceiver(),oib2);
                        break;
                    }

                    //取出指定要发送的私聊的人的io进行发送
                    ObjectOutputStream oos =
                            new ObjectOutputStream(
                                    ManageServeConnectClientThread.getserveConnectClientThread(
                                            oib.getReceiver()).getSocket().getOutputStream());
                    oos.writeObject(oib2);

                }
                //如果是群发
                else if (oib.getContenttype().equals(MessageType.MESSAGE_TO_ALL_MES)){
                    String[] mscct = ManageServeConnectClientThread.getuserid().split(" ");
                    for (int i = 0; i < mscct.length; i++) {
                        Message oib2 = new Message();
                        oib2.setContent(oib.getContent());
                        oib2.setContenttype(MessageType.MESSAGE_TO_ALL_MES);
                        oib2.setTimes(new java.util.Date().toString());
                        oib2.setSender(userid);
                        //判断是否为自己
                        if (oib.getSender().equals(mscct[i])){
                            continue;
                        }
                        oib2.setReceiver(mscct[i]);
                        //发送给其他人
                        ObjectOutputStream oos =
                                new ObjectOutputStream(
                                        ManageServeConnectClientThread.getserveConnectClientThread(
                                                mscct[i]).socket.getOutputStream());
                        oos.writeObject(oib2);
                    }
                }
                //如果发送文件
                else if (oib.getContenttype().equals(MessageType.MESSAGE_FILE_MES)){
                    Message message = new Message();
                    message.setContenttype(MessageType.MESSAGE_FILE_MES);
                    message.setReceiver(oib.getReceiver());
                    message.setSender(oib.getSender());
                    message.setContent(oib.getContent());
                    message.setTimes(new java.util.Date().toString());
                    ObjectOutputStream oos =
                            new ObjectOutputStream(
                                    ManageServeConnectClientThread.getserveConnectClientThread(
                                            oib.getReceiver()).socket.getOutputStream());
                    oos.writeObject(message);

                }
                //判断是否有离线消息
                else {
                    System.out.println("暂不处理");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }  finally {
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public boolean isBloon() {
        return bloon;
    }

    public void setBloon(boolean bloon) {
        this.bloon = bloon;
    }
}
