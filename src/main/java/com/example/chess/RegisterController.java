package com.example.chess;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RegisterController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField repassword;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField charactername;
    @FXML
    private Button registerButton;
    @FXML
    private Button movetologinButton;
    @FXML
    private void register(ActionEvent event) {
        // Take value from UI
        String enteredUsername = username.getText();
        String enteredPassword = password.getText();
        String enteredRepassword = repassword.getText();
        String enteredCharacterName = charactername.getText();

        // Check if the password matches
        if (!enteredPassword.equals(enteredRepassword)) {
            System.out.println("Password and Confirm Password do not match!");
            errorLabel.setText("X");
            return;
        }
        try (Socket socket = new Socket("localhost", 5000);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            String userName = username.getText();
            String passWord = password.getText();
            String characterName = charactername.getText();
            // Sending a register request to the server
            String request = "signup,"+userName+","+passWord+","+characterName;
            out.println(request);

            // Receive and print server response
            String response = in.readLine();
            System.out.println("Server signup response: " + response);
            if(response.equals("success")) {
                // Load the FXML file for the registration window
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent root = loader.load();

                // Create a new stage for the registration window
                Stage loginStage = new Stage();
                loginStage.setTitle("Login");
                loginStage.setScene(new Scene(root));

                // Show the registration window
                loginStage.show();

                //get register stage
                Stage registerStage = (Stage) registerButton.getScene().getWindow();

                // Close register stage
                registerStage.close();
            } else {
                // xử lý đăng nhập thất bại
            }


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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}