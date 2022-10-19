package Exercise;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class C2 {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9997);
        byte[] bytes = "四大名著是那些".getBytes(StandardCharsets.UTF_8);
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("192.168.199.1"), 9998);
        socket.send(packet);
        //接收回答
        bytes = new byte[1024];
        packet = new DatagramPacket(bytes, bytes.length);
        //接收
        socket.receive(packet);
        int length = packet.getLength();
        byte[] data = packet.getData();
        System.out.println(new String(data,0,length));
        socket.close();
    }
}
