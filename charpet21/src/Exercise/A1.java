package Exercise;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class A1 {
    public static void main(String[] args) throws IOException {
        //连接端口9999
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端：9999连接·");
        //写入信息1
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("name");
        writer.newLine();
        writer.flush();

        //接收信息2
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println(reader.readLine());
        //回复3你喜欢什么问题
        writer.write("你喜欢什么呀(*´▽｀)ノノ");
        writer.newLine();
        writer.flush();
        //接收问题4
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println(reader.readLine());
        //关闭流
        reader.close();
        writer.close();
        socket.close();



    }
}
