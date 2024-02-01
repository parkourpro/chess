package com.example.chess;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InviteController {
    private String character_name1;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField characternameTextField;

    @FXML
    public void initData(String username) {
        character_name1 = username;
    }
    @FXML
    public void cancel(){
        // Close invite stage
        Stage loginStage = (Stage) cancelButton.getScene().getWindow();
        loginStage.close();
    }
    @FXML
    public void invite(){
        String character_name2 = characternameTextField.getText();
        String request = "inviteroom," + character_name1 + "," + character_name2;
        System.out.println(request);

        // Receive and print server response
        String response = ConnectionManager.sendRequest(request);
        System.out.println("Server invite response: " + response);
    }
}
