package com.example.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/7/14 9:01
 */
public class WakeOnLan {

    public static void main(String[] args) {
        String macAddress = "目标计算机的MAC地址";
        String broadcastAddress = "广播地址";
        int port = 9; // 唤醒端口号

        try {
            // 转换MAC地址为字节数组
            macAddress = macAddress.replaceAll(":", "");
            byte[] macBytes = new byte[6];
            for (int i = 0; i < 6; i++) {
                macBytes[i] = (byte) Integer.parseInt(macAddress.substring(i * 2, i * 2 + 2), 16);
            }

            // 构建唤醒数据包
            byte[] magicPacket = new byte[102];
            for (int i = 0; i < 6; i++) {
                magicPacket[i] = (byte) 0xff;
            }
            for (int i = 6; i < 102; i += 6) {
                System.arraycopy(macBytes, 0, magicPacket, i, 6);
            }

            // 创建广播地址
            InetAddress address = InetAddress.getByName(broadcastAddress);

            // 创建UDP套接字并发送唤醒数据包
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(magicPacket, magicPacket.length, address, port);
            socket.send(packet);
            socket.close();

            System.out.println("唤醒信号已发送到目标计算机！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
