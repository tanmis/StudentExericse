package Exercise;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class B1 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务端9999等待连接");
        Socket socket = serverSocket.accept();
//        接收信息1
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println(reader.readLine());
////        写入2
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("tom");
        writer.newLine();
        writer.flush();
     //接收问题3
       reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println(reader.readLine());
        //告知问题4
        writer.write("小狗");
        writer.newLine();
        writer.flush();
        //关闭流
        reader.close();
        writer.close();
        socket.close();
    }
}
