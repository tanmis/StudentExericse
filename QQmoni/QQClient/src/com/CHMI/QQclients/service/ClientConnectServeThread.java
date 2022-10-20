package com.CHMI.QQclients.service;

import com.CHMI.Common.Message;

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
                ObjectInputStream oit = new ObjectInputStream(socket.getInputStream());
                Message object = (Message) oit.readObject();
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
