import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class Lesson2_1 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(),9999);
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println(bufferedReader.readLine());
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write("2");
        writer.newLine();
        writer.flush();
        System.out.println(bufferedReader.readLine());
        writer.close();
        bufferedReader.close();
        socket.close();
    }
}
