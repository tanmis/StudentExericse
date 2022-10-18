import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class lesson3_1 {
    public static void main(String[] args) throws Exception {
      //；连接到客户端
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);
        System.out.println("客户端连接9999");
        //3. 读取客户端发送的数据
       //   通过Socket得到输入流
        BufferedInputStream re = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = StreamUtils.streamToByteArray(re);
        //写入指定位置
        String fill = "src\\qie.bmp";
        BufferedOutputStream wr = new BufferedOutputStream(new FileOutputStream(fill));
        wr.write(bytes);
        //告知服务端收到
        BufferedWriter tall = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        tall.write("收到图片");
        tall.flush();
        wr.close();
        socket.shutdownOutput();
        //
        //关闭其他资源
        re.close();
        tall.close();
        socket.close();
    }
}
