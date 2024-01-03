package com.example.javafxproject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Button LoginButton;
    @FXML
    private Button MoveToRegisterButton;
    @FXML
    private void moveToRegister(ActionEvent event) {
        try {
            // Load the FXML file for the registration window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
            Parent root = loader.load();

            // Create a new stage for the registration window
            Stage registerStage = new Stage();
            registerStage.setTitle("Registration");
            registerStage.setScene(new Scene(root));

            // Show the registration window
            registerStage.show();


            // Close the login stage
            Stage loginStage = (Stage) MoveToRegisterButton.getScene().getWindow();
            loginStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
