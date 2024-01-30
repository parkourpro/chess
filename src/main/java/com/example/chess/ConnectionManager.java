package com.example.chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionManager {
    private static Socket socket;

    public static void connect() {
        try {
            socket = new Socket("localhost", 5000);
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
        connect(); // Đảm bảo có kết nối mở trước khi gửi yêu cầu
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(request);
            return in.readLine();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
