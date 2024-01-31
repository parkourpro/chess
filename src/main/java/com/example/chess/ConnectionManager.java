package com.example.chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionManager {
    public static Socket socket;

    public static void connect() {
        try {
            socket = new Socket("127.0.0.1", 5000);
            System.out.println("create new socket");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String sendRequest(String request) {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())))
        {

            out.println(request);
            return in.readLine();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
