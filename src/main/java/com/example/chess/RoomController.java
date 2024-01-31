package com.example.chess;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
public class RoomController {
    @FXML
    private Label roomidLabel;

    @FXML
    private Label player1idLabel;

    public void initData(String roomId, String username) {
        roomidLabel.setText(roomId);
        player1idLabel.setText(username);
    }

}
