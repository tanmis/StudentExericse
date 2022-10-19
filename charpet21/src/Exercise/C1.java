package Exercise;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class C1 {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9998);
        byte[]bytes = new byte[1024];
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
        //接收
        socket.receive(packet);
        int length = packet.getLength();
        byte[] data = packet.getData();
        System.out.println(new String(data,0,length));
        if (packet != null){
            byte[] bytes1 = "红楼梦，xyj，三国演义，水浒传".getBytes(StandardCharsets.UTF_8);
            packet = new DatagramPacket(bytes1, bytes1.length, InetAddress.getByName("192.168.199.1"),9997);
            socket.send(packet);
        }
        else {
            byte[] bytes1 = "what".getBytes(StandardCharsets.UTF_8);
            packet = new DatagramPacket(bytes1, bytes1.length,InetAddress.getByName("192.168.199.1"),9997);
            socket.send(packet);
        }
        socket.close();
    }
}
