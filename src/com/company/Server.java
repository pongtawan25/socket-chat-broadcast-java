package com.company;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8080));
        System.out.println("Listening to 8080");

        ArrayList<Socket> clientSocketList = new ArrayList<>();
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.printf("Client connected %s:%d in %d\n", clientSocket.getInetAddress().getHostAddress(),
                    clientSocket.getPort(), clientSocket.getLocalPort());
            Thread thread = new Thread(new ClientHandler(clientSocket, clientSocketList));
            clientSocketList.add(clientSocket);
            thread.start();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private ArrayList<Socket> clientSocketList;

    public ClientHandler(Socket clientSocket, ArrayList<Socket> clientSocketList) {
        this.clientSocket = clientSocket;
        this.clientSocketList = clientSocketList;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(clientSocket.getInputStream());
            while (true) {
                if (scanner.hasNextLine()) {
                    String message = scanner.nextLine();
                    for (Socket client : clientSocketList) {
                        client.getOutputStream().write((message + "\n").getBytes());
                        client.getOutputStream().flush();
                    }
                }
            }

        } catch (Exception e) {

        }
    }
}

