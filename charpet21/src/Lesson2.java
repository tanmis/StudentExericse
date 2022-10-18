import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Lesson2 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket accept = serverSocket.accept();
        OutputStream outputStream = accept.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write("1");
        writer.newLine();
        writer.flush();
        InputStream inputStream = accept.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        writer.write("3");
        writer.newLine();
        writer.flush();
        System.out.println(s);
        writer.close();
        bufferedReader.close();
        accept.close();
    }
}
