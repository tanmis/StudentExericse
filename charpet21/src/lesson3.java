import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class lesson3 {
    public static void main(String[] args) throws Exception {
        //1. 服务端在本机监听8888端口
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务端8888.。。。等待连接");
        //2. 等待连接
        Socket accept = serverSocket.accept();
        //创建读取磁盘文件的输入流
        String photofill = "E:\\photo\\yb.bmp";
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(photofill));
        //bytes 就是filePath对应的字节数组
        byte[] bytes = StreamUtils.streamToByteArray(bis);
        //通过socket获取到输出流, 将bytes数据发送给服务端
        BufferedOutputStream bos = new BufferedOutputStream(accept.getOutputStream());
        bos.write(bytes);
        accept.shutdownOutput();
        //=====接收从服务端回复的消息=====
        InputStream wre = accept.getInputStream();
        String s = StreamUtils.streamToString(wre);
        System.out.println(s);
        //关闭其他
        wre.close();
        bis.close();
        bos.close();
        accept.close();


    }

}
