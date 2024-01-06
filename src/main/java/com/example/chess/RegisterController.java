package com.example.chess;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField re_username;

    @FXML
    private TextField re_charactername;

    @FXML
    private PasswordField re_password;

    @FXML
    private PasswordField re_confpassword;
    @FXML
    private Button movetologinButton;
    @FXML
    private void register(ActionEvent event) {
        try {
            // Load the FXML file for the registration window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();

            // Create a new stage for the registration window
            Stage registerStage = new Stage();
            registerStage.setTitle("Login");
            registerStage.setScene(new Scene(root));

            // Show the registration window
            registerStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    @FXML
    public void movetoLogin(ActionEvent event) {
        try {
            // Load the FXML file for the registration window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();

            // Create a new stage for the registration window
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.show();

            //get register stage
            Stage registerStage = (Stage) movetologinButton.getScene().getWindow();

            // Close register stage
            registerStage.close();

            // Show the registration window


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}