package com.example.chess;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Objects;

public class SearchController {
    @FXML
    private TextField characternameTextField;
    @FXML
    private TextArea infoTextArea;


    @FXML
    public void search() {
        String charactername = characternameTextField.getText();
        String request = "profilec," + charactername;
        System.out.println(request);

        // Receive and print server response
        String response = ConnectionManager.sendRequest(request);
        System.out.println("Server profile response: " + response);

        String displayText = getString(response, charactername);
        infoTextArea.setText(displayText);
        infoTextArea.setEditable(false);
    }

    private static String getString(String response, String charactername) {
        String displayText;
        if(!Objects.equals(response, "fail")){
            String[] parts = response.split(",");
            String elo = parts[1];
            String totalmatch = parts[2];
            String winrate = parts[3];

            displayText =
                    "Character Name: " + charactername + "\n"
                            + "ELO:                    " + elo + "\n"
                            + "Total Matches:     " + totalmatch + "\n"
                            + "Win Match:          " + winrate;
        }else{
            displayText = "User not found";
        }
        return displayText;
    }
}
