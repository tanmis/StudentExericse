package pub;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class B {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9998);
        byte[] bytes = "你好吃火锅".getBytes(StandardCharsets.UTF_8);
        DatagramPacket Packet =
                //通过ipconfig得到本机ip（以太网下，ipv6地址）
                new DatagramPacket(bytes, bytes.length, InetAddress.getByName("192.168.199.1"), 9999);
        socket.send(Packet);
        //
        //收到回复拆包
        byte[]bytes1 =new byte[1024];
        Packet = new DatagramPacket(bytes1, bytes1.length);
        socket.receive(Packet);
        int length = Packet.getLength();
        byte[] data = Packet.getData();
        System.out.println(new String(data,0,length));
        socket.close();

    }
}
