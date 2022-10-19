package TCR;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class D1 {
    public static void main(String[] args) throws Exception {
        //服务器端等待连接
        System.out.println("服务端端口9999等待连接");
        Socket socket = new ServerSocket(9999).accept();
//        接收数据
        InputStream inputStream = socket.getInputStream();
        byte[]buf = new byte[1024];
        int lend = 0;
        String Musicname = null;
        while ((lend = inputStream.read(buf))!= -1){
            Musicname  = new String(buf,0, buf.length);
        }
        //去取数值
        String fill = "";
        if (Musicname.equals("高山流水")){
            fill = "src\\高山流水.mp3";
        }
        else {
            fill = "src\\无名.mp3";
        }
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(fill));
        byte[] bytes = StreamUtils.streamToByteArray(input);
        //发送给客户端
        OutputStream output = socket.getOutputStream();
        output.write(bytes);
        socket.shutdownOutput();
        output.flush();
        //关闭
        inputStream.close();
        input.close();
        output.close();
        socket.close();




    }
}
