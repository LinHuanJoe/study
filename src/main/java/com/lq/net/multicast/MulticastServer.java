package com.lq.net.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * @program: wheel
 * @Date: 2019/8/9 0009 17:09
 * @Author: lin huan
 * @Description:
 */
public class MulticastServer {
    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName("224.0.0.0");
            MulticastSocket server = new MulticastSocket();
            while (true){
                server.send(new DatagramPacket("aa".getBytes(),"aa".getBytes().length,group,8888));
                TimeUnit.SECONDS.sleep(2);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
