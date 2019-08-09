package com.lq.net.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @program: wheel
 * @Date: 2019/8/9 0009 17:21
 * @Author: lin huan
 * @Description:
 */
public class MulticastClient {

    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName("224.0.0.0");
            MulticastSocket socket = new MulticastSocket(8888);
            socket.joinGroup(group);
            byte[] b = new byte[1024];
            while (true){
                DatagramPacket serverData = new DatagramPacket(b,b.length);
                socket.receive(serverData);
                System.out.println(new String(serverData.getData()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
