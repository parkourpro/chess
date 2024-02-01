package com.example.chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientTest {
    public static void main(String[] args) {
//        ConnectionManager.connect();
        String request = "profilec, songoku";
        System.out.println(request);

        // Receive and print server response
        String response = ConnectionManager.sendRequest(request);
        System.out.println("Server profile response: " + response);


//        ConnectionManager.connect();

        String request2 = "profilec, trunggoku";
        System.out.println(request2);

        // Receive and print server response
        String response2 = ConnectionManager.sendRequest(request2);
        System.out.println("Server profile response: " + response2);
    }
}
