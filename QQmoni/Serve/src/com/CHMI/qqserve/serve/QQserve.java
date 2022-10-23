package com.CHMI.qqserve.serve;

import com.CHMI.Common.Message;
import com.CHMI.Common.MessageType;
import com.CHMI.Common.User;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * @author 茶米酱
 * @version 1.0
 * 服务端后台
 */
public class QQserve {
    private ServerSocket serverSocket;
    private Socket socket;
    private User user;
    private static HashMap<String,User> uhm = new HashMap<>();
    static {
        uhm.put("100",new User("100","1234"));
        uhm.put("200",new User("200","1234"));
        uhm.put("300",new User("300","1234"));
        uhm.put("400",new User("400","1234"));
        uhm.put("500",new User("500","1234"));
    }

    public QQserve(){
        try {
            System.out.println("服务端在9999监听。。。。。");
            serverSocket = new ServerSocket(9999);
            while (true){
                socket = serverSocket.accept();
                //验证是否是用户本人，安全验证
                ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
                User u = (User) oin.readObject();
//                建立一个信息类
                Message message = new Message();
                ObjectOutputStream oio = new ObjectOutputStream(socket.getOutputStream());
                //安全验证
                if (checkuser(u.getUserid(),u.getPassword())){
                    //设置登录成功
                    message.setContenttype(MessageType.Login_success_prompt);
                    oio.writeObject(message);
                    //将io让线程持有
                    ServeConnectClientThread serveConnectClientThread =
                            new ServeConnectClientThread(u.getUserid(), socket);
                    serveConnectClientThread.start();
                    //将线程集体管理
                    ManageServeConnectClientThread.addManageServeConnectClientThread(u.getUserid(),serveConnectClientThread);
                }
                else {
                    //登录失败
                    message.setContenttype(MessageType.UN_Login_success_prompt);
                    oio.writeObject(message);
                    socket.shutdownOutput();
                    socket.close();
                }


            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //安全验证
    public boolean checkuser(String userid,String passwd){
        User user = uhm.get(userid);
        if (user == null){
            return false;
        }
        if (!(passwd.equals(user.getPassword()))){

            return false;
        }
        return true;
    }
}
