package com.example.chess;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.Objects;

public class RoomController {
    private String charname1;
    @FXML
    private Label roomidLabel;
    @FXML
    private Label player1idLabel;
    @FXML
    private TextArea onlineplayersTextArea;
    @FXML
    private Button inviteplayerButton;

    public void initData(String roomId, String charactername) {
        charname1 = charactername;
        roomidLabel.setText(roomId);
        player1idLabel.setText(charactername);

        String request = "getOnlinePlayers";
        System.out.println(request);
        // Receive and print server response
        String response = ConnectionManager.sendRequest(request);
        System.out.println(response);
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

    public void invite() throws IOException {
        // Load the FXML file for the home window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("invite.fxml"));
        Parent root = loader.load();

        // Get the controller for the home window
        InviteController inviteController = loader.getController();

        // Pass the username to the home controller
        inviteController.initData(charname1);
        // Create a new stage for the home window
        Stage inviteStage = new Stage();
        inviteStage.setScene(new Scene(root));
        inviteStage.initStyle(StageStyle.UNDECORATED);
        inviteStage.show();

    }

    public void onlineplayer() {

    }

}
