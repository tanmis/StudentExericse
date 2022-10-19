package pub;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class A {
    public static void main(String[] args) throws IOException {
        DatagramSocket Socket = new DatagramSocket(9999);
        System.out.println("连接a端口9999");
        byte[]bytes =new byte[1024];
        DatagramPacket Packet = new DatagramPacket(bytes, bytes.length);
//        接收数据
        System.out.println("等待数据进去");
        Socket.receive(Packet);
        //拆包
        int length = Packet.getLength();//得到实际数据长度
        byte[] data = Packet.getData();//得到数据
        System.out.println(new String(data,0,length));
        //回复信息
        byte[] bytes1 = "好的ヽ(￣▽￣)و".getBytes(StandardCharsets.UTF_8);
        DatagramPacket datagramPacket = new DatagramPacket(bytes1, bytes1.length, InetAddress.getByName("192.168.199.1"), 9998);
        Socket.send(datagramPacket);
        Socket.close();


    }
}
