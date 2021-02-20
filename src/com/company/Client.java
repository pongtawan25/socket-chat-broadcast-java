package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Socket clientSocket = new Socket();
        System.out.println("Connecting");
        clientSocket.connect(new InetSocketAddress("127.0.0.1", 8080));
        System.out.printf("Connected from port %d\n", clientSocket.getLocalPort());

        Scanner scanner = new Scanner(clientSocket.getInputStream());
        new Thread(() -> {
            while (true) {
                System.out.println(scanner.nextLine());
            }
        }).start();

        while (true) {
            String message = sc.nextLine();
            clientSocket.getOutputStream().write((message + "\n").getBytes());
            clientSocket.getOutputStream().flush();
        }
    }
}