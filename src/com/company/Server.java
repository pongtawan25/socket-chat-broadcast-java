package com.company;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8080));
        System.out.println("Listening to 8080");
        Socket clientSocket = serverSocket.accept();
        System.out.printf("Client connected %s:%d\n", clientSocket.getInetAddress().getHostAddress(), clientSocket.getPort());

        Scanner scanner = new Scanner(clientSocket.getInputStream());
        System.out.println(scanner.nextLine());
        clientSocket.close();

    }
}
