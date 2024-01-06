package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
//    void registerButton(ActionEvent event) throws IOException {
//        String username = re_username.getText();
//        String characterName = re_charactername.getText();
//        String password = re_password.getText();
//        String confPassword = re_confpassword.getText();
//
//        if (!password.equals(confPassword)) {
//            // Display error message: Passwords do not match
//        } else {
//            // Register the user
//            // If registration is successful, redirect to login page
//            Stage stage = (Stage) re_username.getScene().getWindow();
//            // replace 'Login.fxml' with your login page
//            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        }
//    }
}