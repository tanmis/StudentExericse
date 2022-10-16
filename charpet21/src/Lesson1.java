import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Lesson1 {
    public static void main(String[] args) throws IOException {
        //本机端口9999
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("电脑服务器连接 + wait");
        Socket accept = serverSocket.accept();
        InputStream inputStream = accept.getInputStream();
        byte[] buf = new byte[1024];
        int redlin = 0;
        while ((redlin = inputStream.read(buf)) != -1){
            System.out.println(new String(buf,0,redlin));
        }
        OutputStream outputStream = accept.getOutputStream();
            outputStream.write("holle,sevre".getBytes(StandardCharsets.UTF_8));
            accept.shutdownOutput();
        inputStream.close();
        outputStream.close();
        accept.close();




    }
}
