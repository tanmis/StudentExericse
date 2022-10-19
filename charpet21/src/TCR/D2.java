package TCR;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class D2 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入需要寻找的文件");
        String name = scanner.next();
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("链接9999服务端");
        OutputStream outputStream = socket.getOutputStream();
        //告知服务端
        outputStream.write(name.getBytes(StandardCharsets.UTF_8));
        socket.shutdownOutput();
        //接收服务端的返回
        InputStream input = socket.getInputStream();
        byte[] bytes = StreamUtils.streamToByteArray(input);
        String fill = "e:\\"+name+".mp3";
        BufferedOutputStream overw = new BufferedOutputStream(new FileOutputStream(fill));
        overw.write(bytes);
        //关流
        input.close();
        outputStream.close();
        overw.close();
        socket.close();
        System.out.println("完成退出");



    }
}
