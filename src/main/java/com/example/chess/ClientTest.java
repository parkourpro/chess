package com.example.chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientTest {
    public static void main(String[] args) {
       HomeController hc = new HomeController();
       hc.initData("son");
    }
}
