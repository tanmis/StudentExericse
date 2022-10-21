package com.CHMI.qqserve.serve;

import com.CHMI.Common.Message;

import java.io.ObjectInputStream;
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
                Message oib =(Message) oin.readObject();
                //后面会用message进行通讯
            } catch (Exception e) {
                e.printStackTrace();
            }  finally {
            }
        }
    }
}
