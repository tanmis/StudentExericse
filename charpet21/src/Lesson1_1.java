import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class Lesson1_1 {
    public static void main(String[] args) throws IOException {
        //本地接口
        Socket socket = new Socket(InetAddress.getLocalHost(),9999);
        System.out.println("客户端 socket 返回=" + socket.getClass());
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("holle".getBytes(StandardCharsets.UTF_8));
        socket.shutdownOutput();
        InputStream inputStream = socket.getInputStream();
            byte[]buf =new  byte[1032];
            int readline = 0;
            while ((readline = inputStream.read(buf)) != -1){
                System.out.println(new String(buf,0,readline));
        }


        outputStream.close();
        inputStream.close();
        socket.close();


    }
}
