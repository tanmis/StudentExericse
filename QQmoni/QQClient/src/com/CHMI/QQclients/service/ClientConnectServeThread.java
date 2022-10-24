package com.CHMI.QQclients.service;

import com.CHMI.Common.Message;
import com.CHMI.Common.MessageType;

import java.io.*;
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
                if (om.getContenttype().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND)){
                    //分割取出
                    System.out.println("==========用户列表===========");
                    String[] s = om.getContent().split(" ");
                    for (int i = 0; i < s.length; i++) {
                        System.out.println((i+1) + "号：\t" + s[i]);
                    }

                }
                else if (om.getContenttype().equals(MessageType.MESSAGE_COMM_MES)){
                    System.out.println("=======有消息======");
                    System.out.println(om.getSender() + "给您发送消息\n"+om.getContent());

                }
                //群发接收
                else if (om.getContenttype().equals(MessageType.MESSAGE_TO_ALL_MES)){
                    System.out.println("=======有消息======");
                    System.out.println(om.getSender() + "给您发送消息\n"+om.getContent());
                    System.out.println(om.getTimes());
                }
                else if (om.getContenttype().equals(MessageType.MESSAGE_FILE_MES)){
                    String[] s = om.getContent().split(" ");
                    System.out.println(om.getSender() + s[0]+"把文件发送到" + om.getReceiver() + "电脑" + s[1]);
                    BufferedInputStream oin = new BufferedInputStream(new FileInputStream(s[0]));
                    BufferedOutputStream oos = new BufferedOutputStream(new FileOutputStream(s[1]));
                    byte[] bytes = new byte[1024];
                    int led = 0;
                    while ((led = oin.read(bytes))!= -1){
                        oos.write(bytes,0,led);
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
