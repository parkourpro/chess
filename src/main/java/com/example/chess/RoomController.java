package com.example.chess;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.Arrays;
import java.util.Objects;

public class RoomController {
    @FXML
    private Label roomidLabel;
    @FXML
    private Label player1idLabel;
    @FXML
    private TextArea onlineplayersTextArea;

    public void initData(String roomId, String charactername) {
        roomidLabel.setText(roomId);
        player1idLabel.setText(charactername);

        String request = "getOnlinePlayers";
        System.out.println(request);
        // Receive and print server response
        String response = ConnectionManager.sendRequest(request);
        assert response != null;
        response = response.substring(1, response.length() - 1);
        // Tách chuỗi bằng dấu phẩy và khoảng trắng
        String[] items = response.split(",\\s*");
        // In ra từng phần tử
        StringBuilder displayText = new StringBuilder();
        for (String item : items) {
//            System.out.println(item);
            if(!Objects.equals(item, charactername)){
                displayText.append(item).append("\n");
            }
        }

        onlineplayersTextArea.setText(displayText.toString());
        onlineplayersTextArea.setEditable(false);
    }

    public void onlineplayer() {

    }

}
